import java.io.File;
import java.text.DecimalFormat;

public class Similarity {
    private static int GetHammingDistance(String Path1, String Path2) throws Exception {
        String SimHash1=SimHash.GetSimHash(Path1);
        String SimHash2=SimHash.GetSimHash(Path2);
        int distance = 0;//汉明距离初始化为0
        for (int i = 0; i < SimHash1.length()&&i<SimHash2.length(); i++) {
            if (SimHash1.charAt(i) != SimHash2.charAt(i)) {//逐位比较
                distance++;
            }
        }
        return distance;//返回汉明距离
    }
    public static String GetSimilarity(String Path1, String Path2,String Path3) throws Exception {
        String NullPathErrorMessage="参数中的文件路径为空";
        if(Path1==null||Path1.isEmpty()||Path2==null||Path2.isEmpty())//判断文件路径是否为空
        {
            System.out.println(NullPathErrorMessage);
            throw new Exception(NullPathErrorMessage);
        }
        String nullPathErrorMessage = "答案文件路径为空，无法将结果写入";
        if(Path3==null||Path3.isEmpty()) {//路径为空
            System.out.println(nullPathErrorMessage);
            throw new Exception(nullPathErrorMessage);
        }
        if(!new File(Path3).exists())
        {
            throw new Exception("答案文件不存在");
        }
        int HammingDistance = GetHammingDistance(Path1, Path2);
        //保存文件名
        String Path3filename=Path3.substring(Path3.lastIndexOf("\\")+1);
        String fileNotExistErrorMessage = "答案文件"+Path3filename+"不存在，无法将结果写入";
        File file = new File(Path3);
        if(!file.exists()||!file.isFile()) {//文件不存在
            System.out.println(fileNotExistErrorMessage);
            throw new Exception(fileNotExistErrorMessage);
        }
        double Similarity = 1 - (double) HammingDistance / SimHash.getVectorsize();
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        System.out.println(df.format(Similarity));
        FileIO.WriteFile(Path3,df.format(Similarity));
        return df.format(Similarity);
    }

}
