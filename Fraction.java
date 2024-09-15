public class Fraction {
    private int numerator=0;
    private int denominator=1;
    private int number=0;
    public Fraction(String s)  {
        if(!s.contains("'")&&!s.contains("/")){
            number=Integer.parseInt(s);
            numerator=0;
        }
        else if(s.contains("'")){
            String []temp=s.split("['/]");
            number=Integer.parseInt(temp[0]);
            numerator=Integer.parseInt(temp[1]);
            denominator=Integer.parseInt(temp[2]);
        }
        else{
            String []temp=s.split("/");
            numerator=Integer.parseInt(temp[0]);
            denominator=Integer.parseInt(temp[1]);
        }
    }

    private static int gcd(int a,int b){
        while(b!=0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private void ProperFraction(){
        int g=gcd(this.numerator,this.denominator);
        if(this.denominator!=0&&g!=0){
            this.number+=this.numerator/this.denominator;
            this.numerator=this.numerator%this.denominator;
            this.numerator/=g;
            this.denominator/=g;
        }
    }
    public Fraction operation(String operator,Fraction f) {
        switch (operator) {
            case"+":return add(f);
//                case"-":return sub(f);
            case"×":return mul(f);
//                case"÷":return div(f);
        }
        return null;
    }
    private Fraction add(Fraction f){
        int f_number=f.number;
        int f_numerator=f.numerator;
        int f_denominator=f.denominator;
        this.number+=f_number;
        this.numerator=this.numerator*f_denominator+f_numerator*this.denominator;
        this.denominator*=f_denominator;
        ProperFraction();
        return this;
    }


    private Fraction mul(Fraction f){
        int number=this.number;
        int f_number=f.number;
        int f_numerator=f.numerator;
        int f_denominator=f.denominator;
        this.number*=f_number;
        this.numerator=number*this.denominator*f_numerator+this.numerator*f_number*f_denominator+ this.numerator*f_numerator;
        this.denominator*=f_denominator;
        ProperFraction();
        return this;
    }



    public String toString(){
        ProperFraction();
        if(number!=0) {
            if(numerator==0)
                return String.valueOf(number);
            else
                return number + "‘" + numerator + "/" + denominator;
        }
        return numerator + "/" + denominator;
    }
}
