public class myBST<E extends Comparable<E>> implements BinaryTree<E> {
    private class Node<E> { //create the nodes
        E data;

        Node<E> leftChild;
        Node<E> rightChild;

        Node(E data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
        }
    }

    private Node<E> root;
    public void insert(E item) {
         root = insertHelper(root, item);
    }

    private Node<E> insertHelper(Node<E> node, E item) {
        if (node == null) { //make new node iff null with the item
            return new Node<>(item);
        }
        else if (item.compareTo(node.data) < 0) { //go left if smaller, right if larger
              node.leftChild = insertHelper(node.leftChild, item);
        }
        else {
            node.rightChild = insertHelper(node.rightChild, item);
        }
         return node;
    }


    public void delete(E item) {
        root = deleteHelper(root, item);
    }

    private Node<E> deleteHelper(Node<E> node, E item) {
        if (node == null) { // not found
                return null;
        }

        else if (item.compareTo(node.data) < 0) { //search
              node.leftChild = deleteHelper(node.leftChild, item);
        }
        else if (item.compareTo(node.data) > 0) {
            node.rightChild = deleteHelper(node.rightChild, item);
        }
        else { //case 1: no left child, replace with right
                if (node.leftChild == null) {
                    return node.rightChild;
            }
                else if (node.rightChild == null) { //case 2:no right child, replace with left
                    return node.leftChild;
            }
                //case 3: use successor if both children found
                node.data = findMin(node.rightChild);
            node.rightChild = deleteHelper(node.rightChild, node.data);
        }
         return node;
    }

    private E findMin(Node<E> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
          return node.data;
    }
    public boolean find(E item) {
             return findHelper(root, item);
    }

    private boolean findHelper(Node<E> node, E item) {
        if (node == null){
            return false;
        }
        if (item.compareTo(node.data) == 0){
            return true;
        }
        if (item.compareTo(node.data) < 0) {
            return findHelper(node.leftChild, item);
        } else {
             return findHelper(node.rightChild, item);
        }
    }

    public int treeHeight() {
        return getHeight(root);
    }
    private int getHeight(Node<E> node) {
        if (node == null){ //empty tree height -1

            return -1;
        }
        return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
    }

    public int itemHeight(E item) {
        Node<E> node = findNode(root, item);
        return getHeight(node);
    }

    public int leftHeight(E item) {
        Node<E> node = findNode(root, item);
        if (node != null && node.leftChild != null) {
            return getHeight(node.leftChild);
        } else {
            return -1;
        }
    }

    public int rightHeight(E item) {
        Node<E> node = findNode(root, item);
        if (node != null && node.rightChild != null) {
            return getHeight(node.rightChild);
        } else {
            return -1;
        }
    }
    public boolean isLeaf(E item) {
        Node<E> node = findNode(root, item);
        if (node == null) {
            return false;
        }
        return node.leftChild == null && node.rightChild == null;
    }

    public int status(E item) {
        return getStatus(root, item, null);
    }
    private int getStatus(Node<E> node, E item, Node<E> parent) {
        if (node == null) {
            return -1;
        }
        if (item.compareTo(node.data) == 0) {
            if (parent == null) {
                return 0;
            }
            if (parent.leftChild == node) {
                return 1;
            } else {
                return 2;
            }
        }
        if (item.compareTo(node.data) < 0) {
            return getStatus(node.leftChild, item, node);
        } else {
            return getStatus(node.rightChild, item, node);
        }
    }

    private Node<E> findNode(Node<E> node, E item) {
        if (node == null || node.data.equals(item)) {
            return node;
        }
        if (item.compareTo(node.data) < 0) {
            return findNode(node.leftChild, item);
        } else {
            return findNode(node.rightChild, item);
        }
    }

    public void display() { // decsending order to display tree
        displayHelper(root);
        System.out.println();
    }

    private void displayHelper(Node<E> node) {
        if (node != null) {
            displayHelper(node.rightChild);
            System.out.print(node.data + " ");
            displayHelper(node.leftChild);
        }
    }
}
