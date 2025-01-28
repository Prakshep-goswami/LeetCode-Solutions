class Solution {
  
    private int[][] grid; // The grid representing the pond.
    private int rows; // Number of rows in the pond grid.
    private int cols; // Number of columns in the pond grid.

    // This method calculates the maximum number of fish that can be found in a straight line.
    public int findMaxFish(int[][] grid) {
        rows = grid.length; // Assigns the number of rows of the grid.
        cols = grid[0].length; // Assigns the number of columns of the grid.
        this.grid = grid; // Stores the grid in the instance variable for easy access.
        int maxFishCount = 0; // Starts with zero as the maximum number of fish found.
      
        // Iterates through each cell in the grid.
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If the current cell contains fish, perform a DFS to find all connected fish.
                if (grid[i][j] > 0) {
                    maxFishCount = Math.max(maxFishCount, dfs(i, j));
                }
            }
        }
        // Return the largest group of connected fish found in the pond.
        return maxFishCount;
    }

    // This method performs a depth-first search (DFS) to find all connected fish starting from cell (i, j).
    private int dfs(int i, int j) {
        int fishCount = grid[i][j]; // Counts the fish at the current cell.
        grid[i][j] = 0; // Marks the current cell as "visited" by setting its fish count to zero.
        // Array to calculate adjacent cell coordinates (up, right, down, left).
        int[] directions = {-1, 0, 1, 0, -1};
      
        // Explore all four adjacent cells using the directions array.
        for (int k = 0; k < 4; ++k) {
            int x = i + directions[k]; // Row index of the adjacent cell.
            int y = j + directions[k + 1]; // Column index of the adjacent cell.
          
            // Check whether the adjacent cell is within grid bounds and contains fish.
            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] > 0) {
                fishCount += dfs(x, y); // Accumulate fish count and continue DFS.
            }
        }
        // Return the total count of fish connected to cell (i, j).
        return fishCount;
    }
}
