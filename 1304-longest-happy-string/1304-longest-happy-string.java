class Solution {
    public String longestDiverseString(int a, int b, int c) {
        // Priority queue to store characters and their respective frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[1] - x[1]);
      
        // Add 'a', 'b', and 'c' to the priority queue with their frequencies if they are greater than 0
        if (a > 0) {
            maxHeap.offer(new int[] {'a', a});
        }
        if (b > 0) {
            maxHeap.offer(new int[] {'b', b});
        }
        if (c > 0) {
            maxHeap.offer(new int[] {'c', c});
        }

        // StringBuilder to build the result string
        StringBuilder result = new StringBuilder();
      
        // Construct the string by adding characters from the priority queue
        while (!maxHeap.isEmpty()) {
            // Poll the character with the highest frequency
            int[] current = maxHeap.poll();
            int length = result.length();
            // Check if the last two characters are the same as the current character
            if (length >= 2 && result.charAt(length - 1) == current[0] && result.charAt(length - 2) == current[0]) {
                // If the priority queue is empty, we can't add more characters and need to break the loop
                if (maxHeap.isEmpty()) {
                    break;
                }
                // Otherwise, poll the next character to avoid three consecutive characters being the same
                int[] next = maxHeap.poll();
                result.append((char) next[0]);
                // If there's more than one of the next character, decrement the count and offer it back to the queue
                if (next[1] > 1) {
                    next[1]--;
                    maxHeap.offer(next);
                }
                // Offer the current character back to the queue for future consideration
                maxHeap.offer(current);
            } else {
                // If there is no problem with three consecutive characters, append the current character
                result.append((char) current[0]);
                // Decrement the count and offer it back to the queue if there's more left
                if (current[1] > 1) {
                    current[1]--;
                    maxHeap.offer(current);
                }
            }
        }

        // Convert the StringBuilder to String and return the result
        return result.toString();
    }
}