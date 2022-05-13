import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Q10 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        System.setProperty("HADOOP_USER_NAME","root");
        try {
            FileSystem fs=FileSystem.get(cf);
            List<String> tabFiles = getAllFilePath(new Path("/BDCC"),fs);
            fs.close();
            for(int i=0;i<tabFiles.size();i++){
                System.out.println(tabFiles.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<String> getAllFilePath(Path filePath, FileSystem fs) throws FileNotFoundException, IOException {
        List<String> fileList = new ArrayList<String>();
        FileStatus[] fileStatus = fs.listStatus(filePath);
        for (FileStatus fileStat : fileStatus) {
            if (fileStat.isDirectory()) {
                fileList.addAll(getAllFilePath(fileStat.getPath(), fs));
            } else {fileList.add(fileStat.getPath().toString());}
        }
        return fileList;
    }
}
