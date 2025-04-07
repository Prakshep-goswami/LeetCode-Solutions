class Solution {
    public boolean canPartition(int[] nums) {
        // Calculate the sum of all array elements
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
      
        // If the sum is odd, it's not possible to partition the array into two subsets of equal sum
        if (sum % 2 != 0) {
            return false;
        }
      
        // Target sum for each subset is half of the total sum
        int targetSum = sum / 2;
      
        // Create a boolean array to store the subset sums achievable up to the targetSum
        boolean[] subsetSums = new boolean[targetSum + 1];
      
        // There's always one subset with sum 0, the empty set
        subsetSums[0] = true;
      
        // Check each number in the given array
        for (int num : nums) {
            // Traverse the subsetSums array in reverse to avoid using an element multiple times
            for (int j = targetSum; j >= num; j--) {
                // Update the subset sums that are achievable
                // If j-num is achievable, set j as achievable (because we're adding num to the subset)
                subsetSums[j] = subsetSums[j] || subsetSums[j - num];
            }
        }
      
        // The result is whether the targetSum is achievable as a subset sum
        return subsetSums[targetSum];
    }
}