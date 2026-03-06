public class Splay<E extends Comparable<E>> implements BalanceTree<E>{

    private class Node {
        E item;
        Node leftChild;
        Node rightChild;

        Node(E item) {
            this.item = item;
        }
    }

    private Node root ;

    public Splay() {
        root = null;
    }// make tree


    public void insert(E item) {
        root = insert (root, item);
        root = splay(root, item);
    }

    private Node insert(Node node, E item) {
        if (node == null) {
            return new Node(item);
        }
        if (item.compareTo(node.item) < 0) { //left side
            node.leftChild = insert(node.leftChild, item);
        }
        else if (item.compareTo(node.item) > 0) { //right side
            node.rightChild = insert(node.rightChild, item);
        }

        return node;
    }

    public boolean find(E item) {
        root = splay(root, item);
        return root != null && root.item.compareTo(item) == 0;
    }

    private Node splay(Node node, E item) {
        if (node == null || item.compareTo(node.item) == 0) {
            return node; // found or null
        }

        if (item.compareTo(node.item) < 0) {
            if (node.leftChild == null) {
                return node;
            }

            if (item.compareTo(node.leftChild.item) < 0) {//zig-zig
                node.leftChild.leftChild = splay(node.leftChild.leftChild, item);
                node = rotateRight(node);
            } else if (item.compareTo(node.leftChild.item) > 0) {//zig-zag
                node.leftChild.rightChild = splay(node.leftChild.rightChild, item);
                if (node.leftChild.rightChild != null) {
                    node.leftChild = rotateLeft(node.leftChild);
                }

            }

            if (node.leftChild == null) { //rotation if null
                return node;
            } else {
                return rotateRight(node);
            }

        } else { //in right side of tree

            if (node.rightChild == null) {
                return node;
            }

            if (item.compareTo(node.rightChild.item) > 0) {//zag zag
                node.rightChild.rightChild = splay(node.rightChild.rightChild, item);
                node = rotateLeft(node);
            } else if (item.compareTo(node.rightChild.item) < 0) { //zag zig
                node.rightChild.leftChild = splay(node.rightChild.leftChild, item);

                if (node.rightChild.leftChild != null) {
                    node.rightChild = rotateRight(node.rightChild);
                }
            }
            if (node.rightChild == null) { //rotate if null
                return node;
            }
            else {
                return rotateLeft(node);
            }
        }
    }
    private Node rotateRight(Node node) { //zig
        Node leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;
        leftChild.rightChild = node;
        return leftChild;
    }

    private Node rotateLeft(Node node) { //zag
        Node rightChild = node.rightChild;
        node.rightChild = rightChild.leftChild;
        rightChild.leftChild = node;
        return rightChild;
    }

    public void delete(E item) {
        if (root == null) {
            return;
        }
        root = splay(root, item); //splay to root
        if (root.item.compareTo(item) != 0) {
            return;
        }
        if (root.leftChild != null) { //splay largest in left
            Node leftSubtree = root.leftChild;
            root = splay(leftSubtree, item);
            root.rightChild = leftSubtree.rightChild;
        } else {
            root = root.rightChild;
        }

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

    public void printRoot() {
        if (root == null) {
            System.out.println("Root is null");
        }
        else {
            System.out.println("Root is: " + root.item);
        }
    }
    public void printSplay() {
        printSplayHelper(root);
    }

    private void printSplayHelper(Node node) {
        if (node == null){
            return;
        }
        printSplayHelper(node.leftChild);
        System.out.print("(" + node.item + "," + heightHelper(node) + ") ");
        printSplayHelper(node.rightChild);

    }

}
