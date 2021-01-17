import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sort {
    /**
     * Sorts an array of objects that implements Comparable interface using insertion sort.
     * 
     * @param <T>           an object that implements Comparable
     * @param arrayToSort   an array to be sorted
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arrayToSort) {
        for(int i = 1; i < arrayToSort.length; i++) {
            T currentValue = arrayToSort[i];
            int sortedIterator = i - 1;
            // iterate through sorted portion of array
            while((sortedIterator >= 0) && (currentValue.compareTo(arrayToSort[sortedIterator]) < 0))
                arrayToSort[sortedIterator + 1] = arrayToSort[sortedIterator--];
            arrayToSort[sortedIterator + 1] = currentValue;
        }
    }

    /**
     * Sorts an array of objects that implements Comparable interface using merge sort.
     * 
     * @param <T>           an object that implements Comparable
     * @param arrayToSort   an array to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arrayToSort) {
        mergeSort(arrayToSort, 0, arrayToSort.length);
    }

    /**
     * Helper method for mergeSort using recursion.
     * 
     * @param <T>           an object that implements Comparable
     * @param arrayToSort   an array to be sorted
     * @param startIndex    start index (inclusive)
     * @param endIndex      end index (exclusive)
     */
    private static <T extends Comparable<T>> void mergeSort(T[] arrayToSort, int startIndex, int endIndex) {
        if(startIndex + 1 < endIndex) {
            int centerIndex = (endIndex + startIndex)/2;
            mergeSort(arrayToSort, startIndex, centerIndex);  // sort left half
            mergeSort(arrayToSort, centerIndex, endIndex);    // sort right half
            merge(arrayToSort, startIndex, centerIndex, endIndex);    // merge two, sorted halves
        }
    }

    /**
     * Merges two sorted arrays separated by the start, center, and end index.
     * 
     * @param <T>           an object that implements Comparable
     * @param arrayToSort   an array to be sorted
     * @param startIndex    start index (inclusive)
     * @param centerIndex   center index (left array exclusive, right array inclusive)
     * @param endIndex      end index (exclusive)
     */
    private static <T extends Comparable<T>> void merge(T[] arrayToSort, int startIndex, int centerIndex, int endIndex) {
        List<T> left = new ArrayList<>(centerIndex - startIndex);
        List<T> right = new ArrayList<>(endIndex - centerIndex);
        for(int i = startIndex; i < centerIndex; i++)
            left.add(arrayToSort[i]);
        for(int i = centerIndex; i < endIndex; i++)
            right.add(arrayToSort[i]);
        int leftItr = 0;
        int rightItr = 0;
        for(int i = startIndex; i < endIndex; i++) {
            if(leftItr < left.size())
                if(rightItr < right.size())
                    if(left.get(leftItr).compareTo(right.get(rightItr)) < 0)
                        arrayToSort[i] = left.get(leftItr++);
                    else
                        arrayToSort[i] = right.get(rightItr++);
                else
                    arrayToSort[i] = left.get(leftItr++);
            else
                arrayToSort[i] = right.get(rightItr++);
        }
    }

    /**
     * Sorts an array of objects that implements Comparable interface using shell sort
     * with Knuth's increments, i.e. [1, 4, 13, ..., (3^k - 1)/2].
     * Algorithm from <i> Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
     * 
     * @param <T>           an object that implements Comparable
     * @param arrayToSort   an array to be sorted
     */
    public static <T extends Comparable<T>> void shellSort(T[] arrayToSort) {
        int n = arrayToSort.length;
        int gap = 1;
        while(gap < n/3)
            gap = gap * 3 + 1;
        
        while(gap > 0) {
            for(int i = gap; i < n; i++) {
                for(int j = i; j >= gap && arrayToSort[j].compareTo(arrayToSort[j-gap]) < 0; j -= gap) {
                    T value = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j-gap];
                    arrayToSort[j-gap] = value;
                }
            }
            gap = (gap - 1)/3;
        }
    }

    /**
     * Sorts an array of objects that implements Comparable interface using bubble sort.
     * 
     * @param <T>           an Object that implements Comparable
     * @param arrayToSort   an array to be sorted
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arrayToSort) {
        for(int i = 0; i < arrayToSort.length - 1; i++)
            for(int j = arrayToSort.length - 1; j > i + 1; j--)
                if(arrayToSort[j].compareTo(arrayToSort[j - 1]) < 0) {
                    T value = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j - 1];
                    arrayToSort[j - 1] = value;
                }
    }

    /**
     * Sorts an array of objects that implements Comparable interface using my idea
     * of traditional bubble sort.
     * 
     * @param <T>           an Object that implements Comparable
     * @param arrayToSort   an array to be sorted
     */
    public static <T extends Comparable<T>> void myBubbleSort(T[] arrayToSort) {
        boolean isNotSorted = true;
        while(isNotSorted) {
            isNotSorted = false;
            for(int i = 0; i < arrayToSort.length - 1; i++)
                if(arrayToSort[i].compareTo(arrayToSort[i + 1]) > 0) {
                    T value = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i + 1];
                    arrayToSort[i + 1] = value;
                    isNotSorted = true;
                }
        }
    }
    
    public static void main(String[] args) {
        int n = 10000; // array size
        Integer[] unsorted = new Integer[n];
        Integer[] unsorted2 = new Integer[n];
        Integer[] unsorted3 = new Integer[n];
        Integer[] unsorted4 = new Integer[n];
        Integer[] unsorted5 = new Integer[n];

        Random rand = new Random(); // add seed for reproducibility

        for(int i = 0; i < unsorted.length; i++) {
            unsorted[i] = unsorted2[i] = unsorted3[i] = unsorted4[i] = unsorted5[i] = rand.nextInt();
        }

        // System.out.println(Arrays.toString(unsorted));
        long start = System.nanoTime();
        Sort.insertionSort(unsorted);
        long end = System.nanoTime();
        // System.out.println(Arrays.toString(unsorted));
        System.out.printf("Insertion Sort Duration: \t%12.9f\n", ((end - start)/1e9));
        
        // System.out.println(Arrays.toString(unsorted2));
        start = System.nanoTime();
        Sort.mergeSort(unsorted2);
        end = System.nanoTime();
        // System.out.println(Arrays.toString(unsorted2));
        System.out.printf("Merge Sort Duration: \t\t%12.9f\n", ((end - start)/1e9));

        // System.out.println(Arrays.toString(unsorted3));
        start = System.nanoTime();
        Sort.shellSort(unsorted3);
        end = System.nanoTime();
        // System.out.println(Arrays.toString(unsorted3));
        System.out.printf("Shell Sort Duration: \t\t%12.9f\n", ((end - start)/1e9));

        // System.out.println(Arrays.toString(unsorted4));
        start = System.nanoTime();
        Sort.bubbleSort(unsorted4);
        end = System.nanoTime();
        // System.out.println(Arrays.toString(unsorted4));
        System.out.printf("Bubble Sort Duration: \t\t%12.9f\n", ((end - start)/1e9));

        // System.out.println(Arrays.toString(unsorted5));
        start = System.nanoTime();
        Sort.myBubbleSort(unsorted5);
        end = System.nanoTime();
        // System.out.println(Arrays.toString(unsorted5));
        System.out.printf("MyBubble Sort Duration: \t%12.9f\n", ((end - start)/1e9));
    }
}