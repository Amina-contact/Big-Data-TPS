package ma.enset.TP3;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;

//dans target hadoop jar  nomJar.jar ma.enset.TP3.Application /prenom.txt/output1
public class VenteMap extends MapReduceBase
        //Utiliser DoubleWritable
        implements Mapper<LongWritable, Text,Text, IntWritable>{
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {
    String ventes[]=value.toString().split(" ");
    //output est la valeur
        System.out.println(ventes.length);
            output.collect(new Text(ventes[1]),new IntWritable(Integer.parseInt(ventes[3])));
    }
}


