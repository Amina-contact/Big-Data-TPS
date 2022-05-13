package ma.enset.Employee.Dataset;
import ma.enset.Employee.Employee;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.*;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;
public class Application1JsonDS {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();

        Encoder<Employee> employeeEncoder= Encoders.bean(Employee.class);
        Dataset<Employee> ds=ss.read().option("multiline",true).json("employees.json").as(employeeEncoder);
        //ds.printSchema();
        System.out.println("Question 1");
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
