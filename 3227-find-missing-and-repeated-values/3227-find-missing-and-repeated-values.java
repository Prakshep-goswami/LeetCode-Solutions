class Solution {
    // Method to find the missing and repeated values in a grid.
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        // Calculate the size of the grid.
        int n = grid.length;

        // Initialize a count array to keep track of occurrences of each number.
        int[] count = new int[n * n + 1]; // +1 because we are using 1-based indexing.
      
        // Array to store the final answer: [repeated number, missing number].
        int[] answer = new int[2];
      
        // Iterate over the grid to count the occurrences of each number.
        for (int[] row : grid) {
            for (int num : row) {
                // Increment the count of the current number.
                count[num]++;
              
                // If a number appears twice, it is the repeated number.
                if (count[num] == 2) {
                    answer[0] = num;
                }
            }
        }
      
        // Look for the missing number in the count array.
        for (int i = 1; ; i++) {
            // If a number has never appeared, it is the missing number.
            if (count[i] == 0) {
                answer[1] = i;
                // Return the answer array with the repeated and missing numbers.
                return answer;
            }
        }
    }
}
