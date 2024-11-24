class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0; // Initialize a sum variable to hold the total sum of matrix elements
        int negativeCount = 0; // Counter for the number of negative elements in the matrix
        int minAbsValue = Integer.MAX_VALUE; // Initialize to the maximum possible value to track the smallest absolute value seen

        // Loop through each row of the matrix
        for (int[] row : matrix) {
            // Loop through each value in the row
            for (int value : row) {
                sum += Math.abs(value); // Add the absolute value of the element to the sum
                // Find the smallest absolute value in the matrix
                minAbsValue = Math.min(minAbsValue, Math.abs(value));
                // If the element is negative, increment the negativeCount
                if (value < 0) {
                    negativeCount++;
                }
            }
        }

        // If the count of negative numbers is even or there's at least one zero, return the sum of absolute values
        if (negativeCount % 2 == 0 || minAbsValue == 0) {
            return sum;
        }
      
        // Since the negative count is odd, we subtract twice the smallest absolute value to maximize the matrix sum
        return sum - (minAbsValue * 2);
    }
}