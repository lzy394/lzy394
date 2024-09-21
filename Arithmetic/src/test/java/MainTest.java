import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class MainTest {
    @Test
    public void test1() {
        assertEquals("输入的参数个数有误",
                assertThrows(Exception.class, ()->Main.main(new String[]{"","",""})).getMessage());
    }
    @Test
    public void test2() {
        Main.main(new String[]{""});
    }
}
