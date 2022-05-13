import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Q4 {
    public static void main(String[] args) {
        String[] pathValue = {"/BDCC/CPP/Cours/CoursCPP1.txt","/BDCC/CPP/Cours/CoursCPP2.txt","/BDCC/CPP/Cours/CoursCPP3.txt"};
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        System.setProperty("HADOOP_USER_NAME","root");
        try {
            FileSystem fs=FileSystem.get(cf);
            for(int i=0;i<3;i++)
            {
                String url=pathValue[i];
                Path path=new Path(url);
                FSDataInputStream fsi=fs.open(path);
                BufferedReader br=new BufferedReader(new InputStreamReader(fsi));
                String ligne=null;
                while((ligne=br.readLine())!=null){
                    System.out.println(ligne);
                }
                br.close();
            }
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
