package src.PriorityQueue;

/**
 * The TaskScheduler class implements a priority queue using a min-heap data structure.
 * It allows tasks to be added, removed, and processed according to their priority.
 * The queue stores elements of type E, which must be comparable to other elements of the same type.
 * 
 * @param <E> The type of the elements in the priority queue, which must implement Comparable.
 */
public class TaskScheduler<E extends Comparable<E>> implements MyPriorityQueue<E> {

    private E arr[];  // The array used to represent the heap
    private int size; // The current size of the heap
    private int capacity; // The maximum capacity of the heap
    private int INITIAL_CAPACITY = 10; // Default initial capacity of the heap

    /**
     * Constructs a TaskScheduler with a specified capacity.
     *
     * @param capacity The initial capacity of the priority queue.
     * 
     * Time Complexity: O(1)
     * The constructor only initializes the heap array and sets the capacity, which is a constant-time operation.
     */
    @SuppressWarnings("unchecked")
    public TaskScheduler(int capacity) {
        arr = (E[]) new Comparable[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * Constructs a TaskScheduler with the default initial capacity.
     * 
     * Time Complexity: O(1)
     * This constructor initializes the heap array with a default capacity and sets the size to zero.
     */
    @SuppressWarnings("unchecked")
    public TaskScheduler() {
        arr = (E[]) new Comparable[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    /**
     * Adds an element to the priority queue.
     * If the heap reaches its capacity, it will be resized.
     *
     * @param item The element to be added to the queue.
     * 
     * Time Complexity: O(log n)
     * Adding an element to a heap involves placing it in the next available position and performing a "heapify-up" operation, which takes logarithmic time.
     */
    @Override
    public void add(E item) {
        if (size == capacity) {
            resize();
        }
        if (size == 0) {
            arr[size++] = item;
        } else {
            arr[size] = item;
            int index = size;
            size++;
            heapifyup(index);
        }
    }

    /**
     * Ensures the heap property is maintained by moving the element at the given index
     * up the heap if it is smaller than its parent.
     *
     * @param index The index of the element to move up the heap.
     * 
     * Time Complexity: O(log n)
     * The heapify-up operation moves an element up the heap, which takes logarithmic time based on the height of the tree.
     */
    private void heapifyup(int index) {
        int child = index;

        while (child > 0) {
            int parent = (child - 1) / 2;

            // If the child is smaller than the parent, swap them
            if (arr[parent].compareTo(arr[child]) > 0) {
                E temp = arr[parent];
                arr[parent] = arr[child];
                arr[child] = temp;

                child = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Resizes the internal array to double its current capacity when the heap is full.
     * 
     * Time Complexity: O(n)
     * The resizing operation involves copying all elements from the old array to a new, larger array, which is a linear-time operation.
     */
    private void resize() {
        capacity = capacity * 2;
        @SuppressWarnings("unchecked")
        E[] newarr = (E[]) new Comparable[capacity];
        for (int i = 0; i < size; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

    /**
     * Removes and returns the highest-priority element from the priority queue.
     * The element removed will be the root (the smallest element).
     *
     * @return The highest-priority element, or null if the queue is empty.
     * 
     * Time Complexity: O(log n)
     * Removing the root element (the highest-priority element) requires swapping it with the last element and performing a "heapify-down" operation,
     * which has logarithmic time complexity.
     */
    @Override
    public E poll() {
        if (size == 0) {
            System.err.println("The heap is empty.");
            return null;
        }

        E returnElement = arr[0]; // Root element
        arr[0] = arr[size - 1];  // Move the last element to the root
        size--;
        heapifyDown(0);  // Restore heap property

        return returnElement;
    }

    /**
     * Ensures the heap property is maintained by moving the element at the given index
     * down the heap if it is larger than one or both of its children.
     *
     * @param parent The index of the element to move down the heap.
     * 
     * Time Complexity: O(log n)
     * The heapify-down operation ensures the smallest element is at the root of the heap by comparing it with its children
     * and moving it down the heap as needed. This operation takes logarithmic time based on the height of the tree.
     */
    private void heapifyDown(int parent) {
        int smallest = parent;

        while (true) {
            int leftChild = 2 * parent + 1;
            int rightChild = 2 * parent + 2;

            // Compare left child with parent
            if (leftChild < size && arr[leftChild].compareTo(arr[smallest]) < 0) {
                smallest = leftChild;
            }

            // Compare right child with parent
            if (rightChild < size && arr[rightChild].compareTo(arr[smallest]) < 0) {
                smallest = rightChild;
            }

            // If the smallest element is not the parent, swap and continue
            if (smallest != parent) {
                E temp = arr[parent];
                arr[parent] = arr[smallest];
                arr[smallest] = temp;

                parent = smallest;
            } else {
                break; // Heap property restored
            }
        }
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     * 
     * Time Complexity: O(1)
     * The isEmpty() method performs a constant-time check to determine if the heap is empty.
     */
    @Override
    public Boolean isEmpty() {
        return size == 0;
    }
}
