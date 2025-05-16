package TreeHomework;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    static Map<TreeNode, Integer> map = new HashMap<>();



    public static int height(TreeNode node){
        // Если дерево пустое, то его высота = -1
        // Если листок, то высота = 0
        if (node == null) return -1;

        int rightHeight =  height(node.right);
        int leftHeight =  height(node.left);

        int res = 1 + Math.max(rightHeight, leftHeight);
        map.put(node, res);
        return res;
    }


    public static void printTreePreOrder(TreeNode node){
        if (node == null) return;
        System.out.println("Node" + node.value + " : height = " +  map.get(node));
        printTreePreOrder(node.left);
        printTreePreOrder(node.right);
    }
/*
                    1
                   / \
                  2   3
                 /   / \
                4   5   6
*/

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        height(root);

        printTreePreOrder(root);

    }

}

