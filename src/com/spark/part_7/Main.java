package com.spark.part_7;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.fpm.FPGrowth;
import org.apache.spark.ml.fpm.FPGrowthModel;
import org.apache.spark.sql.functions;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Part-6").master("local").getOrCreate();
        Dataset<Row> data = spark.read()
                .option("inferSchema", true)
                .option("header", true)
                .csv("hdfs://localhost:9000/retails.csv");

        Dataset<Row> selectedData = data.select("StockCode", "Country").where("Country is not null");
        Dataset<Row> itemsets = selectedData.withColumn("items", functions.array(selectedData.col("StockCode"), selectedData.col("Country")));
	
        FPGrowth fpg = new FPGrowth()
            .setItemsCol("items")
            .setMinSupport(0.001);

        FPGrowthModel model = fpg.fit(itemsets);

        model.freqItemsets().show(100);  
        model.associationRules().show();
        spark.stop();
    }
}