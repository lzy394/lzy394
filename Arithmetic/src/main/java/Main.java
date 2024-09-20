import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static Set<String> Result= new HashSet<>();
    private static final String exercises_filePath="Exercises1.txt";
    private static final String answers_filePath="Answers1.txt";
    public static void main(String[] args)  {
        String []str1={"-n","10000","-r","100"};
        String []str2={"-e","exercisefile.txt","-a","answersfile.txt"};
        try {
            start(str1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(String[] args) throws IOException {
        if(args.length!=4){
            throw new RuntimeException("输入的参数个数有误");
        }
        int num=0;
        int max=0;
        String exercisefile=null;
        String answerfile=null;
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-n")){
                if(i+1<args.length){
                    num= Integer.parseInt(args[i+1]);
                    if(num<=0) throw new RuntimeException("生成的表达式数量小于等于0");
                }
            }
            if(args[i].equals("-r")){
                if(i+1<args.length){
                    max= Integer.parseInt(args[i+1]);
                    if(max<=0) throw new RuntimeException("生成的表达式最大范围小于等于0");
                }
            }
            if(args[i].equals("-e")){
                if(i+1<args.length){
                    exercisefile= args[i+1];
                }
            }
            if(args[i].equals("-a")){
                if(i+1<args.length){
                    answerfile= args[i+1];
                }
            }
        }
        if(num!=0&&max==0)
            max=num;//只要求生成题目时，最大范围与题目数一致
        if(num==0&&max!=0)
            num=10;//只要求题目范围时，默认生成10道题目
        if(num!=0&&max!=0) generate(num,max);
        if(exercisefile!=null&&answerfile!=null) Judge(exercisefile,answerfile);
    }
    private static void generate(int num,int max) throws IOException {
        int i=0;
        while(i<num){
            int num_Symbol=(int)(Math.random()*3+1);//随机生成1-3个运算符
            Equation e=new Equation(num_Symbol,max);
            String result=e.getResult().toString();
            System.out.println(result);
            if(!Result.isEmpty()&&Result.contains(result))
                continue;
            Result.add(result);
            String questions=(i+1)+". "+e.getinfixExpression()+"=";
            result=(i+1)+". "+result;
            FileUtil.WriteFile(exercises_filePath,questions);
            FileUtil.WriteFile(answers_filePath,result);
            i++;
        }
    }

    private static void Judge(String exercises,String answers) throws IOException {
        List<String> exerciseList=new ArrayList<>();
        List<String> answerList=new ArrayList<>();
        FileUtil.ReadFile(exercises,exerciseList);
        FileUtil.ReadFile(answers,answerList);
        if(exerciseList.size()!=answerList.size()){
            throw new RuntimeException("题目数量与答案数量不一致");
        }
        boolean []tag=new boolean[exerciseList.size()];//标记答案是否正确
        int num=0;//标记正确答案个数
        for(int i=0;i<exerciseList.size();i++){
            Equation e=new Equation(exerciseList.get(i));
            if(e.getResult().toString().equals(answerList.get(i))){
                tag[i]=true;
                num++;
            }
        }
        StringBuilder Correct= new StringBuilder("Correct:" + num + "(");
        StringBuilder Wrong= new StringBuilder("Wrong:" + (exerciseList.size() - num) + "(");
        for(int i=0;i<tag.length;i++){
            if(tag[i])
                Correct.append(i + 1).append(",");
            else
                Wrong.append(i + 1).append(",");
        }
        if(Correct.charAt(Correct.length()-1)!='(')
            Correct = new StringBuilder(Correct.substring(0, Correct.length() - 1) + ")");
        else
            Correct.append(")");
        if(Wrong.charAt(Wrong.length()-1)!='(')
            Wrong = new StringBuilder(Wrong.substring(0, Wrong.length() - 1) + ")");
        else
            Wrong.append(")");
        FileUtil.WriteFile("Grade.txt", Correct.toString());
        FileUtil.WriteFile("Grade.txt", Wrong.toString());
    }
}