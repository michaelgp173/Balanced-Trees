public class AVL<E extends Comparable<E>> implements BalanceTree<E>{


    private class Node {
        E item;
        Node leftChild;
        Node rightChild;

        int height;
        Node(E item) {
            this.item = item;
            this.height = 1;
        }
    }

    private Node root;
    public AVL() { //start tree
        root = null;
    }
    public void insert(E item) {
        root = insertHelper(root, item);
    }

    private Node insertHelper(Node node, E item) {
        if (node == null) return new Node(item);

        if (item.compareTo(node.item) < 0) {
            node.leftChild =insertHelper(node.leftChild, item); //insert to the left
        }
        else if (item.compareTo(node.item) > 0) {
            node.rightChild= insertHelper(node.rightChild, item); //insert to the right
        } else {
            return node; //no dupes
        }

        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return balance(node);
    }


    private Node rotateL(Node x) {
        Node y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        //update heights
        x.height = Math.max(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = Math.max(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        return y; //new root
    }

    private Node rotateR(Node y) {
        Node x = y.leftChild;
        y.leftChild = x.rightChild;
        x.rightChild = y;
        //update heights
        y.height = Math.max(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        x.height = Math.max(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        return x; // new root
    }
    private Node balance(Node node) {
        int BF = getBalance(node);
        if (BF > 1) { //left balance
            if (getBalance(node.leftChild) < 0) // l-r
            {
                node.leftChild = rotateL(node.leftChild);
            } //l-l
            node = rotateR(node);
        } else if (BF < -1) { //right side
            if (getBalance(node.rightChild) > 0) { //r-l
                node.rightChild = rotateR(node.rightChild);
            } //r-r
            node = rotateL(node);

        }
        return node;
    }
    private int getBalance(Node node) {
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    public boolean find(E item) {
        return findHelper(root, item);
    }
    private boolean findHelper(Node node, E item) {
        if (item.compareTo(node.item) < 0) {
            return findHelper(node.leftChild, item);
        } //search left
        else if (item.compareTo(node.item) > 0) {// search right
            return findHelper(node.rightChild, item);
        } else {
            return true;
        }
    }

    public void delete(E item) {
        root = deleteHelper(root, item);
    }

    private Node deleteHelper(Node node, E item) {
        if (item.compareTo(node.item) < 0) {
            node.leftChild = deleteHelper(node.leftChild, item); //delete from left
        } else if (item.compareTo(node.item) > 0) {
            node.rightChild = deleteHelper(node.rightChild, item); // delete from right
        }
        else { //one or no child case
            if (node.leftChild == null || node.rightChild == null) {
                if (node.leftChild != null) {
                    node = node.leftChild;
                } else {
                    node = node.rightChild;
                }
            } else { //two children case
                Node minNode = findMin(node.rightChild);
                node.item = minNode.item;
                node.rightChild = deleteHelper(node.rightChild, minNode.item);
            }
        }

        if (node == null) {
            return node;
        }
        //update height
        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return balance(node);
    }

    private Node findMin(Node node) {
        while (node.leftChild != null) node = node.leftChild;
        return node;
    }

    public int height() {
        return getHeight(root);
    }


    public void heightAVL(E item) {
        Node node = findNode(root, item);

        if (node != null) {
            System.out.println("node height = " + node.height);
        }
    }

    private Node findNode(Node node, E item) {
        if (item.compareTo(node.item) < 0) {
            return findNode(node.leftChild, item);
        } else if (item.compareTo(node.item) > 0) {
            return findNode(node.rightChild, item);
        } else {
            return node;
        }
    }

    public void printAVL() {
        printAVL(root);
    }

    private void printAVL(Node node) {
        if (node == null){
            return;
        }
        printAVL(node.leftChild);
        System.out.print("(" + node.item + "," + getBalance(node) + ") ");
        printAVL(node.rightChild);
    }
}
