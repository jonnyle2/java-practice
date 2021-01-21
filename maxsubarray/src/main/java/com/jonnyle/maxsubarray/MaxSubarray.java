package com.jonnyle.maxsubarray;

import java.util.Scanner;

/**
 * Maximum Subarray Problem from <i>Introductions to Algorithms, Third
 * Edition</i>, by Thomas H. Cormen, et al.
 */
public final class MaxSubarray {

    /**
     * Finds the maximum subarray of an array.
     * 
     * @param array an array to get maximum subarray from
     * @return  An array containing three elements: [start index, end
     *          index, value]. <br>
     *          The start and end index (inclusive) is the range to obtain maximum value
     *          and the value is the maximum value.
     */
    public static int[] getMaxSubarray(int[] array) {
        int[] subarray = new int[array.length - 1];
        for(int i = 0; i < subarray.length; i++)
            subarray[i] = array[i + 1] - array[i];
        int[] result = getMaxSubarray(subarray, 0, subarray.length - 1);
        result[1]++;
        return result;
    }

    /**
     * Helper method for getMaxSubarray()
     * 
     * @param subarray      subarray of values
     * @param startIndex    start index of subarray
     * @param endIndex      end index of subarray
     * @return  An array containing three elements: [start index, end
     *          index, value]. <br>
     *          The start and end index is the range to obtain maximum value
     *          and the value is the maximum value.
     *      
     */
    private static int[] getMaxSubarray(int[] subarray, int startIndex, int endIndex) {
        if(startIndex == endIndex)
            return new int[] {startIndex, endIndex, subarray[startIndex]};
        else {
            int middleIndex = (endIndex + startIndex)/2;
            int[] left = getMaxSubarray(subarray, startIndex, middleIndex);
            int[] right = getMaxSubarray(subarray, middleIndex + 1, endIndex);
            int[] middle = getMaxCross(subarray, startIndex, middleIndex, endIndex);
            if(left[2] >= right[2] && left[2] >= middle[2])
                return left;
            else if(right[2] >= left[2] && right[2] >= middle[2])
                return right;
            else
                return middle;
        }
    }

    /**
     * Helper method for getMaxSubarray() that calculates the maximum,
     * middle crossing subarray.
     * 
     * @param subarray      subarray of values
     * @param startIndex    start index of subarray
     * @param middleIndex   middle index of subarray
     * @param endIndex      end index of subarray
     * @return  An array containing three elements: [start index, end
     *          index, value]. <br>
     *          The start and end index is the range to obtain maximum value
     *          and the value is the maximum value.
     */
    private static int[] getMaxCross(int[] subarray, int startIndex, int middleIndex, int endIndex) {
        int[] middle = new int[3];
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = middleIndex; i >= startIndex; i--) {
            sum += subarray[i];
            if(sum > leftSum) {
                leftSum = sum;
                middle[0] = i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for(int i = middleIndex + 1; i <= endIndex; i++) {
            sum += subarray[i];
            if(sum > rightSum) {
                rightSum = sum;
                middle[1] = i;
            }
        }
        middle[2] = leftSum + rightSum;
        return middle;
    }

    /**
     * Finds the maximum subarray of an array interactively 
     * in the command-line. The program accepts a comma 
     * separated list of integers.
     * 
     */
    public static void main(String[] args) {
        String listRegex = "\\d+,(\\d+,)+\\d+";

        System.out.print("Enter a comma separated list of numbers: ");
        try(Scanner input = new Scanner(System.in)) {
            String line = input.nextLine();
            while(!line.matches(listRegex)){
                System.out.print("Please enter a valid list.\nEnter a comma separated list of numbers: ");
                line = input.nextLine();
            }
            String[] strArray = line.split(",");
            int[] array = new int[strArray.length];
            for(int i = 0; i < array.length; i++)
                array[i] = Integer.parseInt(strArray[i]);
            int[] result = MaxSubarray.getMaxSubarray(array);
            System.out.printf("Value: %d | Start index: %d | End index: %d\n", result[2], result[0], result[1]);
        } catch(IllegalStateException e) {
            System.out.println("System.in is closed.");
            System.exit(1);
        } catch (NumberFormatException e){
            System.out.println("Integer is too large to store as primitive int.");
            System.exit(1);
        }
    }
}
