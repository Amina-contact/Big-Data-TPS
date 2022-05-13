import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Exercice2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName ("Exercice 2" ) .setMaster ("local[*]") ;
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd1 = sc.textFile( "ventes.txt");
        /*
          Question 1
          */
        JavaPairRDD<String, Long> rdd2 = rdd1.mapToPair (s -> new Tuple2<>(s. split( " ") [1],
                Long.parseLong (s.split( " ")[3])));
        JavaPairRDD<String, Long> rdd3 = rdd2.reduceByKey ((v1, v2) -> v1+v2) ;
        rdd3.foreach(nameTuple->System.out.println("Ville: "+nameTuple._1()+" Total_vente="+nameTuple._2()));

        /*
          Question 2
          */
        JavaPairRDD<String,Integer> rdd4 = rdd1.mapToPair(s-> new Tuple2<>("Années: "+s.split(" ")[0]+"  ville:  "+s.split(" ")[1],
                Integer.parseInt(s.split(" ")[3])));
        JavaPairRDD<String,Integer> rdd5=rdd4.reduceByKey((v1, v2) -> v1+v2);
        rdd5.foreach(nameTuple->System.out.println(nameTuple._1()+" Total_vente="+nameTuple._2()));
    }
}
