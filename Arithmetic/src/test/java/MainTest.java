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
        assertEquals("生成的表达式数量小于等于0",
                assertThrows(Exception.class,()->Main.main(new String[]{"-n","0"})).getMessage());
    }
    @Test
    public void test3() {
        assertEquals("生成的表达式数量小于等于0",
                assertThrows(Exception.class,()->Main.main(new String[]{"-n","-1"})).getMessage());
    }
    @Test
    public void test4() {//不存在文件运行
        assertEquals("文件不存在",
                assertThrows(Exception.class,()->Main.main(new String[]{"-e","exercisefile1.txt","-a","answersfile1.txt"})).getMessage());
    }
    @Test
    public void test5() {
        Main.main(new String[]{"-n","20","-r","10"}); //测试能否正常运行
    }
    @Test
    public void test6() {
        Main.main(new String[]{"-e","exercisefile.txt","-a","answersfile.txt"});
    }
}
