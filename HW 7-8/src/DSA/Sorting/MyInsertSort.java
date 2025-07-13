package DSA.Sorting;

import java.util.Comparator;

/**
 * MyInsertSort class implements the insertion sort algorithm
 * as a subclass of GTUSorter.
 * 
 * It sorts a portion of an array using the provided comparator.
 * 
 * Time Complexity:
 * - Best Case: O(n) (when array is already sorted)
 * - Average and Worst Case: O(n²)
 * 
 * @see GTUSorter
 */
public class MyInsertSort extends GTUSorter {

    /**
     * Sorts the array segment [start, end) using insertion sort algorithm.
     * 
     * It iterates from start+1 to end-1, inserts each element into
     * its correct position in the sorted subarray to the left.
     * 
     * Time Complexity:
     * - Best case: O(n), if array is already sorted
     * - Worst case: O(n²), if array is reverse sorted
     * 
     * @param <T>        type of elements in array
     * @param arr        array to sort
     * @param start      starting index (inclusive)
     * @param end        ending index (exclusive)
     * @param comparator comparator to define element ordering
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start + 1; i < end; i++) {
            T temp = arr[i];
            int j = i - 1;

            // Shift elements to right to make space for temp
            while (j >= start && comparator.compare(temp, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

}
