public class Equation {
    private int number;//等式数量
    private int range=1;//等式数值范围,默认为1

    public Equation(int number, int range) {
        this.number = number;
        this.range = range;
    }

    public static String SimpleEquation(String Expression) {
        StringBuilder result= new StringBuilder();
        int[] a=new int[Expression.length()];//记录是否被处理过
        for(int i=0;i<Expression.length();i++) {
            char c=Expression.charAt(i);
            if(a[i]==1) {
                continue;
            }
            if(c=='(') {
                boolean tag=true;
                if(i>=1) {
                    if(Expression.charAt(i-1)=='×' || Expression.charAt(i-1)=='÷' ) {  //左括号前有乘除
                        //tag=false;
                        continue;//jia
                    }
                }

                if(!tag) {
                  continue;
                }

                int index=i+1;//右括号位置
                int count=0;
                boolean tag_sub =true;
                if(i>=1) {
                    if(Expression.charAt(i-1)=='-') {
                        tag_sub =false;
                    }
                }
                while(true) {    //找到匹配的右括号
                    if(!tag_sub && (Expression.charAt(index)=='+')) {  //判断左括号是-时，括号里有没有+
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

                if(!tag) {
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
}
