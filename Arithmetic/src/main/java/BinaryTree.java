import org.w3c.dom.Node;

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
        int num=2*n+1;//节点总数
        BinaryTree []Node=new BinaryTree[num];//节点数组
        for(int i=0;i<num;i++){//初始化
            if(i<n) {
                int r = (int) (Math.random() * 4);
                Node[i] = new BinaryTree(Symbol[r]);//随机生成运算符
            }
            else{
                Node[i]=new BinaryTree(Fraction.RandomFraction(max).toString());//随机生成分数
            }
        }
        for(int i=0;i<n;i++){//连接
            if(2*i+1<num) Node[i].setLeft(Node[2*i+1]);//左子树
            if(2*i+2<num) Node[i].setRight(Node[2*i+2]);//右子树
        }
        for(int i=n-1;i>=0;i--){//更改不合理的节点
            if(Node[i].getVal().equals("-")) {//减法结果不能为负数
                Fraction result=getResult(Node[i]);
                if (Fraction.isNegative(result)) {
                    Swap(Node[i]);
                }
                if(result.toString().equals("0"))//减法结果不能为0
                    RandomBinaryTree(n,max);//重新生成
            }
            if(Node[i].getVal().equals("÷")) {//除法运算符右边不能为0
                Fraction result=getResult(Node[i].getRight());
                if(Fraction.isNegative(result)||result.toString().equals("0"))//为除法时不能为负数或0
                    RandomBinaryTree(n,max);//重新生成
            }
        }
        return Node[0];
    }

    public static Fraction getResult(BinaryTree T){//计算结果
        if(T==null) return null;//空节点
        if(T.getLeft()==null && T.getRight()==null){
            return new Fraction(T.getVal());//叶子节点
        }
        Fraction LeftResult=getResult(T.getLeft());
        Fraction RightResult=getResult(T.getRight());
        if(T.getVal().equals("÷")&& RightResult.toString().equals("0"))
            return new Fraction("-1");//返回负数说明计算出错
        return Fraction.operation(T.getVal(),LeftResult,RightResult);//递归计算结果
    }
}