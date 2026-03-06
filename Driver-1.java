public class Driver {
    public static void main(String[] args) {
        // Test AVL Tree
        System.out.println("Testing AVL ");
        AVL<Integer> avl = new AVL<>();
        avl.insert(10);
        avl.insert(20);
        avl.insert(15);
        avl.insert(23);
        avl.insert(30);

        System.out.println("AVL Height: " + avl.height());
        System.out.println("Finding 15 height:");
        avl.heightAVL(15);
        avl.printAVL();
        System.out.println();

        System.out.println("Deleting 15:");
        avl.delete(15);
        avl.printAVL();
        System.out.println();
        System.out.println();

        // Test Splay Tree
        System.out.println("Splay ");
        Splay<Integer> splay = new Splay<>();
        splay.insert(10);
        splay.insert(20);
        splay.insert(15);
        splay.insert(23);
        splay.insert(30);

        System.out.println("Splay Height: " + splay.height());
        splay.printRoot();
        splay.printSplay();
        System.out.println();

        System.out.println("Deleting 15:");
        splay.delete(15);
        splay.printSplay();
        System.out.println();
        System.out.println();

        // Test Red Black Tree
        System.out.println("RB: ");
        RedBlack<Integer> rb = new RedBlack<>();
        rb.insert(10);
        rb.insert(20);
        rb.insert(15);
        rb.insert(23);
        rb.insert(30);

        System.out.println("Red Black Height: " + rb.height());
        rb.statusRB();
        rb.printRedBlack();
        System.out.println();

        System.out.println("Deleting 15:");
        rb.delete(15);
        rb.printRedBlack();
        rb.statusRB();
        System.out.println();
        System.out.println();

        // Test 2-3-4 Tree
        System.out.println("B234 ");
        B234<Integer> b234 = new B234<>();
        b234.insert(10);
        b234.insert(20);
        b234.insert(15);
        b234.insert(23);
        b234.insert(30);

        System.out.println("2-3-4 Height: " + b234.height());
        b234.status234();
        b234.print234();
        System.out.println();

        System.out.println("Deleting 15:");
        b234.delete(15);
        b234.print234();
        b234.status234();
        System.out.println();
    }
}
