public class Driver {
    public static void main(String[] args) {
        System.out.println("Welcome to my demo of Project 1:");

        myBST<String> bst = new myBST<>();
        bst.insert("dog");
        bst.insert("cat");
        bst.insert("pig");
        bst.insert("horse");

        System.out.println("The height of the BST is: " + bst.treeHeight());
        System.out.println("pig is present: " + bst.find("pig"));
        System.out.println("pig status is: " + bst.status("pig"));
        System.out.println("pig is in a leaf node: " + bst.isLeaf("pig"));
        System.out.println("pig height is: " + bst.itemHeight("pig"));
        System.out.println("horse is in a leaf node: " + bst.isLeaf("horse"));
        System.out.println("horse height is: " + bst.itemHeight("horse"));

        bst.delete("pig");
        System.out.println("pig is present: " + bst.find("pig"));

        bst.insert("mango");
        bst.insert("apple");
        bst.insert("mangrove");
        bst.insert("igloo");

        bst.display();
        System.out.println("The height of the BST is: " + bst.treeHeight());
        System.out.println("horse height is: " + bst.itemHeight("horse"));
        System.out.println("The height of the left subtree of horse is: " + bst.leftHeight("horse"));
        System.out.println("The height of the right subtree of horse is: " + bst.rightHeight("horse"));
    }
}
