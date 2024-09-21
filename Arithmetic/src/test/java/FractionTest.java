import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class FractionTest {
    @Test
    public void test1(){//测试分母为0
        assertEquals("分母不能为0",
                assertThrows(Exception.class, () -> new Fraction(1, 0)).getMessage());
    }
    @Test
    public void test2(){//测试除数为0
        assertEquals("除数不能为0",
                assertThrows(Exception.class,
                        ()->Fraction.operation("÷",new Fraction("1"),new Fraction("0"))).getMessage());
    }
    @Test
    public void test3(){//测试加法
        assertEquals("3",Fraction.operation("+",new Fraction("2"),new Fraction("1")).toString());
    }
    @Test
    public void test4(){//测试减法
        assertEquals("1",Fraction.operation("-",new Fraction("2"),new Fraction("1")).toString());
    }
    @Test
    public void test5(){//测试乘法
        assertEquals("2",Fraction.operation("×",new Fraction("2"),new Fraction("1")).toString());
    }
    @Test
    public void test6(){//测试除法
        assertEquals("1/2",Fraction.operation("÷",new Fraction("1"),new Fraction("2")).toString());
    }
}
