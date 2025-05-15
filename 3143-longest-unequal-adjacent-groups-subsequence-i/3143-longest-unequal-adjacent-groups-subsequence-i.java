class Solution 
{
    public List<String> getLongestSubsequence(String[] words, int[] groups) 
    {
        List<String> result = new ArrayList<>();

        // Step 1: Initialize result with the first word
        result.add(words[0]);

        // Step 2: Loop through the rest of the words
        for (int i = 1; i < words.length; i++) 
        {
            // Step 3: Check if current group is different from the previous
            if (groups[i] != groups[i - 1]) 
            {
                // Step 4: Add word to result if group is alternating
                result.add(words[i]);
            }
        }

        // Step 5: Return the final result
        return result;
    }
}
