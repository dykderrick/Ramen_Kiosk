package com.group_91.administrator.boundaries;

import com.group_91.administrator.entities.IntOptionStats;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Interface for spiciness stats viewing function.
 *
 * @author Yingke Ding
 */
public class IntOptionStatsPanel extends JFrame {
    private final JPanel contentPane;
    private final JFXPanel jfxPanel;
    private final JLabel messageLabel;
    private final JButton backButton;


    /**
     * Constructs the frame.
     */
    public IntOptionStatsPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        //contentPane.setSize(500,575);
        contentPane.setBounds(50, 75, 500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        jfxPanel = new JFXPanel();
        jfxPanel.setPreferredSize(new Dimension(500, 480));

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(jfxPanel, BorderLayout.CENTER);
        chartPanel.setBounds(0, 0, 500, 480);
        contentPane.add(chartPanel);

        int mostPopularIndex = IntOptionStats.getMostPopular().keySet().toArray(new Integer[0])[0];
        double percentage = IntOptionStats.getMostPopular().values().toArray(new Double[0])[0];
        String percentageString = String.format("%.2f", percentage * 100);
        messageLabel = new JLabel("<html>The most popular spiciness is "+ mostPopularIndex +" degree,<br/>" + percentageString + "% orders choose this.<html>");
        messageLabel.setBounds(100, 480, 480, 40);
        contentPane.add(messageLabel);
        messageLabel.setVisible(false);  // have to setVisible in Platform.runLatter


        backButton = new JButton("Back");
        backButton.setBounds(210, 520, 70, 30);
        contentPane.add(backButton);
        backButton.setVisible(false);

        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            topFrame.setVisible(false);
            FunctionSelection panel = new FunctionSelection();
            panel.setTitle("Totoro Ramen Kiosk");
            panel.setSize(600, 725);
            panel.setVisible(true);
        });

        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);


        Platform.runLater(this::createScene);

    }


    /**
     * Runner procedure for JavaFX application.
     * Invoke {@link IntOptionStatsPanel#createPieChart()} and load the chart to {@link JFXPanel}.
     */
    private void createScene() {
        messageLabel.setVisible(true);
        backButton.setVisible(true);
        Chart chart = createPieChart();
        jfxPanel.setScene(new Scene(chart));
    }


    /**
     * Creating procedure for the line chart of spiciness stats.
     * @return {@link PieChart}
     */
    private PieChart createPieChart() {
        HashMap<Integer, Integer> rawData = IntOptionStats.getAllPopularity();

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("0 (No)", rawData.get(0)),
                        new PieChart.Data("1", rawData.get(1)),
                        new PieChart.Data("2", rawData.get(2)),
                        new PieChart.Data("3", rawData.get(3)),
                        new PieChart.Data("4", rawData.get(4)),
                        new PieChart.Data("5 (High)", rawData.get(5))
                );

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Customer Spiciness Preference");


        return chart;

    }


    /**
     * Getter.
     * @return {@link IntOptionStatsPanel#contentPane}
     */
    public JPanel getIntOptionStatsPanel() {
        return contentPane;
    }

}
