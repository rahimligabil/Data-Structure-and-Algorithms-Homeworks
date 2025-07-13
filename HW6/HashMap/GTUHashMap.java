package HashMap;

import java.util.Iterator;
import Entry.Entry;

public class GTUHashMap<K, V> {

    private Entry<K, V>[] table;
    private int size;
    private int capacity;
    private int collisionCount; 
    private static final int INITIAL_CAPACITY = 131071;

    @SuppressWarnings("unchecked")
    public GTUHashMap() {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
        collisionCount = 0;
    }

    public V get(K key) {
        if (key == null) return null;

        int hash = (key.hashCode() & 0x7FFFFFFF);
        int i = 0;

        while (i < table.length) {
            int index = (hash + i * i) % table.length;
            Entry<K, V> entry = table[index];

            if (entry == null) return null;
            if (!entry.isDeleted && entry.key.equals(key)) return entry.value;

            i++;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        if (key == null) return false;

        int hash = (key.hashCode() & 0x7FFFFFFF);
        int i = 0;

        while (i < table.length) {
            int index = (hash + i * i) % table.length;
            Entry<K, V> entry = table[index];

            if (entry == null) return false;
            if (!entry.isDeleted && entry.key.equals(key)) return true;

            i++;
        }

        return false;
    }

    public void remove(K key) {
        if (key == null) return;

        int hash = (key.hashCode() & 0x7FFFFFFF);
        int i = 0;

        while (i < table.length) {
            int index = (hash + i * i) % table.length;
            Entry<K, V> entry = table[index];

            if (entry == null) return;
            if (!entry.isDeleted && entry.key.equals(key)) {
                entry.isDeleted = true;
                size--;
                return;
            }

            i++;
        }
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");

        if ((float) size / table.length > 0.7f) {
            rehash();
        }

        int hash = (key.hashCode() & 0x7FFFFFFF);
        int i = 0;

        while (i < table.length) {
            int index = (hash + i * i) % table.length;
            Entry<K, V> entry = table[index];

            if (entry == null || entry.isDeleted) {
                table[index] = new Entry<>(key, value);
                size++;
                return;
            } else if (entry.key.equals(key)) {
                entry.value = value;
                if (entry.isDeleted) {
                    entry.isDeleted = false;
                    size++; 
                }
                return;
            } else {
                collisionCount++;  // collision count increment
            }

            i++;
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        int newCapacity = findPrime(capacity * 2);
        table = (Entry<K, V>[]) new Entry[newCapacity];
        capacity = newCapacity;
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }

    private int findPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    public class MyIterator<E> implements Iterator<E> {
        private int index = 0;

        @SuppressWarnings("unchecked")
        public E next() {
            return (E) table[index++].key;
        }

        public boolean hasNext() {
            while (index < table.length) {
                if (table[index] != null && !table[index].isDeleted) {
                    return true;
                }
                index++;
            }
            return false;
        }
    }

    public Iterator<K> getIterator() {
        return new MyIterator<>();
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public void printMemoryUsage() {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double usedMemoryMB = usedMemory / (1024.0 * 1024.0); 
        System.out.printf("Total memory used: %.2f MB\n", usedMemoryMB);
    }
}
