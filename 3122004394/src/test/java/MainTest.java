import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
    @Test
    public void a_testParameterNum() {//参数个数测试
        assertEquals("提供的参数个数不正确",
                assertThrows(Exception.class, ()-> Main.main(new String[]{""})).getMessage());
    }

    @Test
    public void b_testNullPath()  {//查重文件路径为空测试
        assertEquals("参数中的文件路径为空",
                assertThrows(Exception.class, ()-> Main.main(new String[]{null, "", ""})).getMessage());
    }

    @Test
    public void c_testNull_ansPath() {//答案文件路径为空测试
        assertEquals("答案文件路径为空，无法将结果写入",
                assertThrows(Exception.class, ()-> Main.main(new String[]{
                        "D:\\Javacode\\PersonalProject\\orig.txt",
                        "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                        null})).getMessage());
    }

    @Test
    public void d_testNotExist_ansFile()  {//答案文件不存在测试
        assertEquals("答案文件不存在",
                assertThrows(Exception.class, ()-> Main.main(new String[]{
                        "D:\\Javacode\\PersonalProject\\orig.txt",
                        "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                        "D:\\Javacode\\PersonalProject\\a.txt"})).getMessage());
    }

    @Test
    public void e_testNotExist_File(){//查重文件不存在测试
        assertEquals("文件testfile1.txt不存在，无法查重",
                assertThrows(Exception.class, ()-> Main.main(new String[]{
                        "D:\\Javacode\\PersonalProject\\testfile1.txt",
                        "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                        "D:\\Javacode\\PersonalProject\\ans.txt"})).getMessage());
    }

    @Test
    public void f_testEmpty_File(){//查重文件为空测试
        assertEquals("文件testfile2.txt内容为空",
                assertThrows(Exception.class, ()-> Main.main(new String[]{
                        "D:\\Javacode\\PersonalProject\\testfile2.txt",
                        "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                        "D:\\Javacode\\PersonalProject\\ans.txt"})).getMessage());
    }

    @Test
    public void g_testShort_File(){//查重文件过短测试
        assertEquals("文件testfile3.txt内容过短",
                assertThrows(Exception.class, ()-> Main.main(new String[]{
                        "D:\\Javacode\\PersonalProject\\testfile3.txt",
                        "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                        "D:\\Javacode\\PersonalProject\\ans.txt"})).getMessage());
    }

    @Test
    public void h_test1() throws Exception {
        Main.main(new String[]{
                "D:\\Javacode\\PersonalProject\\orig.txt",
                "D:\\Javacode\\PersonalProject\\orig_0.8_add.txt",
                "D:\\Javacode\\PersonalProject\\ans.txt"});
    }

    @Test
    public void i_test2() throws Exception {
        Main.main(new String[]{
                "D:\\Javacode\\PersonalProject\\orig.txt",
                "D:\\Javacode\\PersonalProject\\orig_0.8_del.txt",
                "D:\\Javacode\\PersonalProject\\ans.txt"});
    }

    @Test
    public void j_test3() throws Exception {
        Main.main(new String[]{
                "D:\\Javacode\\PersonalProject\\orig.txt",
                "D:\\Javacode\\PersonalProject\\orig_0.8_dis_1.txt",
                "D:\\Javacode\\PersonalProject\\ans.txt"});
    }

    @Test
    public void k_test4() throws Exception {
        Main.main(new String[]{
                "D:\\Javacode\\PersonalProject\\orig.txt",
                "D:\\Javacode\\PersonalProject\\orig_0.8_dis_10.txt",
                "D:\\Javacode\\PersonalProject\\ans.txt"});
    }

    @Test
    public void l_test5() throws Exception {
        Main.main(new String[]{
                "D:\\Javacode\\PersonalProject\\orig.txt",
                "D:\\Javacode\\PersonalProject\\orig_0.8_dis_15.txt",
                "D:\\Javacode\\PersonalProject\\ans.txt"});
    }
}