package src.PriorityQueue;

/**
 * This interface represents a priority queue, where elements are ordered based on their natural ordering
 * or by a comparator provided at queue construction time. It supports typical priority queue operations:
 * adding an element, removing the highest-priority element, and checking if the queue is empty.
 * 
 * @param <T> the type of elements held in this priority queue, which must be comparable to other elements of the same type.
 */
public interface MyPriorityQueue<T extends Comparable<T>> {

    /**
     * Adds an element to the priority queue. The element will be placed according to its priority in the queue.
     * 
     * @param item the element to be added to the queue
     * 
     * Time Complexity: O(log n)
     * The add operation involves inserting an element into a heap (or another priority queue implementation), 
     * which requires maintaining the heap property, taking logarithmic time relative to the number of elements in the queue (n).
     */
    public void add(T item);

    /**
     * Removes and returns the highest-priority element from the priority queue. The element removed
     * will be the one with the highest priority, according to its natural ordering.
     * 
     * @return the highest-priority element, or null if the queue is empty
     * 
     * Time Complexity: O(log n)
     * Removing the highest-priority element typically involves removing the root of the heap and reheapifying the queue, 
     * which takes logarithmic time based on the number of elements in the queue (n).
     */
    public T poll();

    /**
     * Checks if the priority queue is empty.
     * 
     * @return true if the queue contains no elements, false otherwise
     * 
     * Time Complexity: O(1)
     * The isEmpty() method performs a constant-time check on the size of the queue, 
     * making this operation O(1).
     */
    Boolean isEmpty();
}
