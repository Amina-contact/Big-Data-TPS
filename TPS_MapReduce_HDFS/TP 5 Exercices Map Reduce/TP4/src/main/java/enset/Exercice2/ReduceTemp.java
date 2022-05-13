package enset.Exercice2;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;
import java.util.Iterator;
public class ReduceTemp extends MapReduceBase
        implements Reducer<Text, DoubleWritable,Text,DoubleWritable> {
    @Override
    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output,
                       Reporter reporter) throws IOException {
        Double max = Double.MIN_VALUE, min = Double.MAX_VALUE, var;
        while (values.hasNext()) {
            var = values.next().get();
            max=Math.max(var,max);
            min=Math.min(var,min);
        }
        output.collect(new Text("La température maximale  du mois: "+key.toString()), new DoubleWritable(max));
        output.collect(new Text("La température minimale  du mois: "+key.toString()), new DoubleWritable(min));
    }
}
