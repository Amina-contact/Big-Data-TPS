import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.List;
public class Exercice3 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName ("Exercice 3" ) .setMaster ("local[*]") ;
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd1 = sc.textFile( "data.csv");
        JavaPairRDD<String, Long> rdd2 = rdd1.mapToPair (s -> new Tuple2<>(s. split( ",") [2],
                Long.parseLong (s.split( ",")[3])));
        //rdd2.foreach(nameTuple-> System.out.println(nameTuple._1()+" "+nameTuple._2()));
         /*
          Question 1
          */
        JavaPairRDD<String, Long> rdd3 = rdd2. filter (a -> a._1(). equals ("TMIN"));
        JavaPairRDD<String, Long> rdd4 = rdd3.reduceByKey ((v1, v2) -> v1+v2) ;
        Long nMIN = rdd3.count();
        System.out.printf("Température minimale moyenne ===>");
        rdd4.foreach(nameTuple->System.out.println(nameTuple._1()+" "+nameTuple._2()/nMIN));
        /*
          Question 2
          */
        JavaPairRDD<String, Long> rdd5 = rdd2. filter (a -> a._1(). equals ("TMAX"));
       // rdd5.foreach(nameTuple-> System.out.println(nameTuple._1()+" "+nameTuple._2()));
        JavaPairRDD<String, Long> rdd6 = rdd5.reduceByKey ((v1, v2) -> v1+v2) ;
        Long nMax = rdd5.count();
        System.out.printf("Température maximale moyenne ===>");
        rdd6.foreach(nameTuple->System.out.println(nameTuple._1()+" "+nameTuple._2()/nMax));
        /*
          Question 3
          */
        JavaRDD<Long> rdd7= rdd1.map ((s -> Long.parseLong(s.split( ",")[3])));
        List<Long> list = rdd7.collect();
        System.out.println("Température maximale la plus élevée "+list.stream().max(Long::compare).get());
        /*
          Question 4
          */
        System.out.println("Température minimale la plus basse "+list.stream().min(Long::compare).get());

        /*
          Question 5
          */
        JavaRDD<String> rddTMax = rdd1.filter(s -> s.contains("TMAX"));
        JavaPairRDD<Integer, String> station_chaudes = rddTMax.mapToPair(s -> new Tuple2<>(Integer.parseInt(s.split(",")[3]),
                s.split(",")[0]));
        System.out.println("Les 5  stations meteo les plus chaudes : " + station_chaudes.sortByKey(false).take(5));

        /*
          Question 6
          */
        JavaRDD<String> rddTMin = rdd1.filter(s -> s.contains("TMIN"));
        JavaPairRDD<Integer, String> station_froids = rddTMin.mapToPair(s -> new Tuple2<>(Integer.parseInt(s.split(",")[3]),
                s.split(",")[0]));
        System.out.println("Les 5  stations meteo les plus froides. : " + station_froids.sortByKey(true).take(5));

    }
}
