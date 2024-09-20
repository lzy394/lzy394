import java.io.*;
import java.util.List;

public class FileUtil {
    public static void WriteFile(String Path,String str) throws IOException {
        File f= new File(Path);
        if(!f.exists()){//文件不存在
            if(!f.createNewFile()){
                System.out.println("创建失败");
                System.exit(1);
            }
        }
        try{
            FileWriter fw=new FileWriter(f,true);
            fw.write(str+"\n");
            fw.close();
        }catch(IOException e){
            throw new IOException("写入文件失败");
        }
    }

    public static void ReadFile(String Path, List<String> list) throws FileNotFoundException {
        File f=new File(Path);
        if(!f.exists())
            throw new RuntimeException("文件不存在");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while((line=br.readLine())!=null){
                int index= line.indexOf(".");
                if(line.contains("="))
                    line= line.substring(index+1, line.length()-1);
                else
                    line= line.substring(index+1);
                list.add(line.trim());
            }
        }catch(Exception e){
            throw new RuntimeException("读取文件失败");
        }

    }
}
