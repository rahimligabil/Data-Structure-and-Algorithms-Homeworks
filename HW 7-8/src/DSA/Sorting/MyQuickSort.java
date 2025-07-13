package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * MyQuickSort class implements the QuickSort algorithm as a subclass of GTUSorter.
 * 
 * This implementation uses a randomized pivot selection to improve
 * average case performance and supports hybrid sorting by switching
 * to another sorter (like insertion sort) for small partitions.
 * 
 * Time Complexity:
 * - Best/Average case: O(n log n)
 * - Worst case: O(n²) (rare due to random pivot)
 * 
 * Hybrid Optimization:
 * When the partition size is smaller than 'partLimit' and a fallback
 * sorter is provided, the algorithm delegates sorting of that partition
 * to the fallback sorter.
 */
public class MyQuickSort extends GTUSorter {

    private GTUSorter sorter;   // Fallback sorter for small partitions (optional)
    private int partLimit;      // Threshold for switching to fallback sorter
    private Random rand = new Random();

    /**
     * Default constructor initializes with no fallback sorter and zero threshold.
     */
    public MyQuickSort() {
        this.sorter = null;
        this.partLimit = 0;
    }

    /**
     * Constructor with fallback sorter and partition limit.
     * 
     * @param sorter    fallback sorter to use for small partitions
     * @param partLimit partition size threshold to switch sorter
     */
    public MyQuickSort(GTUSorter sorter, int partLimit) {
        this.sorter = sorter;
        this.partLimit = partLimit;
    }

    /**
     * Sorts the array segment [start, end) using the QuickSort algorithm.
     * 
     * If the segment length is smaller than partLimit and a fallback sorter
     * is defined, delegates sorting to that sorter.
     * 
     * Uses a random pivot to avoid worst-case scenarios in sorted or
     * nearly sorted data.
     * 
     * Time Complexity:
     * - Average: O(n log n)
     * - Worst: O(n²) (rare due to random pivot)
     * 
     * @param <T>        type of elements in the array
     * @param arr        array to sort
     * @param start      starting index (inclusive)
     * @param end        ending index (exclusive)
     * @param comparator comparator defining order of elements
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {

        if (start >= end - 1) // Base case: zero or one element
            return;

        // If partition is small enough, use fallback sorter if available
        if (sorter != null && (end - start) < partLimit) {
            sorter.sort(arr, start, end, comparator);
            return;
        }

        // Random pivot selection within [start, end)
        int pivotIndex = start + rand.nextInt(end - start);
        T pivot = arr[pivotIndex];

        // Move pivot to the end for partitioning
        swap(pivotIndex, end - 1, arr);

        int i = start;
        // Partitioning: elements less than pivot to left, others to right
        for (int j = start; j < end - 1; j++) {
            if (comparator.compare(arr[j], pivot) < 0) {
                swap(i, j, arr);
                i++;
            }
        }

        // Place pivot in its correct position
        swap(i, end - 1, arr);

        // Recursively sort left partition [start, i)
        sort(arr, start, i, comparator);

        // Recursively sort right partition [i + 1, end)
        sort(arr, i + 1, end, comparator);
    }

    /**
     * Swaps two elements in the array.
     * Time Complexity: O(1)
     * 
     * @param i   first index
     * @param j   second index
     * @param arr array in which to swap elements
     * @param <T> type of elements
     */
    private <T> void swap(int i, int j, T[] arr) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
