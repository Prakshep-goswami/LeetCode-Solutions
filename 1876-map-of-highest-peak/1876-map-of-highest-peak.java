class Solution {
    public int[][] highestPeak(int[][] isWater) {
        // Obtain dimensions of the input matrix.
        int rows = isWater.length;
        int cols = isWater[0].length;
      
        // Initialize the answer matrix with the same dimensions.
        int[][] highestPeaks = new int[rows][cols];
      
        // Queue for BFS (Breadth-first search).
        Deque<int[]> queue = new ArrayDeque<>();
      
        // Initialize answer matrix and enqueue all water cells.
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // Mark water cells with 0 and land cells with -1
                highestPeaks[i][j] = isWater[i][j] - 1;
              
                // Add water cell coordinates to the queue.
                if (highestPeaks[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
      
        // Directions for exploring adjacent cells (up, right, down, left).
        int[] directions = {-1, 0, 1, 0, -1};
      
        // Perform BFS to find the highest peak values.
        while (!queue.isEmpty()) {
            // Poll a cell from the queue.
            int[] position = queue.poll();
            int row = position[0];
            int col = position[1];
          
            // Explore all adjacent cells.
            for (int k = 0; k < 4; ++k) {
                // Calculate coordinates of the adjacent cell.
                int x = row + directions[k];
                int y = col + directions[k + 1];
              
                // Check if the adjacent cell is within bounds and if it is land.
                if (x >= 0 && x < rows && y >= 0 && y < cols && highestPeaks[x][y] == -1) {
                    // Set the height of the land cell to be one more than the current cell.
                    highestPeaks[x][y] = highestPeaks[row][col] + 1;
                  
                    // Enqueue the position of the land cell.
                    queue.offer(new int[] {x, y});
                }
            }
        }
      
        // Return the filled highestPeaks matrix.
        return highestPeaks;
    }
}