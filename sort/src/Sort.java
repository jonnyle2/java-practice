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

    // note: startIndex inclusive and endIndex exclusive
    private static <T extends Comparable<T>> void mergeSort(T[] arrayToSort, int startIndex, int endIndex) {
        if(startIndex + 1 < endIndex) {
            int centerIndex = (endIndex + startIndex)/2;
            mergeSort(arrayToSort, startIndex, centerIndex);  // sort left half
            mergeSort(arrayToSort, centerIndex, endIndex);    // sort right half
            merge(arrayToSort, startIndex, centerIndex, endIndex);    // merge two, sorted halves
        }
    }

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
     * Sorts an array of objects that implements Comparable interface using shell sort.
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
            for(int i = 0; i < n; i++) {
                int j = i + gap;
                if(j >= n) {
                    break;
                }
                T right = arrayToSort[j];
                if(arrayToSort[i].compareTo(right) > 0) {
                    arrayToSort[j] = arrayToSort[i];
                    arrayToSort[i] = right;
                }
            }
            gap = (gap - 1)/3;
        }
    }
    
    public static void main(String[] args) {
        int n = 10; // array size
        Integer[] unsorted = new Integer[n];
        Integer[] unsorted2 = new Integer[n];
        Integer[] unsorted3 = new Integer[n];

        Random rand = new Random(); // add seed for reproducibility

        for(int i = 0; i < unsorted.length; i++) {
            unsorted[i] = unsorted2[i] = unsorted3[i] = rand.nextInt(50)+1;
        }
        long start = System.nanoTime();
        Sort.insertionSort(unsorted);
        long end = System.nanoTime();
        System.out.printf("Insertion Sort Duration: \t%12.9f\n", ((end - start)/1e9));
        
        start = System.nanoTime();
        Sort.mergeSort(unsorted2);
        end = System.nanoTime();
        System.out.printf("Merge Sort Duration: \t\t%12.9f\n", ((end - start)/1e9));

        start = System.nanoTime();
        Sort.shellSort(unsorted3);
        end = System.nanoTime();
        System.out.printf("Shell Sort Duration: \t\t%12.9f\n", ((end - start)/1e9));
    }
}