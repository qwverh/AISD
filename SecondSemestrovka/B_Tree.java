package SecondSemestrovka;

import java.util.ArrayList;
import java.util.List;

class Node {
    int[] keys;
    int T;
    Node[] children;
    int countKeys;
    boolean leaf;

    public Node(int T, boolean leaf) {
        this.T = T;
        this.leaf = leaf;
        keys = new int[2 * T - 1];
        children = new Node[2 * T];
        countKeys = 0;
    }

    protected Node find(int key) {
        int i;
        for (i = 0; i < countKeys; i++) {
            if (key == keys[i]) {
                return this;
            }
            if (keys[i] > key) {
                break;
            }
        }
        if (leaf) {
            return null;
        }
        return children[i].find(key);
    }

    protected void insertNonFull(int key) {
        int i = countKeys - 1;
        if (leaf) {
            while (i >= 0 && key < keys[i]) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            countKeys++;
        } else {
            while (i >= 0 && key < keys[i])
                i--;
            i++;

            if (children[i].countKeys == 2 * T - 1) {
                splitChild(i, children[i]);
                if (key > keys[i])
                    i++;
            }
            children[i].insertNonFull(key);
        }
    }

    protected void splitChild(int i, Node y) {
        Node z = new Node(y.T, y.leaf);
        z.countKeys = T - 1;

        for (int j = 0; j < T - 1; j++) {
            z.keys[j] = y.keys[j + T];
        }

        if (!y.leaf) {
            for (int j = 0; j < T; j++) {
                z.children[j] = y.children[j + T];
            }
            Node[] yChildren = new Node[2*T];
            for (int j = 0; j < T; j++) {
                yChildren[j] = y.children[j];
            }
            y.children = yChildren;
        }

        y.countKeys = T - 1;

        for (int j = countKeys; j >= i + 1; j--) {
            children[j + 1] = children[j];
        }
        children[i + 1] = z;

        for (int j = countKeys - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[T - 1];

        int[] yKeys = new int[2*T - 1];
        for (int j = 0; j < T - 1; j++) {
            yKeys[j] = y.keys[j];
        }
        y.keys = yKeys;
        countKeys++;
    }

    public void remove(int key) {
        int currentIndex = findKey(key);

        if (currentIndex < countKeys && keys[currentIndex] == key) {
            if (leaf) {removeFromLeaf(currentIndex);
            } else {removeFromNonLeaf(currentIndex);}
        } else {
            if (leaf) {return;}
            boolean flag = (currentIndex == countKeys);

            if (children[currentIndex].countKeys < T) {
                fill(currentIndex);
            }
            if (flag && currentIndex > countKeys) {
                children[currentIndex - 1].remove(key);
            } else {children[currentIndex].remove(key);}
        }
    }

    private int findKey(int key) {
        int currentIndex = 0;
        while (currentIndex < countKeys && keys[currentIndex] < key) {
            ++currentIndex;
        }
        return currentIndex;
    }

    private void removeFromLeaf(int currentIndex) {
        for (int i = currentIndex + 1; i < countKeys; i++) {
            keys[i - 1] = keys[i];
        }
        countKeys--;
    }

    private void removeFromNonLeaf(int currentIndex) {
        int k = keys[currentIndex];

        if (children[currentIndex].countKeys >= T) {
            int pred = getPred(currentIndex);
            keys[currentIndex] = pred;
            children[currentIndex].remove(pred);
        } else if (children[currentIndex + 1].countKeys >= T) {
            int succ = getSucc(currentIndex);
            keys[currentIndex] = succ;
            children[currentIndex + 1].remove(succ);
        } else {
            merge(currentIndex);
            children[currentIndex].remove(k);
        }
    }

    private int getPred(int currentIndex) {
        Node current = children[currentIndex];
        while (!current.leaf) {
            current = current.children[current.countKeys];
        }
        return current.keys[current.countKeys - 1];
    }

    private int getSucc(int currentIndex) {
        Node current = children[currentIndex + 1];
        while (!current.leaf) {
            current = current.children[0];
        }
        return current.keys[0];
    }

    private void fill(int currentIndex) {
        if (currentIndex != 0 && children[currentIndex - 1].countKeys >= T) {
            borrowFromPrev(currentIndex);
        } else if (currentIndex != countKeys && children[currentIndex + 1].countKeys >= T) {
            borrowFromNext(currentIndex);
        } else {
            if (currentIndex != countKeys) {
                merge(currentIndex);
            } else {
                merge(currentIndex - 1);
            }
        }
    }

    private void borrowFromPrev(int currentIndex) {
        Node child = children[currentIndex];
        Node sibling = children[currentIndex - 1];

        for (int i = child.countKeys - 1; i >= 0; --i) {
            child.keys[i + 1] = child.keys[i];
        }
        if (!child.leaf) {
            for (int i = child.countKeys; i >= 0; --i) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.keys[0] = keys[currentIndex - 1];

        if (!child.leaf) {
            child.children[0] = sibling.children[sibling.countKeys];
        }

        keys[currentIndex - 1] = sibling.keys[sibling.countKeys - 1];

        child.countKeys++;
        sibling.countKeys--;
    }

    private void borrowFromNext(int currentIndex) {
        Node child = children[currentIndex];
        Node sibling = children[currentIndex + 1];

        child.keys[child.countKeys] = keys[currentIndex];

        if (!child.leaf) {
            child.children[child.countKeys + 1] = sibling.children[0];
        }

        keys[currentIndex] = sibling.keys[0];

        for (int i = 1; i < sibling.countKeys; ++i) {
            sibling.keys[i - 1] = sibling.keys[i];
        }
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.countKeys; ++i) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        child.countKeys++;
        sibling.countKeys--;
    }

    private void merge(int currentIndex) {
        Node child = children[currentIndex];
        Node sibling = children[currentIndex + 1];

        child.keys[T - 1] = keys[currentIndex];

        for (int i = 0; i < sibling.countKeys; ++i) {
            child.keys[i + T] = sibling.keys[i];
        }

        if (!child.leaf) {
            for (int i = 0; i <= sibling.countKeys; ++i) {
                child.children[i + T] = sibling.children[i];
            }
        }

        for (int i = currentIndex + 1; i < countKeys; ++i) {
            keys[i - 1] = keys[i];
        }
        for (int i = currentIndex + 2; i <= countKeys; ++i) {
            children[i - 1] = children[i];
        }

        child.countKeys += sibling.countKeys + 1;
        countKeys--;
    }

    protected static void inOrderTraversal(Node node, List<Integer> keysList) {
        if (!node.leaf) {
            inOrderTraversal(node.children[0], keysList);
        }
        for (int i = 0; i < node.countKeys; i++) {
            keysList.add(node.keys[i]);
            if (!node.leaf) {
                inOrderTraversal(node.children[i + 1], keysList);
            }
        }
    }
}

public class B_Tree {

    private Node root;
    private final int T;

    public B_Tree(int T) {
        this.T = T;
    }

    public Node search(int key) {
        return (root == null) ? null : root.find(key);
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(T, true);
            root.keys[0] = key;
            root.countKeys = 1;
        } else {
            if (root.countKeys == 2 * T - 1) {
                Node newRoot = new Node(T, false);
                newRoot.children[0] = root;
                newRoot.splitChild(0, root);

                int i = 0;

                if (newRoot.keys[0] < key)
                    i++;

                newRoot.children[i].insertNonFull(key);
                root = newRoot;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    public void delete(int key) {
        if (root == null) {
            return;
        }

        root.remove(key);

        if (root.countKeys == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }

    public List<Integer> getAllKeysInOrder() {
        List<Integer> keysList = new ArrayList<>();
        if (root != null) {
            Node.inOrderTraversal(root, keysList);
        }
        return keysList;
    }
}