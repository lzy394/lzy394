public class BinaryTree {
    private String val;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(String val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }
    public static String InOrderTraversal(BinaryTree T) {
        if(T==null) return null;
        return "("+InOrderTraversal(T.getLeft()) + T.getVal() + InOrderTraversal(T.getRight())+")";
    }

    public static String PostOrderTraversal(BinaryTree T) {
        if(T==null) return null;
        return PostOrderTraversal(T.getLeft()) + PostOrderTraversal(T.getRight())+T.getVal()+" ";
    }
}
