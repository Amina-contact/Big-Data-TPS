import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Q12 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        System.setProperty("HADOOP_USER_NAME","root");
        try {
            FileSystem fs=FileSystem.get(cf);
            fs.delete(new Path("/BDCC/JAVA"),true);
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
