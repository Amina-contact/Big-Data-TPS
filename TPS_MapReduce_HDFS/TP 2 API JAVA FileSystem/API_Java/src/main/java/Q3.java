import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class Q3 {
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
                FSDataOutputStream fsdo= fs.create(path);
                BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fsdo));
                br.write("Bonjour ");
                br.newLine();
                br.write("tout le monde");
                br.close();
            }
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
