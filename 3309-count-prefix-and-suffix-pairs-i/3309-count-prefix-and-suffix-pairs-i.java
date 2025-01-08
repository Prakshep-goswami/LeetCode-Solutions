class Solution {
    // Method to count the number of pairs where one word is both a prefix and a suffix of another word.
    public int countPrefixSuffixPairs(String[] words) {
        int pairCount = 0; // Initialize counter for pairs to 0.
        int wordCount = words.length; // Store the length of the words array.
      
        // Iterate over all words in the array using two nested loops to consider pairs.
        for (int i = 0; i < wordCount; ++i) {
            String currentWord = words[i]; // The current word for prefix/suffix checking
          
            // Iterate over the words following the current word to avoid duplicate pairs.
            for (int j = i + 1; j < wordCount; ++j) {
                String comparisonWord = words[j]; // Word to compare with the current word
              
                // Check if the comparison word starts with and ends with the current word.
                if (comparisonWord.startsWith(currentWord) && comparisonWord.endsWith(currentWord)) {
                    pairCount++; // Increment the number of valid pairs if conditions are met.
                }
            }
        }
      
        return pairCount; // Return the final count of valid pairs.
    }
}