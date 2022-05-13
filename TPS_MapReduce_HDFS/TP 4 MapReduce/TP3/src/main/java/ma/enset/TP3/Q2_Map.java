package ma.enset.TP3;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;
public class Q2_Map extends MapReduceBase
        implements Mapper<LongWritable, Text,Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {
        //Key double
        String year_city=value.toString().split(" ")[0]+" "+value.toString().split(" ")[1];
        System.out.println(year_city);
        String ventes[]=value.toString().split(" ");
        System.out.println(ventes.length);
        output.collect(new Text(year_city), new IntWritable(Integer.parseInt(ventes[3])));
    }
}

