package plots;

import org.jfree.chart.ChartFactory; // Main factory class for creating charts easily.
import org.jfree.chart.ChartUtilities; // Utility class for saving charts as image files.
import org.jfree.chart.JFreeChart; // Represents the fundamental chart object itself.
import org.jfree.chart.axis.LogarithmicAxis; // Allows setting axes to a logarithmic scale.
import org.jfree.chart.plot.CategoryPlot; // Manages the plotting area of category-based charts.
import org.jfree.chart.renderer.category.LineAndShapeRenderer; // Allows customizing line color, thickness, and shape.
import org.jfree.data.category.DefaultCategoryDataset; // Data structure holding chart data (x, y values, and series).

import java.awt.BasicStroke; // Used for line thickness and style settings.
import java.awt.Color; // Used for color definitions.
import java.io.BufferedReader; // Reads from a file line-by-line efficiently.
import java.io.FileReader; // Opens a connection to read a file.

public class PlotGenerator {

    public static void main(String[] args) {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // Creates an empty dataset to store chart data.

            BufferedReader dosyaokuyucu = new BufferedReader(new FileReader("data/results.csv")); //Opens a reader for the "data/results.csv" file.
            String line = dosyaokuyucu.readLine(); // Reads the first line of results.csv file and skips it.

            while ((line = dosyaokuyucu.readLine()) != null) { // Read line by line until end of file.

                if (line.trim().isEmpty()) // If line is empty, skip to next line.
                continue;
                if (line.startsWith("Algorithm")) // If line starts with "Algorithm", skip it.
                continue;

                String[] parts = line.split(","); // We make sense of the data by splitting lines at the comma and we accept each line as an array.
                if (parts.length < 4) // If data is missing (less than 4 columns), skip.
                continue;

                String algorithm = parts[0]; // Get first part as algorithm name.
                int n = Integer.parseInt(parts[1]); // Convert second part to integer (array size).
                double time = Double.parseDouble(parts[3]); // Convert fourth part to double (time).

                dataset.addValue(time, algorithm, String.valueOf(n)); // Add the read values to the chart dataset.
            }

            
            JFreeChart linechart = ChartFactory.createLineChart(
                    "Sorting Algorithm Performance",
                    "Array Size (n)",
                    "Time (ms, log scale)",
                    dataset
            ); //Start creating the line chart object. // Set title, X/Y axis labels, and data source.

            CategoryPlot plot = linechart.getCategoryPlot(); // Extracts the plot area for axis configuration.
            plot.setRangeAxis(new LogarithmicAxis("Time (ms, log scale)")); // Set the vertical (Y) axis to a logarithmic scale.

            
            LineAndShapeRenderer lineStyle = (LineAndShapeRenderer) plot.getRenderer(); // We set the line and shape appearance.
            lineStyle.setSeriesStroke(0, new BasicStroke(3.0f));
            lineStyle.setSeriesStroke(1, new BasicStroke(3.0f));
            lineStyle.setSeriesStroke(2, new BasicStroke(3.0f));         // Set the line thickness (stroke) to 3.0.
            lineStyle.setSeriesStroke(3, new BasicStroke(3.0f));
            lineStyle.setSeriesStroke(4, new BasicStroke(3.0f));   

            
            lineStyle.setSeriesPaint(0, Color.RED);        // BubbleSort
            lineStyle.setSeriesPaint(1, Color.BLUE);       // MergeSort
            lineStyle.setSeriesPaint(2, Color.GREEN);      // QuickSort      // Assign a specific color to each algorithm.
            lineStyle.setSeriesPaint(3, Color.MAGENTA);    // HeapSort
            lineStyle.setSeriesPaint(4, Color.ORANGE);     // SelectionSort

            ChartUtilities.saveChartAsPNG(
                    new java.io.File("data/performance_chart.png"),
                    linechart,
                    1200, 800
            ); // Save the configured chart as a PNG image file with dimensions 1200x800.

            System.out.println("Professional graph generated: data/performance_chart.png");

            dosyaokuyucu.close();

        } catch (Exception e) {
            e.printStackTrace(); // Catches any exceptions that occur during file processing and print the error details.
        }
    }
}

