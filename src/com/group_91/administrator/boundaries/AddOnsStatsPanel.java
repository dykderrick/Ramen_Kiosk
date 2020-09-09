package com.group_91.administrator.boundaries;

import com.group_91.administrator.entities.AddOnsStats;
import com.group_91.administrator.entities.WeeklyDates;
import com.group_91.customer.entities.AddOnsInfo;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for querying add on stats panel.
 *
 * @author Yingke Ding
 */
public class AddOnsStatsPanel extends JFrame {
    private final JPanel contentPane;
    private final JFXPanel jfxPanel;
    private final JButton backButton;


    /**
     * Create the panel.
     * Show the line chart on the top and a button in the bottom for back to previous page.
     */
    public AddOnsStatsPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBounds(50, 75, 500, 575);
        contentPane.setLayout(null);

        jfxPanel = new JFXPanel();
        jfxPanel.setPreferredSize(new Dimension(500, 520));

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(jfxPanel, BorderLayout.CENTER);
        chartPanel.setBounds(0, 0, 500, 520);
        contentPane.add(chartPanel);


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


        //contentPane.setBackground(Color.white);
        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);


        // JavaFX runner. lambda is used as a listener.
        Platform.runLater(this::createScene);

    }


    /**
     * Runner procedure for JavaFX application.
     * Invoke {@link AddOnsStatsPanel#createLineChart()} and load the chart to {@link JFXPanel}.
     */
    private void createScene() {
        backButton.setVisible(true);
        Chart chart = createLineChart();
        jfxPanel.setScene(new Scene(chart));
    }


    /**
     * Creating procedure for the line chart.
     * Online reference <a href="url">https://docs.oracle.com/javase/8/javafx/interoperability-tutorial/jtable-barchart.htm#CHDBHIJJ</a>
     * @return {@link LineChart} of the weekly add on sale chart.
     */
    private Chart createLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 20, 5);

        xAxis.setLabel("Weekdays");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Add Ons Weekly Stats");

        String[] addOnsNames = AddOnsInfo.getAddOnsNames();
        XYChart.Series[] seriesArray = new XYChart.Series[addOnsNames.length];

        for (int i = 0; i < seriesArray.length; ++i) {
            seriesArray[i] = new XYChart.Series<>();
            seriesArray[i].setName(addOnsNames[i]);

            Integer[] values = AddOnsStats.getSpecificAddOnWeeklyStats(addOnsNames[i]);

            for (int j = 0; j < values.length; ++j) {
                seriesArray[i].getData().add(new XYChart.Data(WeeklyDates.getWeeklyDates()[j], values[j]));
            }
        }

        lineChart.getData().addAll(seriesArray);

        return lineChart;
    }


    /**
     * Getter.
     * @return {@link AddOnsStatsPanel#contentPane}
     */
    public JPanel getAddOnsStatsPanel() {
        return contentPane;
    }

}
