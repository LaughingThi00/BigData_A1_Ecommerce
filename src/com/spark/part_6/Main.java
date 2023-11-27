package com.spark.part_6;

import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.clustering.KMeansSummary;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Part-6")
                .master("local")
                .getOrCreate();

        // Load data
        Dataset<Row> data = spark.read()
                .option("inferSchema", true)
                .option("header", true)
                .csv("hdfs://localhost:9000/retails.csv");

        // Select relevant features for clustering (assuming features are in columns
        // "Quantity" and "UnitPrice")
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[] { "Quantity", "UnitPrice" })
                .setOutputCol("features");

        Dataset<Row> assembledData = assembler.transform(data).select("features");

        // Run KMeans with a range of values for k
        int kUpperLimit = 10; // Set an upper limit for the number of clusters to consider
        double[] distortions = new double[kUpperLimit];

        for (int k = 2; k <= kUpperLimit; k++) {
            KMeans kMeans = new KMeans().setK(k).setSeed(1L);
            KMeansModel model = kMeans.fit(assembledData);

            // Use KMeansCost to compute cost
            KMeansSummary summary = model.summary();
            distortions[k - 2] = summary.trainingCost();
        }

        // Find the optimal number of clusters using the Elbow Method
        int optimalNumberOfClusters = findOptimalNumberOfClusters(distortions);

        System.out.println("Optimal number of clusters (k) according to Elbow Method: " + optimalNumberOfClusters);

        // Run KMeans with the optimal number of clusters
        KMeans kMeans = new KMeans().setK(optimalNumberOfClusters).setSeed(1L);
        KMeansModel model = kMeans.fit(assembledData);

        // Get cluster centers
        Vector[] clusterCenters = model.clusterCenters();
        KMeansSummary summary = model.summary();

        for (int i = 0; i < clusterCenters.length; i++) {
            System.out
                    .println("Cluster " + i + " center: " + clusterCenters[i] + " size: " + summary.clusterSizes()[i]);
        }

        // Predict clusters for the data
        Dataset<Row> predictions = model.transform(assembledData);

        // Display results
        predictions.show();
    }

    // Function to find the optimal number of clusters using the Elbow Method
    private static int findOptimalNumberOfClusters(double[] distortions) {
        double[] differences = new double[distortions.length - 1];

        for (int i = 0; i < differences.length; i++) {
            differences[i] = distortions[i] - distortions[i + 1];
        }

        // Find the "elbow" of the curve
        int optimalK = 2;
        for (int i = 1; i < differences.length - 1; i++) {
            if (differences[i] < differences[optimalK - 2]) {
                optimalK = i + 2;
            }
        }

        return optimalK;
    }
}
