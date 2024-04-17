public class NaturalMergeSorter {

    /**
     * The method returns the number of array elements sorted in ascending order,
     * starting at startIndex and ending either at the end of the sorted run, or
     * the end of the array, whichever comes first.
     * <p>
     * The method returns 0 if startIndex is out of bounds.
     *
     * @param array       array
     * @param arrayLength length
     * @param startIndex  strat
     * @return the number of array elements sorted in ascending order
     */
    public int getSortedRunLength(int[] array, int arrayLength,
                                  int startIndex) {
        if (startIndex < 0 || startIndex >= arrayLength - 1)
            return 0;
        int ans = 1;
        for (int i = startIndex; i < arrayLength - 1; i++, ans++) {
            if( array[i] > array[i + 1])
                return ans;
        }
        return ans;
    }

    public void naturalMergeSort(int[] array, int arrayLength) {
        for (int i = 0; i < arrayLength; ) {
            int length = getSortedRunLength(array, arrayLength, i);

            if (length == 0) {
                // startIndex is out of bounds, return
                return;
            }

            if (i + length == arrayLength) {
                // First run ends at the array's end, reset i to 0
                i = 0;
                continue;
            }

            int secondRunLength = getSortedRunLength(array, arrayLength, i + length);

            // Merge the two runs
            merge(array, i, length, i + length + secondRunLength - 1);

            // Reassign i with the first index after the second run
            i = i + length + secondRunLength;
        }
    }

    public void merge(int[] numbers, int leftFirst, int leftLast,
                      int rightLast) {
        int mergedSize = rightLast - leftFirst + 1;
        int[] mergedNumbers = new int[mergedSize];
        int mergePos = 0;
        int leftPos = leftFirst;
        int rightPos = leftLast + 1;

        // Add smallest element from left or right partition to merged numbers
        while (leftPos <= leftLast && rightPos <= rightLast) {
            if (numbers[leftPos] <= numbers[rightPos]) {
                mergedNumbers[mergePos] = numbers[leftPos];
                leftPos++;
            } else {
                mergedNumbers[mergePos] = numbers[rightPos];
                rightPos++;
            }
            mergePos++;
        }

        // If left partition isn't empty, add remaining elements to mergedNumbers
        while (leftPos <= leftLast) {
            mergedNumbers[mergePos] = numbers[leftPos];
            leftPos++;
            mergePos++;
        }

        // If right partition isn't empty, add remaining elements to mergedNumbers
        while (rightPos <= rightLast) {
            mergedNumbers[mergePos] = numbers[rightPos];
            rightPos++;
            mergePos++;
        }

        // Copy merged numbers back to numbers
        for (mergePos = 0; mergePos < mergedSize; mergePos++) {
            numbers[leftFirst + mergePos] = mergedNumbers[mergePos];
        }

        // Free temporary array
        mergedNumbers = null;
    }
}