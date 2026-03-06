public class B234<E extends Comparable<E>> implements BalanceTree<E>{
    private class Node {
        E[] keys;
        Node[] children;
        int size;
        boolean isLeaf;

        Node() {
            keys = (E[]) new Comparable[3];
            size = 0;
            isLeaf = true;
            children = (Node[]) new B234.Node[4];
        }
    }

    private Node root;

    public B234() {
        root = new Node();
    }

    public void insert(E key) {
        if (root.size == 3) { //split if full
            Node newRoot = new Node();
            newRoot.isLeaf = false;
            newRoot.children[0] = root;
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertHelper(root, key);
    }

    private void insertHelper(Node node, E item) {
        if (node.isLeaf) { //into leaf
            int i = node.size - 1;

            while (i >= 0 && item.compareTo(node.keys[i]) < 0) { // shift keys for space
                node.keys[i + 1] = node.keys[i];
                i = i - 1;
            }
            node.keys[i + 1] = item;
            node.size = node.size + 1;
        } else {
            int i = node.size - 1;
            while (i >= 0 && item.compareTo(node.keys[i]) < 0) {
                i = i - 1;
            }
            i = i + 1;

            if(node.children[i].size == 3) { //split first
                splitChild(node, i);
                if (item.compareTo(node.keys[i]) > 0) {
                    i = i + 1;
                }
            }

            insertHelper(node.children[i], item);
        }
    }

    private void splitChild(Node parent, int index) {
        Node fullChild = parent.children[index];
        Node newChild = new Node();

        newChild.isLeaf = fullChild.isLeaf;
        newChild.size = 1;
        newChild.keys[0] = fullChild.keys[2];

        if (!fullChild.isLeaf) {
            newChild.children[0] = fullChild.children[2];
            newChild.children[1] = fullChild.children[3];
        }

        fullChild.size = 1;


        for (int j = parent.size; j >= index + 1; j--) { //shift children for space
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[index + 1] = newChild;

        for (int j = parent.size - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[index] = fullChild.keys[1];
        parent.size = parent.size + 1;
    }


    public boolean find(E item) {
        return findHelper(root, item);
    }

    private boolean findHelper(Node node, E item) {
        int i = 0;
        while (i < node.size && item.compareTo(node.keys[i]) > 0) {
            i = i + 1;
        }
        if (i < node.size && item.compareTo(node.keys[i]) == 0) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        return findHelper(node.children[i], item);
    }

    public void delete(E item) {
        deleteHelper(root, item);
    }

    private void deleteHelper(Node node, E item) {
        if (node == null) return;

        int i = 0;
        while (i < node.size && item.compareTo(node.keys[i]) > 0) {
            i = i + 1;
        }

        if (i < node.size && item.compareTo(node.keys[i]) == 0) {
            if (node.isLeaf) { //delete key from leaf
                for (int j = i; j < node.size - 1; j++) {
                    node.keys[j] = node.keys[j + 1];
                }
                node.keys[node.size - 1] = null;
                node.size = node.size - 1;
            } else { // delete internal node
                Node successor = node.children[i + 1];
                while (successor.isLeaf == false) {
                    successor = successor.children[0];
                }
                E successorKey = successor.keys[0];
                node.keys[i] = successorKey;
                deleteHelper(node.children[i + 1], successorKey);
            }
        } else if (!node.isLeaf) {
            deleteHelper(node.children[i], item);
        }
    }

    public int height() {
        int height = 0;
        Node current = root;
        while (current != null && current.isLeaf == false) {
            current = current.children[0];
            height++;
        }
        return height;
    }

    public void status234() {
        int[] count = new int[3];
        countStatus(root, count);
        System.out.println(" Two = " + count[0] + " Three = " + count[1] + " Four = " + count[2]);
    }

    private void countStatus(Node node, int[] count) {
        if (node == null) return;
        if (node.size == 1) {
            count[0]++;
        } else {
            if (node.size == 2) {
                count[1]++;
            } else {
                if (node.size == 3) {
                    count[2]++;
                }
            }
        }
        if (!node.isLeaf) {
            for (int i = 0; i <= node.size; i++) {
                countStatus(node.children[i], count);
            }
        }
    }

    public void print234() {
        printTree(root);
        System.out.println();
    }

    private void printTree(Node node)
    {
        if (node != null)
        {
            int i = 0;
            while (i < node.size)
            {
                if (!node.isLeaf)
                {
                    printTree(node.children[i]);
                }

                System.out.print("(" + node.keys[i] + ", ");
                if (node.size == 1)
                {
                    System.out.print("two)");
                }
                else if (node.size == 2)
                {
                    System.out.print("three)");
                }
                else if (node.size == 3)
                {
                    System.out.print("four)");
                }
                System.out.print(" "); // space between nodes

                i = i + 1;
            }
            if (!node.isLeaf)
            {
                printTree(node.children[i]);
            }
        }
    }
}