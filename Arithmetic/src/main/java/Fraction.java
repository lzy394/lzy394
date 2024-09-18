public class Fraction {
    private int numerator=0;//分子
    private int denominator=1;//分母
    //private int number=0;//整数部分
    public Fraction(String s) throws Exception {
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
            throw new Exception("分母不能为0");
        }
    }
    private Fraction (int number,int numerator,int denominator){
        this.numerator=numerator;
        this.denominator=denominator;
    }
    private static int gcd(int a,int b){
        while(b!=0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public Fraction operation(String operator,Fraction f1,Fraction f2) {
        switch (operator) {
            //case"+":return add(f1,f2);
//                case"-":return sub(f);
            //case"×":return mul(f1,f2);
//                case"÷":return div(f);
        }
        return null;
    }
//    private static Fraction add(Fraction fa,Fraction fb){
//        int fa1=fa.number,fb1=fb.number;
//        int fa2=fa.numerator,fb2=fb.numerator;
//        int fa3=fa.denominator,fb3=fb.denominator;
//        int number=fa1+fb1;
//        int numerator=fa2*fb3+fb2*fa3;
//        int denominator=fa3*fb3;
//        Fraction f=new Fraction(number,numerator,denominator);
//        ProperFraction(f);
//        return f;
//    }

//    private static Fraction mul(Fraction f){
//        int number=this.number;
//        int f_number=f.number;
//        int f_numerator=f.numerator;
//        int f_denominator=f.denominator;
//        this.number*=f_number;
//        this.numerator=number*this.denominator*f_numerator+this.numerator*f_number*f_denominator+ this.numerator*f_numerator;
//        this.denominator*=f_denominator;
//        ProperFraction();
//        return this;
//    }



    public String toString() {
        int g=gcd(this.numerator,this.denominator);
        if (numerator < denominator) {
            return numerator/g + "/" + denominator/g;
        } else {
            int number=this.numerator/this.denominator;
            int r=this.numerator%this.denominator;
            if(r==0) {
                return number + "";
            }else{
                int r1=gcd(r,this.denominator);
                return number+"'"+r/r1+"/"+this.denominator/r1;
            }

        }
    }
}
