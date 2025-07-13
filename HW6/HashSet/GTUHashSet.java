package HashSet;

import java.util.Iterator;

import HashMap.GTUHashMap;
public class GTUHashSet<E> implements Iterable<E> {

    private static final Object WORD = new Object();

    private GTUHashMap<E, Object> map;

    public GTUHashSet() {
        map = new GTUHashMap<>();
    }

    public void add(E element) {
        map.put(element, WORD);
    }

    public void remove(E element) {
        map.remove(element);
    }

  
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public int size() {
        return map.size();
    }

    @Override
    public Iterator<E> iterator() {
        return map.getIterator();
    }

    public int getCollisionCount() {
       return map.getCollisionCount();
    }

    public void printMemoryUsage() {
       map.printMemoryUsage();
    }
}
