package com.group_91.administrator.boundaries;

import com.group_91.administrator.entities.GeneralOptionsStats;
import com.group_91.administrator.entities.WeeklyDates;
import com.group_91.customer.entities.SuitInfo;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Interface for querying general options stats panel.
 *
 * @author Yingke Ding
 */
public class GeneralOptionsStatsPanel extends JFrame {
    private final JPanel contentPane;
    private final JFXPanel jfxPanel;
    JRadioButton[] radioButtons;
    private final JButton backButton;
    private String selectedGeneralOption = "soup";  // in case of NullPointerException
    public static final int GENERAL_OPTION_COUNT = 3;


    /**
     * Constructs the frame here.
     */
    public GeneralOptionsStatsPanel() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBounds(50, 75, 500, 575);
        contentPane.setLayout(null);


        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridLayout(1, 3));
        radioButtonsPanel.setBounds(0, 0, 500, 50);
        radioButtons = new JRadioButton[GENERAL_OPTION_COUNT];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < GENERAL_OPTION_COUNT; ++i) {
            radioButtons[i] = new JRadioButton(SuitInfo.getGeneralOptionsNames()[i]);
            radioButtons[i].addItemListener(new RadioButtonListener());
            group.add(radioButtons[i]);
            radioButtonsPanel.add(radioButtons[i]);
        }
        contentPane.add(radioButtonsPanel);



        jfxPanel = new JFXPanel();
        jfxPanel.setPreferredSize(new Dimension(500, 520));

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(jfxPanel, BorderLayout.CENTER);
        chartPanel.setBounds(0, 60, 500, 450);
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
     * Inner control class for {@link GeneralOptionsStatsPanel}.
     * Controls {@link JRadioButton}s in the panel.
     * Not divided into a separated class because of {@link Platform#runLater(Runnable)}
     * can only be called within the panel boundary class.
     */
    class RadioButtonListener implements ItemListener {

        /**
         * Invoked when an item has been selected or deselected by the user.
         * The code written for this method performs the operations
         * that need to occur when an item is selected (or deselected).
         *
         * @param e the event to be processed
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedGeneralOption = ((JRadioButton) e.getSource()).getText();
                Platform.runLater(GeneralOptionsStatsPanel.this::createScene);
            }
        }
    }


    /**
     * Runner procedure for JavaFX application.
     * Invoke {@link GeneralOptionsStatsPanel#createLineChart(String)} ()} and load the chart to {@link JFXPanel}.
     */
    private void createScene() {
        backButton.setVisible(true);
        Chart chart = createLineChart(selectedGeneralOption);
        jfxPanel.setScene(new Scene(chart));
    }


    /**
     * Creating procedure for the line chart of general options stats.
     * @param generalOptionName a specific general option name. Example: "soup"
     * @return {@link LineChart}
     */
    private LineChart<String, Number> createLineChart(String generalOptionName) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 15, 5);
        xAxis.setLabel("Weekdays");
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle(generalOptionName + " Weekly Stats");

        XYChart.Series[] seriesArray = new XYChart.Series[GENERAL_OPTION_COUNT];
        String[] choiceNames = SuitInfo.getGeneralChoicesNames(generalOptionName);

        for (int i = 0; i < GENERAL_OPTION_COUNT; ++i) {
            seriesArray[i] = new XYChart.Series();
            seriesArray[i].setName(choiceNames[i]);

            Integer[] values = GeneralOptionsStats.getWeeklyStats(generalOptionName).get(choiceNames[i]);

            for (int j = 0; j < values.length; ++j) {
                seriesArray[i].getData().add(new XYChart.Data(WeeklyDates.getWeeklyDates()[j], values[j]));
            }

        }

        lineChart.getData().addAll(seriesArray);


        return lineChart;
    }


    /**
     * Getter.
     * @return {@link GeneralOptionsStatsPanel#contentPane}
     */
    public JPanel getGeneralOptionsStatsPanel() {
        return contentPane;
    }

}
