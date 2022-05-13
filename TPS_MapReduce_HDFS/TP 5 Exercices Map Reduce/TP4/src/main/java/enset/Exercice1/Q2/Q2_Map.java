package enset.Exercice1.Q2;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;
public class Q2_Map extends MapReduceBase implements Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {
                String employees[]=value.toString().split(",");
                System.out.println(employees.length);
                int valeur=1;
                output.collect(new Text(employees[2]),new IntWritable(valeur));
    }
}

