import java.util.HashMap;
import java.util.Map;

// Definition for a binary tree node.
class TreeNode {
    int val;                // Node's value.
    TreeNode left;          // Pointer to the left child node.
    TreeNode right;         // Pointer to the right child node.

    // Constructor with no parameters initializes val to 0 and all pointers to null.
    TreeNode() {
        val = 0;
        left = null;
        right = null;
    }

    // Constructor with the node's value that initializes all pointers to null.
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }

    // Constructor with the node's value and pointers to the left and right child nodes.
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // Map to store the index of each value in postorder traversal.
    private Map<Integer, Integer> postOrderIndexMap = new HashMap<>();

    // Function that constructs the binary tree from preorder and postorder traversal arrays.
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Fill the map with the postorder values and their indices.
        for (int i = 0; i < postorder.length; i++) {
            postOrderIndexMap.put(postorder[i], i);
        }
        // Call the recursive build function to construct the tree.
        return buildTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    // Helper function to recursively build the binary tree from preorder and postorder traversal subsets.
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] postorder, int postStart, int postEnd) {
        // If there are no elements to construct the tree, return null.
        if (preStart > preEnd) return null;

        // The first value in preorder is the root node value.
        TreeNode root = new TreeNode(preorder[preStart]);

        // If there is only one node, it is the root of the current subtree.
        if (preStart == preEnd) return root;

        // Find the index of the root of the left subtree in postorder traversal using the map.
        int leftRootIndex = postOrderIndexMap.get(preorder[preStart + 1]);

        // The length of the left subtree in the postorder array can be calculated from the indices.
        int leftSubtreeLength = leftRootIndex - postStart + 1;

        // Recursively construct the left subtree.
        root.left = buildTree(preorder, preStart + 1, preStart + leftSubtreeLength,
                              postorder, postStart, leftRootIndex);

        // Recursively construct the right subtree.
        root.right = buildTree(preorder, preStart + leftSubtreeLength + 1, preEnd,
                               postorder, leftRootIndex + 1, postEnd - 1);

        // Return the root of the constructed subtree.
        return root;
    }
}