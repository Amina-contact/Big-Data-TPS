import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
public class Q5 {
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
                FileUtil.copy(fs,new Path(url),fs,new Path("/BDCC/JAVA/Cours"),false,cf);
            }
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
