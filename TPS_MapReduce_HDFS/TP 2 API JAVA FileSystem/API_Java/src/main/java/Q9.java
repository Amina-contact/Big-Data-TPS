import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class Q9 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        System.setProperty("HADOOP_USER_NAME","root");
        try {
            FileSystem fs=FileSystem.get(cf);
            fs.copyFromLocalFile(new Path("/home/amina/Documents/BDCC4/BigData/Rep_Hadoop_TP1/TP2CPP.txt"),
                    new Path("/BDCC/CPP/TPs"));
            fs.copyFromLocalFile(new Path("/home/amina/Documents/BDCC4/BigData/Rep_Hadoop_TP1/TP2JAVA.txt"),
                    new Path("/BDCC/JAVA/TPs"));
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
