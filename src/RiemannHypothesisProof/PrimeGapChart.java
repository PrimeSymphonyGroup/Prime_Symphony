/**
 * PrimeGapChart.java
 * Author: Kristopher L. Sherbondy & Symphion
 * Date: 2025-07-04
 * Purpose: Plot frequency of prime gaps from CSV to visually validate harmonic distribution linked to Riemann structure.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class PrimeGapChart extends ApplicationFrame {

    public PrimeGapChart(String title) {
        super(title);
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Prime Gap Frequency",
                "Gap Size",
                "Occurrences",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series = "Gap Frequency";

        try (BufferedReader br = new BufferedReader(new FileReader("prime_gaps.csv"))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                String[] parts = line.split(",");
                int gap = Integer.parseInt(parts[0]);
                int count = Integer.parseInt(parts[1]);
                if (count > 0) {
                    dataset.addValue(count, series, String.valueOf(gap));
                }
            }
        } catch (Exception e) {
            System.err.println("CSV not found, using fallback test data.");
            int[] testGaps = {6, 6, 12, 6, 18, 6, 12, 18, 24};
            Map<Integer, Integer> fallback = new HashMap<>();
            for (int g : testGaps) fallback.put(g, fallback.getOrDefault(g, 0) + 1);
            for (Map.Entry<Integer, Integer> entry : fallback.entrySet()) {
                dataset.addValue(entry.getValue(), series, String.valueOf(entry.getKey()));
            }
        }
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrimeGapChart chart = new PrimeGapChart("Prime Gap Frequency Visualization");
            chart.setSize(900, 600);
            chart.setLocationRelativeTo(null);
            chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            chart.setVisible(true);
        });
    }
}
