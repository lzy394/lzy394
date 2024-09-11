import com.hankcs.hanlp.HanLP;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class SimHash {
    private static int Vectorsize=64;//设置向量维度为32
    private static int[] vector=new int[Vectorsize];//向量

    public static int getVectorsize() {
        return Vectorsize;
    }
    private static List<String> SplitString(String str)  {
        return HanLP.extractKeyword(str, str.length());//使用HanLP进行分词
    }
    private static int[] WeightStringHash(String s) {//计算字符串的权重哈希值
        int[] temp = new int[Vectorsize];//初始化权重哈希值数组
        Arrays.fill(temp, 0);//将数组元素全部置为0
        BigInteger hash = BigInteger.valueOf(s.hashCode()); //获取字符串的哈希值
        int weight = hash.mod(BigInteger.valueOf(10)).intValue() + 1; //设置权值为1-10
        String binaryStringHash = Integer.toBinaryString(s.hashCode()); //将哈希值转换为二进制字符串
        for (int i = 0; i < binaryStringHash.length()&&i<Vectorsize; i++) {
            if (binaryStringHash.charAt(i) == '1') {//逐位修改权值
                temp[i]+=weight;
            }else{
                temp[i]-=weight;
            }
        }
        return temp;
    }
    private static void MergeVector(List<String> list) {//合并向量
        for (String s : list) {
            int[] temp = WeightStringHash(s);
            for (int i = 0; i < temp.length; i++) {
                vector[i] += temp[i];
            }
        }
    }
    public static String GetSimHash(String s) throws Exception {//获取字符串的SimHash值
        MergeVector(SplitString(FileIO.ReadFile(s)));//将文件内容进行分词，并计算权重哈希值
        StringBuilder sb = new StringBuilder();
        for (int i : vector) {//将向量转换为二进制字符串
            if (i > 0) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        Arrays.fill(vector, 0);//清空向量
        return sb.toString();
    }
}
