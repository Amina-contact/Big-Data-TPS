package enset.Exercice1.Q1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;
public class Q1_Reduce extends MapReduceBase implements Reducer<Text, DoubleWritable, Text,Text> {
    @Override
    public void reduce(Text text, Iterator<DoubleWritable> values, OutputCollector<Text,Text> outputCollector,
                       Reporter reporter) throws IOException {
        Double maxSalary = 0.00, minSalary = 0.00, var;
        maxSalary = minSalary = values.next().get();
        while (values.hasNext()) {
            var = values.next().get();
            if (var > maxSalary) {
                maxSalary = var;
            }
            else minSalary = var;
        }
        String max_min="Le salaire  Min="+String.valueOf(minSalary)+" le salaire Max="+String.valueOf(maxSalary);
                 System.out.println(max_min);
                 outputCollector.collect(text,new Text(max_min));
    }

    /*public Double getMax(Iterator<DoubleWritable> list) {
        Double max = Double.MIN_VALUE;
        Double var;
        while (list.hasNext()){
            var=list.next().get();
            max=Math.max(var,max);
        }
        return max;
    }
    public Double getMin(Iterator<DoubleWritable> list) {
        Double min = Double.MAX_VALUE;
        Double variabl;
        while (list.hasNext()){
            variabl=list.next().get();
            min=Math.min(variabl,min);
        }
        return min;
    }*/
}
