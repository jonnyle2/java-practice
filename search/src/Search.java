public class Search {
    /**
     * Finds value in a sorted array using binary search.
     * 
     * @param <T>       an object that implements Comparable
     * @param array     the array to search through
     * @param value     the value to search for
     * @return          The method returns the index of the first occurence of value. If the value
     *                  is not found or the array is not sorted, the method returns the integer -1.
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T value) {
        // check if array is sorted simply by iterating
        for(int i = 0; i < array.length - 1; i++)
            if(array[i].compareTo(array[i + 1]) > 0)
                return -1;
        return binarySearch(array, 0, array.length - 1, value);
    }

    /**
     * Helper method for binarySearch(). 
     * 
     * @param <T>           an object that implements Comparable
     * @param array         the array to search through
     * @param startIndex    starting index of array
     * @param endIndex      ending index of array
     * @param value         the value to search for
     * @return              The method returns the index of the first occurence of value. If 
     *                      the value is not found in the array, the method returns the integer -1.
     */
    private static <T extends Comparable<T>> int binarySearch(T[] array, int startIndex, int endIndex, T value) {
        if(startIndex >= endIndex)
            if(array[startIndex].equals(value))
                return startIndex;
            else
                return -1;
        int middleIndex = (startIndex + endIndex)/2;
        int checkMiddle = array[middleIndex].compareTo(value);
        if(checkMiddle > 0)
            return binarySearch(array, startIndex, middleIndex - 1, value);
        else if(checkMiddle < 0)
            return binarySearch(array, middleIndex + 1, endIndex, value);
        else {
            while(middleIndex >= 1 && array[middleIndex - 1].equals(value))
                middleIndex--;
            return middleIndex;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] array = {"abc", "bcd", "bcdd", "efg", "xyz"};
        int result = binarySearch(array, "bcdd");
        if(result == -1)
            System.out.println("Array is not sorted or value can not be found.");
        else
            System.out.println("Index of first value: " + result + "\nValue at index: " + array[result]);
    }
}
