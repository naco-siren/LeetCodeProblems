package leetcode.LC_105;

import base.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 */
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Construct a map of inorder's indices for O(1) access
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        /* Recursively build the tree */
        return buildNode(preorder, inorder, inorderMap, 0, preorder.length, 0, inorder.length);
    }

    /**
     * The "s" and "e" here is the same as substring(start included, end excluded), i.e. slicing
     */
    private TreeNode buildNode(
            int[] pre, int[] in, Map<Integer, Integer> inMap,
            int preS, int preE, int inS, int inE
    ) {
        /* Return if current sequence is empty */
        if (preS == preE)
            return null;

        /* Find the root element's position in the inorder traverse */
        final int val = pre[preS];

        /* Decide the lengths of left subarray and right subarray */
        final int mid = inMap.get(val);
        final int leftLen = mid - inS, rightLen = inE - (mid + 1);

        /* Recursion */
        final TreeNode node = new TreeNode(val);
        node.left = buildNode(pre, in, inMap, preS + 1, preS + 1 + leftLen, inS, mid);
        node.right = buildNode(pre, in, inMap, preS + 1 + leftLen, preE, mid + 1, inE);
        return node;
    }
}
