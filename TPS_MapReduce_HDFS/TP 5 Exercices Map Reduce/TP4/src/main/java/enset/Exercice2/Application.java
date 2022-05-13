package enset.Exercice2;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import java.io.IOException;
public class Application {
    public static void main(String[] args) throws IOException {
        JobConf conf=new JobConf();
        conf.setJobName("La température minimale et maximale pour chaque mois");
        conf.setJarByClass(enset.Exercice1.Q1.Application.class);

        conf.setMapperClass(MapTemp.class);
        conf.setReducerClass(ReduceTemp.class);

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

