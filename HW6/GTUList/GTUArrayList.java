package GTUList;

import java.util.Iterator;

public class GTUArrayList<E extends Comparable<E>> implements Iterable<E> {

    private E[] data; 
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    private int capacity;

    @SuppressWarnings("unchecked")
    public GTUArrayList() {
        data = (E[]) new Comparable[INITIAL_CAPACITY]; 
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    public void add(E element) {
        if (size == capacity) {
            resize();
        }
        data[size++] = element; 
    }

    public boolean contains(E element) {
        if (element == null) return false;

        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        E[] newData = (E[]) new Comparable[capacity]; 
        System.arraycopy(data, 0, newData, 0, size); 
        data = newData;
    }

   public class MyIterator<K> implements Iterator<K> {
        private int index = 0;

        @SuppressWarnings("unchecked")
        public K next() {
            return (K) data[index++];
        }

        public boolean hasNext() {
            if(index < size){
                return true;
            }
            return false;
    }
}
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }


}
