package ma.enset.Employee.Dataset;
import ma.enset.Employee.Employee;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;
public class Application2CSVDS {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();

        Encoder<Employee> employeeEncoder= Encoders.bean(Employee.class);
        Dataset<Employee> ds = ss.read().format("csv").option("delimiter",",").option("header", true)
                                                    .option("inferSchema", "true").option("charset", "UTF8")
                                                    .csv("employees.csv").as(employeeEncoder);
        System.out.println("Question 1");
        System.out.println("csv filr");
        ds.filter((FilterFunction<Employee>) emp->emp.getAge()>=30&&emp.getAge()<=35).show();


        System.out.println("Question 2");
        ds.select(col("departement"), col("salary")
                ).groupBy(col("departement")).mean("salary").show();


        System.out.println("Question 3");
        ds.select(col("departement"))
                .groupBy(col("departement")).count().show();

        System.out.println("Question 4");
        ds.select(max("salary")).show();

    }
}
