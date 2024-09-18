public class Main {
    public static void main(String[] args) throws Exception {
        //Fraction f=new Fraction("3'4/5");
        Fraction f=new Fraction("19/3");
        Fraction f1=new Fraction("1/2");
        System.out.println(Fraction.operation("+",f,f1));
        System.out.println(Fraction.operation("-",f,f1));
        System.out.println(Fraction.operation("×",f,f1));
        System.out.println(Fraction.operation("÷",f,f1));

    }
}
