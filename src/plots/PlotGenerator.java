package plots;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

public class PlotGenerator {

    public static void main(String[] args) {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            BufferedReader br = new BufferedReader(new FileReader("data/results.csv"));
            String line = br.readLine(); // başlığı geç

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;
                if (line.startsWith("Algorithm")) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String algorithm = parts[0];
                int n = Integer.parseInt(parts[1]);
                int trial = Integer.parseInt(parts[2]);
                double time = Double.parseDouble(parts[3]);

                dataset.addValue(time, algorithm, String.valueOf(n));
            }

            // LOGARİTMİK GRAFİK
            JFreeChart chart = ChartFactory.createLineChart(
                    "Sorting Algorithm Performance",
                    "Array Size (n)",
                    "Time (ms, log scale)",
                    dataset
            );

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeAxis(new LogarithmicAxis("Time (ms, log scale)"));

            // ÇİZGİ KALINLIĞI AYARLAMA
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesStroke(0, new BasicStroke(3.0f));
            renderer.setSeriesStroke(1, new BasicStroke(3.0f));
            renderer.setSeriesStroke(2, new BasicStroke(3.0f));
            renderer.setSeriesStroke(3, new BasicStroke(3.0f));

            // RENKLER
            renderer.setSeriesPaint(0, Color.RED);        // BubbleSort
            renderer.setSeriesPaint(1, Color.BLUE);       // MergeSort
            renderer.setSeriesPaint(2, Color.GREEN);      // QuickSort
            renderer.setSeriesPaint(3, Color.MAGENTA);    // HeapSort

            ChartUtilities.saveChartAsPNG(
                    new java.io.File("data/performance_chart.png"),
                    chart,
                    1200, 800
            );

            System.out.println("Profesyonel grafik oluşturuldu: data/performance_chart.png");

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

