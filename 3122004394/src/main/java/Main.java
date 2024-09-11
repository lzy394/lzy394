
public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length !=3){
            System.out.println("提供的参数个数不正确");
            throw new Exception("提供的参数个数不正确");
        }
        Similarity.GetSimilarity(args[0],args[1],args[2]);
    }
}
