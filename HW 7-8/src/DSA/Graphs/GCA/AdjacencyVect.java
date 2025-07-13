package DSA.Graphs.GCA;

import java.util.Collection;
import java.util.Iterator;

/**
 * AdjacencyVect class implements a set-like structure using a boolean array
 * to represent adjacency vectors efficiently for graph vertices.
 * It stores which vertices are adjacent (true) or not (false).
 * 
 * This class implements Collection<Integer> interface where elements are
 * vertex indices represented as Integers.
 * 
 * Time Complexity notes:
 * - add, remove, contains: O(1)
 * - size, isEmpty, containsAll, addAll, removeAll, retainAll: O(n) where n = capacity
 * - iterator methods have O(1) amortized for next and O(n) for hasNext in worst case.
 * 
 */
public class AdjacencyVect implements Collection<Integer> {

    private boolean[] vect;  // Stores adjacency info: true means vertex included
    private int capacity;    // Total number of possible vertices

    /**
     * Constructor to initialize the adjacency vector with given capacity.
     * @param vNum number of vertices (capacity)
     */
    public AdjacencyVect(int vNum) {
        this.capacity = vNum;
        this.vect = new boolean[vNum];
    }

    /**
     * Returns the number of vertices currently set as adjacent (true).
     * Time Complexity: O(n)
     * @return count of true elements in vect
     */
    @Override
    public int size() {
        int count = 0;
        for (boolean b : vect) {
            if (b) count++;
        }
        return count;
    }

    /**
     * Checks if the adjacency vector is empty (no vertex set as adjacent).
     * Time Complexity: O(n)
     * @return true if no vertex is adjacent, false otherwise
     */
    @Override
    public boolean isEmpty() {
        for (boolean b : vect) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given Integer vertex index is in the adjacency vector.
     * Time Complexity: O(1)
     * @param o Integer vertex index to check
     * @return true if vect[o] is true; false if not or invalid index/type
     */
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        int index = (Integer) o;

        if (index < 0 || index >= vect.length) {
            return false;
        }
        return vect[index];
    }

    /**
     * Returns an iterator over the vertices set as adjacent.
     * Time Complexity: iterator creation O(1),
     * hasNext worst O(n), next O(1)
     * @return Iterator over Integer vertex indices included in adjacency
     */
    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    /**
     * Returns an array containing all the adjacent vertices.
     * Time Complexity: O(n)
     * @return Object[] array of Integers representing vertices set as adjacent
     */
    @Override
    public Object[] toArray() {
        int size = size();
        int index = 0;
        int count = 0;
        Integer[] arr = new Integer[size];
        for (boolean b : vect) {
            if (b) {
                arr[index] = count;
                index++;
            }
            count++;
        }
        return arr;
    }

    /**
     * Returns an array containing all the adjacent vertices,
     * in the runtime type of the provided array if large enough.
     * Time Complexity: O(n)
     * @param <T> the component type of the array to contain the collection
     * @param a the array into which the elements of the collection are to be stored
     * @return array containing adjacent vertices
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        int index = 0;
        int count = 0;
        if (a.length < size) {
            a = (T[]) new Object[size];
        }
        for (boolean b : vect) {
            if (b) {
                a[index] = (T) Integer.valueOf(count);
                index++;
            }
            count++;
        }
        return a;
    }

    /**
     * Adds a vertex index to the adjacency vector.
     * Time Complexity: O(1)
     * @param e vertex index to add
     * @return true if added or already present, false if index invalid
     */
    @Override
    public boolean add(Integer e) {
        if (e < 0 || e >= vect.length) {
            return false;
        }
        if (vect[e]) {
            return true;
        }
        vect[e] = true;
        return true;
    }

    /**
     * Removes a vertex index from the adjacency vector.
     * Time Complexity: O(1)
     * @param o vertex index to remove
     * @return true if removed or already absent, false if invalid index/type
     */
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        Integer e = (Integer) o;
        if (e < 0 || e >= vect.length) {
            return false;
        }
        if (!vect[e]) {
            return true;
        }
        vect[e] = false;
        return true;
    }

    /**
     * Checks if all elements of collection c are contained in this adjacency vector.
     * Time Complexity: O(n)
     * @param c collection to check for containment
     * @return true if all elements present, false otherwise or if invalid types
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!(o instanceof Integer)) {
                return false;
            }
            int index = (Integer) o;
            if (index < 0 || index >= vect.length || !vect[index]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all elements from collection c to this adjacency vector.
     * Time Complexity: O(n)
     * @param c collection of Integer vertex indices to add
     * @return true if the adjacency vector changed as a result
     */
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean changed = false;
        for (Integer o : c) {
            if (o == null || o < 0 || o >= vect.length) {
                continue;
            }
            if (!vect[o]) {
                vect[o] = true;
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all elements from this adjacency vector that are in collection c.
     * Time Complexity: O(n)
     * @param c collection of vertex indices to remove
     * @return true if the adjacency vector changed as a result
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < vect.length; i++) {
            if (vect[i] && c.contains(i)) {
                vect[i] = false;
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Retains only the elements in this adjacency vector that are contained in collection c.
     * Time Complexity: O(n)
     * @param c collection of vertex indices to retain
     * @return true if the adjacency vector changed as a result
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < vect.length; i++) {
            if (vect[i] && !c.contains(i)) {
                vect[i] = false;
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Clears the adjacency vector, removing all vertices.
     * Time Complexity: O(n)
     */
    @Override
    public void clear() {
        for (int i = 0; i < vect.length; i++) {
            vect[i] = false;
        }
    }

    /**
     * Iterator implementation over the adjacency vector,
     * returning the indices of true elements.
     */
    public class MyIterator implements Iterator<Integer> {

        private int index;

        /**
         * Constructor initializes index at zero.
         */
        MyIterator() {
            index = 0;
        }

        /**
         * Checks if there is a next true element in vect.
         * Time Complexity: O(n) worst case (skips false values)
         * @return true if next element exists, false otherwise
         */
        @Override
        public boolean hasNext() {
            while (index < capacity && !vect[index]) {
                index++;
            }
            return index < capacity;
        }

        /**
         * Returns the current index and advances the iterator.
         * Time Complexity: O(1)
         * @return next adjacent vertex index
         */
        @Override
        public Integer next() {
            return index++;
        }
    }
}
