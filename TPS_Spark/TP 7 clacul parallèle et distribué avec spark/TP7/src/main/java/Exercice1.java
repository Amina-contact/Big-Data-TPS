import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;

public class Exercice1 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("EXercice 1").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.parallelize(Arrays.asList("Amina","Safae","Fatima","Bouchra"));
        JavaRDD<String> rdd3=rdd1.filter(a -> a.contains("Amina"));
        rdd3.foreach(a-> System.out.printf("rdd3: "+a));

        JavaRDD<String> rdd4=rdd1.filter(a -> a.contains("Fatima"));
        rdd4.foreach(a-> System.out.printf("rdd4: "+a));

        JavaRDD<String> rdd5=rdd1.filter(a -> a.contains("Safae"));
        rdd5.foreach(a-> System.out.printf("rdd5: "+a));

        JavaRDD<String> rdd6=rdd3.union(rdd4);
        rdd6.foreach(a-> System.out.printf("rdd6: "+a));

        JavaRDD<String> rdd71=rdd5.map( a-> a +" DH");
        rdd71.foreach(a-> System.out.printf("rdd71: "+a));

        JavaRDD<String> rdd81=rdd6.map( a-> a +" DH");
        rdd81.foreach(a-> System.out.printf("rdd81: "+a));

        JavaPairRDD<String,Integer> rdd72=rdd71.mapToPair(s->new Tuple2<>(s,1));
        JavaPairRDD<String,Integer> rdd7=rdd72.reduceByKey((v1, v2)->v1+v2);
        rdd7.foreach(nameTuple-> System.out.println("rdd7: "+"Name: "+nameTuple._1()+" key="+nameTuple._2()));

        JavaPairRDD<String,Integer> rdd82=rdd81.mapToPair(s->new Tuple2<>(s,1));
        JavaPairRDD<String,Integer> rdd8=rdd82.reduceByKey((v1, v2)->v1+v2);
        rdd8.foreach(nameTuple-> System.out.println("rdd8: "+"Name: "+nameTuple._1()+" key="+nameTuple._2()));

        JavaPairRDD<String,Integer> rdd9=rdd7.union(rdd8);
        rdd9.foreach(nameTuple-> System.out.println("rdd9: "+"Name: "+nameTuple._1()+" key="+nameTuple._2()));

        JavaPairRDD<String,Integer> rdd10=rdd9.sortByKey();
        rdd10.foreach(nameTuple-> System.out.println("rdd10: "+"Name: "+nameTuple._1()+" key="+nameTuple._2()));
    }
}
