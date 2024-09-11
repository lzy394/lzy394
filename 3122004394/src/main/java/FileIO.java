import java.io.*;


public class FileIO {
    public static String ReadFile(String Path) throws Exception {
        String filename=Path.substring(Path.lastIndexOf("\\")+1);
        String FileNotExistsErrorMessage="文件"+filename+"不存在，无法查重";
        File file=new File(Path);//创建文件对象

        if(!file.exists()) {//判断文件是否存在
            System.out.println(FileNotExistsErrorMessage);
            throw new Exception(FileNotExistsErrorMessage);
        }

        StringBuilder str = new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));//创建文件读取流
            String str1;
            while ((str1=br.readLine())!=null) {//按行读取文件内容
                str.append(str1);
            }
            br.close();
        }catch(Exception e){
            throw new Exception("文件读取失败");
        }

        if(str.toString().isEmpty()) {//判断文件内容是否为空
            String EmptyFileErrorMessage="文件"+filename+"内容为空";
            System.out.println(EmptyFileErrorMessage);
            throw new Exception(EmptyFileErrorMessage);
        }
        if(str.length()<3) {//判断文件内容是否过短
            String ShortFileErrorMessage="文件"+filename+"内容过短";
            System.out.println(ShortFileErrorMessage);
            throw new Exception(ShortFileErrorMessage);
        }

        return str.toString();//将文件内容读取到字符串中
    }
    public static void WriteFile(String ansPath,String ans) throws IOException {
        //tag为真时将结果写入答案文件，否则将错误信息写入指定的文件，为了方便测试，结果和错误信息都写入同一个文件
       try{
           FileWriter fw;
           fw = new FileWriter(ansPath, true);
           fw.write(ans+"\n");
           fw.close();
       }catch(Exception e){
           throw new IOException("文件写入失败");//抛出异常
       }
    }
}
