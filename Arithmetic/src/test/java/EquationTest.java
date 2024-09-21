import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class EquationTest {
    @Test
    public void test1(){//测试随机生成的表达式
        Equation e=new Equation(2,5);
        System.out.println(e.getinfixExpression());
        System.out.println(e.getPostfixExpression());
        System.out.println(e.getResult().toString());
    }
    @Test
    public void test2(){//测试自定义的表达式
        Equation e=new Equation("1+2+3");
        System.out.println(e.getinfixExpression());
        System.out.println(e.getPostfixExpression());
        System.out.println(e.getResult().toString());
    }
}
