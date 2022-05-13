import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Q6 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        System.setProperty("HADOOP_USER_NAME","root");
        try {
            FileSystem fs=FileSystem.get(cf);
           // fs.delete(new Path("/BDCC/JAVA/Cours/CoursCPP3.txt"),false);
            fs.rename(new Path("/BDCC/JAVA/Cours/CoursCPP1.txt"),new Path("/BDCC/JAVA/Cours/CoursJAVA1.txt"));
            fs.rename(new Path("/BDCC/JAVA/Cours/CoursCPP2.txt"),new Path("/BDCC/JAVA/Cours/CoursJAVA2.txt"));
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
