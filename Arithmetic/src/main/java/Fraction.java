public class Fraction {
    private int numerator;//分子
    private int denominator=1;//分母

    public Fraction(String s)  {
        if(!s.contains("'")&&!s.contains("/")){
            numerator=Integer.parseInt(s);
        }
        else if(s.contains("'")){
            String []temp=s.split("['/]");
            int number=Integer.parseInt(temp[0]);
            int numerator=Integer.parseInt(temp[1]);
            denominator=Integer.parseInt(temp[2]);
            this.numerator=numerator+number*denominator;
        }
        else{
            String []temp=s.split("/");
            numerator=Integer.parseInt(temp[0]);
            denominator=Integer.parseInt(temp[1]);
        }
        if(denominator==0){
            throw new RuntimeException("分母不能为0");
        }
    }
    Fraction(int numerator, int denominator){
        if(denominator==0) {
            throw new RuntimeException("分母不能为0");
        }
        this.numerator=numerator;
        this.denominator=denominator;
    }
    public static Fraction RandomFraction(int max){
        int p=(int)(Math.random()*100)+1;
        if(p<=75){//75%的概率生成整数
            int numerator=(int)(Math.random()*max);
            if(numerator==0)
                numerator=1;
            return new Fraction(numerator,1);
        }
        else{
            int denominator=(int)(Math.random()*max)+1;
            if(denominator==max&&denominator!=1)
                denominator--;
            int numerator=(int)(Math.random()*denominator*max)+1;
            return new Fraction(numerator,denominator);
        }
    }

    private static int gcd(int a,int b){//求最大公因数
        while(b!=0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static Fraction operation(String operator,Fraction fa,Fraction fb) {//运算
        return switch (operator) {
            case "+" -> add(fa, fb);
            case "-" -> sub(fa, fb);
            case "×" -> mul(fa, fb);
            case "÷" -> div(fa, fb);
            default -> null;
        };
    }

    private static Fraction add(Fraction fa,Fraction fb){//加法
        int numerator=fa.numerator*fb.denominator+fb.numerator*fa.denominator;
        int denominator=fa.denominator*fb.denominator;
        return new Fraction(numerator,denominator);
    }

    private static Fraction sub(Fraction fa,Fraction fb) {//减法
        int numerator=fa.numerator*fb.denominator-fb.numerator*fa.denominator;
        int denominator=fa.denominator*fb.denominator;
        return new Fraction(numerator,denominator);
    }

    private static Fraction mul(Fraction fa,Fraction fb){//乘法
        int numerator=fa.numerator*fb.numerator;
        int denominator=fa.denominator*fb.denominator;
        return new Fraction(numerator,denominator);
    }

    private static Fraction div(Fraction fa,Fraction fb) {//除法
        if(fb.numerator==0)
            throw new RuntimeException("除数不能为0");
        int numerator=fa.numerator*fb.denominator;
        int denominator=fa.denominator*fb.numerator;
        return new Fraction(numerator,denominator);
    }

    public static Boolean isNegative(Fraction f){
        return f.toString().contains("-");
    }

    @Override//重写toString方法
    public String toString() {//输出
        if(this.numerator==0) return "0";
        if(this.denominator==1) return this.numerator+"";
        boolean tag= (numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0);//判断是否为负数
        numerator=Math.abs(this.numerator);
        denominator=Math.abs(this.denominator);
        int g=gcd(this.numerator,this.denominator);
        if (numerator < denominator) {
            if(!tag)return numerator/g + "/" + denominator/g;
            else return "-" + numerator/g + "/" + denominator/g;
        } else {
            int number=this.numerator/this.denominator;
            int r=this.numerator%this.denominator;
            if(r==0) {
                if(!tag)return number + "";
                else return "-" + number;
            }else{
                int r1=gcd(r,this.denominator);
                if(!tag)return number+"'"+r/r1+"/"+this.denominator/r1;
                else return "-" + number+"'"+r/r1+"/"+this.denominator/r1;
            }
        }
    }
}

