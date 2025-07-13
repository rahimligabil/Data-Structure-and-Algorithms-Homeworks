package DSA.Sorting;

import java.util.Comparator;

/**
 * MySelectSort class implements the Selection Sort algorithm
 * as a subclass of GTUSorter.
 * 
 * The algorithm divides the array into a sorted and unsorted region,
 * repeatedly selecting the minimum element from the unsorted region
 * and swapping it to the end of the sorted region.
 * 
 * Time Complexity:
 * - Best, Average, Worst Case: O(n²)
 * 
 * Selection Sort is not stable and not adaptive.
 * 
 * @see GTUSorter
 */
public class MySelectSort extends GTUSorter {

    /**
     * Sorts the array segment [start, end) using selection sort.
     * 
     * For each position i, finds the minimum element in [i, end)
     * and swaps it with the element at i.
     * 
     * Time Complexity: O(n²) in all cases since it always scans
     * the unsorted region fully.
     * 
     * @param <T>        type of elements in the array
     * @param arr        array to sort
     * @param start      starting index (inclusive)
     * @param end        ending index (exclusive)
     * @param comparator comparator defining element ordering
     */
    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start; i < end - 1; i++) {
            int min = i;
            for (int j = i + 1; j < end; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            swap(i, min, arr);
        }
    }

    /**
     * Swaps two elements in the array.
     * Time Complexity: O(1)
     * 
     * @param i   first index
     * @param min second index (index of minimum element)
     * @param arr array where swap happens
     * @param <T> element type
     */
    private <T> void swap(int i, int min, T[] arr) {
        T temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }

}
