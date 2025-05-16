package TreeHomework;

public class Task2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);


        String s = printTree(root);
        System.out.println(s);
    }
/*
                    1
                   / \
                  2   3
                 /   / \
                4   5   6
*/

    public static String printTree(TreeNode node){
        if (node == null) return "NULL";

        return "(" + node.value + ", " + printTree(node.left) + ", " + printTree(node.right) + ")";
    }
}