package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.ModifySelection;
import com.group_91.administrator.entities.GeneralOptionModification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.administrator.boundaries.ModifyGeneralOptions}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class ModifyGeneralOptionsControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton confirmButton, backButton;
    private final JComboBox<String> generalOptionNamesComboBox, choicesComboBox;
    private final JRadioButton availableRadioButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.administrator.boundaries.ModifyGeneralOptions}
     * @param confirmButton Select confirm button
     * @param backButton Select back button
     * @param generalOptionNamesComboBox {@link JComboBox} indicating manger input of general option name
     * @param choicesComboBox {@link JComboBox} indicating manger input of general option choice
     * @param availableRadioButton {@link JRadioButton} receiving manager decision of stock in
     */
    public ModifyGeneralOptionsControl(JPanel contentPane, JButton confirmButton, JButton backButton, JComboBox<String> generalOptionNamesComboBox, JComboBox<String> choicesComboBox, JRadioButton availableRadioButton) {
        this.contentPane = contentPane;
        this.confirmButton = confirmButton;
        this.backButton = backButton;
        this.generalOptionNamesComboBox = generalOptionNamesComboBox;
        this.choicesComboBox = choicesComboBox;
        this.availableRadioButton = availableRadioButton;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            String selectedOptionName = (String) generalOptionNamesComboBox.getSelectedItem();
            String selectedChoiceName = (String) choicesComboBox.getSelectedItem();
            boolean isSelectedStockIn = availableRadioButton.isSelected();

            try {
                GeneralOptionModification.setChoiceAvailability(selectedOptionName, selectedChoiceName, isSelectedStockIn);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            String inOrOut;
            if (isSelectedStockIn) {
                inOrOut = "IN";
            }
            else inOrOut = "OUT";
            JOptionPane.showMessageDialog(null, "Successfully set " + selectedOptionName + " " + selectedChoiceName + " to STOCK " + inOrOut +".");

        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifySelection().getModifyPanel());
            contentPane.revalidate();
        }
    }
}
