class Solution {

    // Method that finds if the searchWord is a prefix of any word in the sentence.
    // If it is, returns the position (1-indexed) of the first occurrence. If not, returns -1.
    public int isPrefixOfWord(String sentence, String searchWord) {
        // Split the sentence into an array of individual words.
        String[] words = sentence.split(" ");
      
        // Iterate through each word in the array.
        for (int i = 0; i < words.length; i++) {
            // Check if the current word starts with the searchWord.
            if (words[i].startsWith(searchWord)) {
                // If it does, return the position of the word in the sentence, noting that index is 1-based.
                return i + 1;
            }
        }
        // If no word in the sentence is prefixed by searchWord, return -1.
        return -1;
    }
}