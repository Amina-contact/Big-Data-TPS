package enset.Exercice1.Q1;

import enset.Exercice1.Q2.Q2_Map;
import enset.Exercice1.Q2.Q2_Reduce;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
public class Application {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("Min Max des salaires des employées par département");
        conf.setJarByClass(Application.class);

        conf.setMapperClass(Q1_Map.class);
        conf.setReducerClass(Q1_Reduce.class);

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(DoubleWritable.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

/*      FileInputFormat.addInputPath(conf,new Path("Employee.csv"));
        FileOutputFormat.setOutputPath(conf,new Path("./output1"));*/

        FileInputFormat.addInputPath(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));

        JobClient.runJob(conf);
    }
}

