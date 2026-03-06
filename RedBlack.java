public class RedBlack<E extends Comparable<E>> implements BalanceTree<E> {
    private class Node {
        E item;
        Node leftChild;
        Node rightChild;
        Node parent;
        boolean isRed;


        Node(E item) { //new nodes start red
            this.item = item;
            this.isRed = true;
            this.parent = null;
        }
    }

    private Node root;

    public RedBlack() { //make empty tree
        root = null;
    }

    public void insert(E item) {
        root = insertHelper(root, item);
        root.isRed = false; // Root is always black
    }

    private Node insertHelper(Node node, E item) {
        if (node == null) {
            return new Node(item);
        }
        if (item.compareTo(node.item) < 0) {
            node.leftChild = insertHelper(node.leftChild, item);
            node.leftChild.parent = node;
        } else if (item.compareTo(node.item) > 0) {
            node.rightChild = insertHelper(node.rightChild, item);
            node.rightChild.parent = node;
        }
        return balanceInsert(node);
    }

    private Node balanceInsert(Node node) {
        if (isRedHelper(node.rightChild) && !isRedHelper(node.leftChild)) {
            node = rotateL(node); //rotate left when right child red and left is black
        }
        if (isRedHelper(node.leftChild) && isRedHelper(node.leftChild.leftChild)) {
            node = rotateR(node); //rotate right when left child/grandchild are red
        }
        if (isRedHelper(node.leftChild) && isRedHelper(node.rightChild)) {
            swapColors(node); //swap colors if both children red
        }
        return node;
    }


    private boolean isRedHelper(Node node) {
        return node != null && node.isRed;
    }
    private Node rotateL(Node node) {
        Node x = node.rightChild;
        node.rightChild = x.leftChild;
        if (x.leftChild != null){
            x.leftChild.parent = node;
        }
        x.leftChild = node;
        x.parent = node.parent;
        node.parent = x;
        return x;
    }

    private Node rotateR(Node node) {
        Node x = node.leftChild;
        node.leftChild = x.rightChild;
        if (x.rightChild != null){
            x.rightChild.parent = node;
        }
        x.rightChild = node;
        x.parent = node.parent;
        node.parent = x;
        return x;
    }

    private void swapColors(Node node) {
        node.isRed = !node.isRed;
        if (node.leftChild != null) node.leftChild.isRed = false;
        if (node.rightChild != null) node.rightChild.isRed = false;
    }
    public boolean find(E item) {
        return findHelper(root, item);
    }

    private boolean findHelper(Node node, E item) {
        if (node == null) {
            return false;
        }
        if (item.compareTo(node.item) < 0) {
            return findHelper(node.leftChild, item);
        } else if (item.compareTo(node.item) > 0) {
            return findHelper(node.rightChild, item);
        }
        else {
            return true;
        }
    }

    public void delete(E item) {
        root = deleteHelper(root, item);
        if (root != null) {// makes sure root is still black
            root.isRed = false;
        }
    }

    private Node deleteHelper(Node node, E item) {
        if (node == null) {
            return null;
        }
        if (item.compareTo(node.item) < 0) { //look for node to delete
            node.leftChild = deleteHelper(node.leftChild, item);
        } else if (item.compareTo(node.item) > 0) {
            node.rightChild = deleteHelper(node.rightChild, item);
        }
        else {
            if (node.leftChild == null || node.rightChild == null) {
                Node temp;
                if (node.leftChild != null) {
                    temp = node.leftChild;
                } else {
                    temp = node.rightChild;
                }
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            }
            else { // two children node
                Node temp = getMin(node.rightChild);
                node.item = temp.item;
                node.rightChild = deleteHelper(node.rightChild, temp.item);
            }
        }
        return balanceDelete(node);
    }

    private Node getMin(Node node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    private Node balanceDelete(Node node) {
        if (isRedHelper(node.leftChild)) {
            node = rotateR(node);
        }
        if (isRedHelper(node.rightChild)) {
            node = rotateL(node);
        }
        if (isRedHelper(node.leftChild) && isRedHelper(node.rightChild)) {
            swapColors(node);
        }
        return node;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(heightHelper(node.leftChild), heightHelper(node.rightChild));
        }
    }
    public void statusRB() {
        int black = countBlack(root);
        int blackHeight = calcBH(root);
        int red = countRed(root);
        System.out.println("R = " + red);
        System.out.print("B = " + black);
        System.out.print("BH = " + blackHeight);
    }

    private int countRed(Node node) {
        int count ;
        if (node == null) {
            return 0;
        }
        if (isRedHelper(node)) {
            count = 1;
        } else {
            count = 0;
        }
        count += countRed(node.leftChild);
        count += countRed(node.rightChild);
        return count;
    }

    private int countBlack(Node node) {
        int count;
        if (node == null) {
            return 0;
        }
        if (!isRedHelper(node)) {
            count = 1;
        } else {
            count = 0;
        }
        count += countBlack(node.leftChild);
        count += countBlack(node.rightChild);
        return count;
    }


    private int calcBH(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calcBH(node.leftChild);
        int rightHeight = calcBH(node.rightChild);

        int height;
        if (isRedHelper(node)) {
            height = 0;
        } else {
            height = 1;
        }

        return Math.max(leftHeight, rightHeight) + height;
    }

    public void printRedBlack() {
        printRedBlack(root);
    }

    private void printRedBlack(Node node) {
        if (node == null) {
            return;
        }
        printRedBlack(node.leftChild);

        String color;
        if (node.isRed) {
            color = "R";
        } else {
            color = "B";
        }
        System.out.print("(" + node.item + "," + color + ") ");
        printRedBlack(node.rightChild);
    }

}
