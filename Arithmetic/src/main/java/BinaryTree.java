public class BinaryTree {
    private String val;
    private BinaryTree left;
    private BinaryTree right;
    private static final String []Symbol={"+","-","×","÷"};
    public BinaryTree() {//默认构造函数
        this.val = null;
        this.left = null;
        this.right = null;
    }

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
    public static String InOrderTraversal(BinaryTree T) {//中序遍历
        if(T==null) return "";
        if(T.getLeft()==null && T.getRight()==null)
            return T.getVal();
        return "("+InOrderTraversal(T.getLeft()) + T.getVal() + InOrderTraversal(T.getRight())+")";
    }

    public static String PostOrderTraversal(BinaryTree T) {//后序遍历
        if(T==null) return "";
        return PostOrderTraversal(T.getLeft()) + PostOrderTraversal(T.getRight())+T.getVal()+" ";
    }

    public static void Swap(BinaryTree T){//交换左右子树
        if(T.left!=null && T.right!=null){
            BinaryTree temp=T.left;
            T.left=T.right;
            T.right=temp;
        }
    }

    public static BinaryTree RandomBinaryTree(int n,int max) {//随机生成二叉树
        if(n==0) return null;
        int num=2*n+1;
        BinaryTree []Node=new BinaryTree[num];
        for(int i=0;i<num;i++){
            if(i<n) {
                int r = (int) (Math.random() * 4);
                Node[i] = new BinaryTree(Symbol[r]);
            }
            else{
                Node[i]=new BinaryTree(Fraction.RandomFraction(max).toString());
            }
        }
        for(int i=0;i<n;i++){
            if(2*i+1<num) Node[i].setLeft(Node[2*i+1]);
            if(2*i+2<num) Node[i].setRight(Node[2*i+2]);
        }
        for(int i=n-1;i>=0;i--){
            if(Node[i].getVal().equals("-")) {
                if (Fraction.isNegative(getResult(Node[i]))) {
                    Swap(Node[i]);
                }
            }
            if(Node[i].getVal().equals("÷")) {
                if(getResult(Node[i].getRight()).toString().equals("0"))
                    RandomBinaryTree(n,max);
            }
        }
        System.out.println(getResult(Node[0]));
        return Node[0];
    }

    private static Fraction getResult(BinaryTree T){
        if(T==null) return null;
        if(T.getLeft()==null && T.getRight()==null){
            return new Fraction(T.getVal());
        }
        return Fraction.operation(T.getVal(),getResult(T.getLeft()),getResult(T.getRight()));
    }

}