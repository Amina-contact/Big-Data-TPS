package enset.Exercice1.Q1;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
public class Q1_Map extends MapReduceBase implements Mapper<LongWritable, Text,Text, DoubleWritable> {
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output,
                    Reporter reporter) throws IOException {
                String employees[]=value.toString().split(",");
                System.out.println(employees.length);
                output.collect(new Text(employees[2]),new DoubleWritable(Double.parseDouble(employees[4])));
    }
}
