class Solution {
    public int maximumSwap(int num) {
        // Convert the number to a character array to manipulate single digits
        char[] digits = String.valueOf(num).toCharArray();
        int length = digits.length;
        // Initialize an array to hold the indices of the 'max right' elements
        int[] maxRightIndex = new int[length];

        // Fill the array with the corresponding indices initially
        for (int i = 0; i < length; ++i) {
            maxRightIndex[i] = i;
        }

        // Populate the maxRightIndex array with the index of the greatest digit 
        // to the right of each position i, inclusive
        for (int i = length - 2; i >= 0; --i) {
            // Update the index only if the current digit is less than or equal to 
            // the maximum digit to the right
            if (digits[i] <= digits[maxRightIndex[i + 1]]) {
                maxRightIndex[i] = maxRightIndex[i + 1];
            }
        }

        // Iterate through each digit to find the first occurrence where the current
        // digit is less than the maximum digit to its right
        for (int i = 0; i < length; ++i) {
            int maxIndex = maxRightIndex[i];
            // If such a digit is found, swap it with the maximum digit to its right
            if (digits[i] < digits[maxIndex]) {
                char temp = digits[i];
                digits[i] = digits[maxIndex];
                digits[maxIndex] = temp;
                // Only the first such swap is needed for the maximum number, so break
                break;
            }
        }

        // Convert the modified character array back to an integer and return
        return Integer.parseInt(new String(digits));
    }
}
