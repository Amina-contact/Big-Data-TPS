package ma.enset.TP3;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import java.io.IOException;
public class Q2_Application {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("Total des ventes par villes pour une année donnée");
        conf.setJarByClass(Q2_Application.class);

        conf.setMapperClass(Q2_Map.class);
        conf.setReducerClass(Q2_Reduce.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

/*        FileInputFormat.addInputPath(conf,new Path("ventes.txt"));
        FileOutputFormat.setOutputPath(conf,new Path("./output1"));*/

        FileInputFormat.addInputPath(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));

        JobClient.runJob(conf);
    }
}

