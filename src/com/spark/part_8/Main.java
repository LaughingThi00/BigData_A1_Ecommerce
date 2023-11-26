package com.spark.part_8;

import org.apache.spark.ml.fpm.FPGrowth;
import org.apache.spark.ml.fpm.FPGrowthModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Part-8")
                .master("local")
                .getOrCreate();

        Dataset<Row> data = spark.read()
                .option("inferSchema", true)
                .option("header", true)
                .csv("hdfs://localhost:9000/retails.csv");

        // Select relevant features for association rule mining
        Dataset<Row> transactions = data.groupBy("InvoiceNo").agg(functions.collect_set("StockCode").as("items"));

        // Create FPGrowth model
        FPGrowth fpGrowth = new FPGrowth()
                .setItemsCol("items")
                .setMinSupport(0.01)
                .setMinConfidence(0.5);

        FPGrowthModel model = fpGrowth.fit(transactions);
        Dataset<Row> frequentItemsets = model.freqItemsets();
        Dataset<Row> associationRules = model.associationRules();

        // Display meaningful results
        frequentItemsets.show();
        associationRules.show();

        spark.stop();
    }
}