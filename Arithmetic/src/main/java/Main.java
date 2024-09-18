public class Main {
    public static void main(String[] args) throws Exception {
        Fraction f=new Fraction("3'4/5");
        System.out.println(f.toString());
        Fraction f1=new Fraction("13/0");
        System.out.println(f1.toString());
    }
}
