class Solution {
    private List<Integer>[] graph; // The graph represented as an adjacency list
    private List<Integer> componentNodes = new ArrayList<>(); // List to store nodes of the currently visited component
    private boolean[] visited; // Array to track visited nodes
    private int totalNodes; // Total number of nodes in the graph

    // Method to compute the magnificent sets value for the given graph
    public int magnificentSets(int n, int[][] edges) {
        totalNodes = n;
        graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>()); // Initialize adjacency list for each node
        for (int[] edge : edges) { // Build the graph
            int nodeA = edge[0], nodeB = edge[1];
            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }

        visited = new boolean[n + 1];
        int totalMagnificentSets = 0;
        for (int i = 1; i <= n; ++i) {
            if (!visited[i]) {
                dfs(i); // Perform Depth-First Search to find all nodes in the component
                int largestDepth = -1;
                // For each node in the component, use BFS to find the largest depth
                for (int node : componentNodes) {
                    largestDepth = Math.max(largestDepth, bfs(node));
                }
                if (largestDepth == -1) {
                    return -1; // If it's not a magnificent set, return -1
                }
                totalMagnificentSets += largestDepth; // Add the largest depth to the total
                componentNodes.clear(); // Clear nodes list for next component
            }
        }
        return totalMagnificentSets; // Return the total magnificent sets of all components
    }

    // Helper method for BFS to calculate the largest depth of the BFS tree from the starting node
    private int bfs(int startNode) {
        int[] depth = new int[totalNodes + 1];
        Arrays.fill(depth, Integer.MAX_VALUE); // Set initial depth to a high value
        depth[startNode] = 1; // Depth of start node is 1
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode); // Initialize the queue with the starting node

        int maxDepth = 1; // Track the maximum depth
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbor : graph[currentNode]) {
                if (depth[neighbor] == Integer.MAX_VALUE) {
                    depth[neighbor] = depth[currentNode] + 1; // Update the depth of the neighbor
                    maxDepth = depth[neighbor]; // Update the max depth
                    queue.offer(neighbor); // Add the neighbor node to the queue
                }
            }
        }

        // Update the depth for any node that hasn't been reached by BFS
        for (int node : componentNodes) {
            if (depth[node] == Integer.MAX_VALUE) {
                depth[node] = ++maxDepth;
            }
        }

        // Verify that all edges in the component have depths differing by 1
        for (int node : componentNodes) {
            for (int neighbor : graph[node]) {
                if (Math.abs(depth[node] - depth[neighbor]) != 1) {
                    return -1; // This component isn't magnificent
                }
            }
        }

        return maxDepth; // Return the largest depth in the BFS tree
    }

    // Helper method for DFS to traverse all nodes in a connected component
    private void dfs(int currentNode) {
        componentNodes.add(currentNode); // Add current node to component list
        visited[currentNode] = true; // Mark current node as visited
        for (int neighbor : graph[currentNode]) { // Visit all unvisited neighbors
            if (!visited[neighbor]) {
                dfs(neighbor); // Recursively visit neighbors
            }
        }
    }
}