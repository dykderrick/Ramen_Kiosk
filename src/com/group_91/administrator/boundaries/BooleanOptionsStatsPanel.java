package com.group_91.administrator.boundaries;

import com.group_91.administrator.entities.BooleanOptionsStats;
import com.group_91.administrator.entities.WeeklyDates;
import com.group_91.customer.entities.SuitInfo;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for querying boolean options stats panel.
 *
 * @author Yingke Ding
 */
public class BooleanOptionsStatsPanel extends JFrame {
    private final JPanel contentPane;
    private final JFXPanel jfxPanel;
    private final JButton backButton;


    /**
     * Construct the frame.
     * Show the bar chart on the top and a button in the bottom for back to previous page.
     */
    public BooleanOptionsStatsPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
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


        Platform.runLater(this::createScene);

    }


    /**
     * Runner procedure for JavaFX application.
     * Invoke {@link BooleanOptionsStatsPanel#createBarChart()} and load the chart to {@link JFXPanel}.
     */
    private void createScene() {
        backButton.setVisible(true);
        Chart chart = createBarChart();
        jfxPanel.setScene(new Scene(chart));
    }


    /**
     * Creating procedure for the bar chart of boolean options stats.
     * @return {@link BarChart} of boolean options stat.
     */
    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(0, 15, 5);

        xAxis.setLabel("Weekdays");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Boolean Options Stat");

        String[] booleanOptionsNames = SuitInfo.getBooleanOptionsNames();
        XYChart.Series[] seriesArray = new XYChart.Series[booleanOptionsNames.length];

        for (int i = 0; i < seriesArray.length; ++i) {
            seriesArray[i] = new XYChart.Series();
            seriesArray[i].setName(booleanOptionsNames[i]);

            Integer[] values = BooleanOptionsStats.getChoiceWeeklyStats(booleanOptionsNames[i]);

            for (int j = 0; j < values.length; ++j) {
                seriesArray[i].getData().add(new XYChart.Data(WeeklyDates.getWeeklyDates()[j], values[j]));
            }

        }

        barChart.getData().addAll(seriesArray);


        return barChart;
    }


    /**
     * Getter.
     * @return {@link BooleanOptionsStatsPanel#contentPane}
     */
    public JPanel getBooleanOptionsStatsPanel() {
        return contentPane;
    }


}
