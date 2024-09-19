public class Main {
    public static void main(String[] args) throws Exception {
        Fraction f=new Fraction("19/3");
        Fraction f1=new Fraction("1/2");
        for(int i=0;i<10;i++){
            BinaryTree T=BinaryTree.RandomBinaryTree(3,10);
            System.out.println(Equation.SimpleEquation(BinaryTree.InOrderTraversal(T)));
        }
    }
}