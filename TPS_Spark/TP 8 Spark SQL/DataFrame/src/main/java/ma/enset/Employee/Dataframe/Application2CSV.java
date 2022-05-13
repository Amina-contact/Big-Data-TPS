package ma.enset.Employee.Dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;
public class Application2CSV {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Dataset<Row> df = ss.read().option("header", true).csv("employees.csv");
        //df.printSchema();
        df.select( col("name")
          ).where(col("age").geq(30).and(col("age").leq(35))).show();

        //MOy des salaires par département
        df.select(col("departement"),col("salary").cast("double")).
               groupBy("departement").avg("salary").show();

        //Nombre des Emplooyees par departement
        df.select(col("departement")
                ).groupBy(col("departement")).count().show();

        //Salaire max
        df.select(max("salary")).show();
    }
}
