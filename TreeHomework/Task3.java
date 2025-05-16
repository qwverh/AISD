package TreeHomework;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1);
        root.children.add(new NTreeNode(2));
        root.children.add(new NTreeNode(3));
        root.children.add(new NTreeNode(4));

        root.children.get(0).children.add(new NTreeNode(5));
        root.children.get(2).children.add(new NTreeNode(6));
        root.children.get(2).children.add(new NTreeNode(7));

        System.out.println(printNTree(root));
    }

    /*
                    1
                  / | \
                 2  3  4
                /     / \
               5     6   7
*/


    public static String printNTree(NTreeNode node){
        if (node == null) return "NULL";

        StringBuffer res = new StringBuffer();
        res.append("(").append(node.value);

        if (node.children.isEmpty()){
            res.append(", NULL");
        }else{
            for (NTreeNode elem: node.children){
                res.append(", ").append(printNTree(elem));
            }
        }

        res.append(")");
        return res.toString();
    }

}