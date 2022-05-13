package ma.enset.Employee.Dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;
public class Application1Json{
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Dataset<Row> df = ss.read().option("multiline", true).json("employees.json");
        //df.printSchema();
        //df.show();
        df.createOrReplaceTempView("employees");
        df.filter(col("age").between(30,35)).show();

        df.groupBy("departement").avg("salary").show();

        df.groupBy("departement").count().show();

        df.select(max("salary")).show();

    }
}
