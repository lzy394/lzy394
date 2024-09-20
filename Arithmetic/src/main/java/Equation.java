import java.util.Arrays;
import java.util.Stack;

public class Equation {
    private static final String []Symbol={"+","-","×","÷","(",")"};
    private final String infixExpression;//中缀表达式
    private final String postfixExpression;//后缀表达式
    private Fraction result;//结果
    public Equation(int SymbolNum,int Range){//随机生成表达式
        BinaryTree T=BinaryTree.RandomBinaryTree(SymbolNum,Range);
        this.infixExpression=SimpleEquation(BinaryTree.InOrderTraversal(T));
        this.postfixExpression=BinaryTree.PostOrderTraversal(T);
        this.result=BinaryTree.getResult(T);
    }
    public Equation(String Expression){
        if(!Expression.contains(" ")){//如果表达式中没有空格,直接写入中缀表达式
            this.infixExpression=Expression;
            for (String s : Symbol) {
                Expression = Expression.replace(s, " " + s + " ").trim();
            }
        }else {
            String []temp=Expression.split("\\s+");
            StringBuilder str= new StringBuilder();
            for (String s : temp) {
                str.append(s);
            }
            this.infixExpression= str.toString();
        }
        this.postfixExpression=infixToPostfix(Expression);
        this.result=Calculate_post(this.postfixExpression);
    }
    public String getinfixExpression() {
        return infixExpression;
    }

    public String getPostfixExpression() {
        return postfixExpression;
    }

    public Fraction getResult() {
        return result;
    }

    public static String SimpleEquation(String Expression) {//化简表达式,去括号
        StringBuilder result= new StringBuilder();
        int[] a=new int[Expression.length()];//记录是否被处理过
        for(int i=0;i<Expression.length();i++) {
            char c=Expression.charAt(i);
            if(a[i]==1) {//如果已经处理过，则跳过
                continue;
            }
            if(c=='(') {
                boolean tag=true;
                if(i>=1) {
                    if(Expression.charAt(i-1)=='×' || Expression.charAt(i-1)=='÷' ) {
                        continue;
                    }
                }
                int index=i+1;//右括号位置
                int count=0;
                boolean tag_sub =true;
                if(i>=1) {
                    if(Expression.charAt(i-1)=='-') {
                        tag_sub =false;
                    }
                }
                while(true) { //找到匹配的右括号
                    if(!tag_sub && (Expression.charAt(index)=='+'||Expression.charAt(index)=='-')) {  //判断左括号是-时，括号里有没有+
                        tag=false;
                        break;
                    }
                    if(Expression.charAt(index)==')' && count==0) {
                        break;
                    }
                    if(Expression.charAt(index)=='(') {
                        count++;
                    }
                    if(Expression.charAt(index)==')') {
                        count--;
                    }
                    index++;
                }
                if(!tag) {//如果括号里有+，则不处理
                  continue;
                }
                boolean tag_delete=true;
                if(index+1<Expression.length()) {//避免越界
                    if(Expression.charAt(index+1)=='×' || Expression.charAt(index+1)=='÷') {  //判断右括号右边是否有乘除
                        tag_delete=false;
                    }
                }
                if(tag_delete) {   //记录删除的括号位置
                    a[i]=1;
                    a[index]=1;
                }
            }
        }
        for(int i=0;i<Expression.length();i++) {
            if(a[i]==0) {
                result.append(Expression.charAt(i));
            }
        }
        return result.toString();
    }

    private Boolean isSymbol(String s){
        return s.equals("+")||s.equals("-")||s.equals("×")||s.equals("÷")||s.equals("(")||s.equals(")")||s.equals("=");
    }

    private int SymbolPriority(String s){
        return switch (s) {
            case "+", "-" -> 1;
            case "×", "÷" -> 2;
            default -> 0;
        };
    }

    private String infixToPostfix(String infixExpression){
        String []Expression=infixExpression.split("\\s+");
        StringBuilder result= new StringBuilder();
        Stack<String> S=new Stack<>();
        for (String s : Expression) {
            if(s.equals(" "))
                continue;
            if (!isSymbol(s)) {//不是符号,直接输出
                result.append(s).append(" ");
            } else if (s.equals("(")) {//左括号直接入栈
                S.push(s);
            } else if (s.equals(")")) {//元素出栈直到遇到左括号
                while (!S.isEmpty() && !S.peek().equals("(")) {
                    result.append(S.pop()).append(" ");
                }
                S.pop();//匹配后的左括号出栈
            } else {//按优先级入栈出栈
                while (!S.isEmpty() && SymbolPriority(S.peek()) >= SymbolPriority(s)) {
                    result.append(S.pop()).append(" ");
                }
                S.push(s);
            }
        }
        while(!S.isEmpty()) {
            result.append(S.pop()).append(" ");
        }
        return result.toString();//输出后缀表达式
    }

    private Fraction Calculate_post(String Expression){
        String []exp=Expression.split(" ");
        Stack<Fraction>  S=new Stack<>();
        for (String s : exp) {
            if (!isSymbol(s)) {
                S.push(new Fraction(s));
            } else {
                if (S.size() < 2) {
                    throw new RuntimeException("后缀表达式异常");
                }
                Fraction b = S.pop();
                Fraction a = S.pop();
                S.push(Fraction.operation(s, a, b));
            }
        }
        return S.pop();
    }

    public static void main(String[] args) {
        Equation e=new Equation("(38'74/75×63'19/64)×(55×52'17/25)");
        System.out.println(e.getPostfixExpression());
        System.out.println(e.getResult().toString());
    }
}