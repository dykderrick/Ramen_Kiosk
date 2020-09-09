package com.group_91.administrator.boundaries;

import com.group_91.administrator.entities.SuitStats;
import com.group_91.administrator.entities.WeeklyDates;
import com.group_91.customer.entities.SuitInfo;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for Suit stats panel.
 *
 * @author Yingke Ding
 */
public class SuitStatsPanel extends JFrame {
    private final JPanel contentPane;
    private JFXPanel jfxPanel;
    private final JButton backButton;


    /**
     * Constructs the frame.
     */
    public SuitStatsPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        //contentPane.setSize(500,575);
        //contentPane.setSize(600, 725);
        contentPane.setBounds(50, 75, 500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

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
            /*
            contentPane.setVisible(false);
            new FunctionSelection().setVisible(true);

             */

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            topFrame.setVisible(false);
            FunctionSelection panel = new FunctionSelection();
            panel.setTitle("Totoro Ramen Kiosk");
            panel.setSize(600, 725);
            panel.setVisible(true);



        });



        //this.setSize(500,575);
        //this.setSize(600, 725);
        this.getContentPane().add(contentPane);
        this.setResizable(false);


        Platform.runLater(this::createScene);


    }


    /**
     * Runner procedure for JavaFX application.
     * Invoke {@link SuitStatsPanel#createLineChart)} ()} and load the chart to {@link JFXPanel}.
     */
    private void createScene() {
        backButton.setVisible(true);
        Chart chart = createLineChart();
        jfxPanel.setScene(new Scene(chart));
    }


    /**
     * Creating procedure for the line chart of suits stats.
     * @return {@link LineChart}
     */
    private LineChart createLineChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 25, 5);
        xAxis.setLabel("Weekdays");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(SuitInfo.getSuitName() + " Weekly Stats");

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName(SuitInfo.getSuitName());

        //populating the series with data
        for (int i = 0; i < SuitStats.getWeeklySales().length; ++i) {
            series.getData().add(new XYChart.Data(WeeklyDates.getWeeklyDates()[i], SuitStats.getWeeklySales()[i]));
        }
        lineChart.getData().add(series);

        return lineChart;
    }


    /**
     * Getter.
     * @return {@link SuitStatsPanel#contentPane}
     */
    public JPanel getSuitStatsPanel() {
        return contentPane;
    }
}
