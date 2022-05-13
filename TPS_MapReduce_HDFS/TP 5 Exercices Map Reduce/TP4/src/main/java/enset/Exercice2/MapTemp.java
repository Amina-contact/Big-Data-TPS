package enset.Exercice2;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;
public class MapTemp extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    public void map(LongWritable longWritable, Text value, OutputCollector<Text, DoubleWritable> outputCollector,
                    Reporter reporter) throws IOException {
        String date= value.toString().split("\",\"")[1];//extraire la date
        String month=date.toString().split("-")[0];//extraire le mois
        String Temp=value.toString().split("\",\"")[13];//extraire la température
        Temp.replace(",",".");
        Double Tempdouble=Double.parseDouble(Temp);
        System.out.println(Tempdouble);
        outputCollector.collect(new Text(month),new DoubleWritable(Tempdouble));
       /* String data= value.toString().split("\",\"")[1].split("-")[1];
        String TEMP=value.toString().split("\",\"")[13];
        StringBuilder stringBuilder= new StringBuilder(TEMP);
        Double a=Double.parseDouble( stringBuilder.toString().replace(",","."));
        System.out.println(a);
        outputCollector.collect(new Text(data),new DoubleWritable(a));*/
    }
}
