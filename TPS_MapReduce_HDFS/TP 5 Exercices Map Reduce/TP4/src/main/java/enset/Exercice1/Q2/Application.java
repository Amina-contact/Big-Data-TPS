package enset.Exercice1.Q2;
import enset.Exercice1.Q1.Q1_Map;
import enset.Exercice1.Q1.Q1_Reduce;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
public class Application {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("Nombre des employées par département");
        conf.setJarByClass(Application.class);

        conf.setMapperClass(Q1_Map.class);
        conf.setReducerClass(Q1_Reduce.class);


        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

/*      FileInputFormat.addInputPath(conf,new Path("Employee.csv"));
        FileOutputFormat.setOutputPath(conf,new Path("./output2"));*/

        FileInputFormat.addInputPath(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));

        JobClient.runJob(conf);
    }
}

