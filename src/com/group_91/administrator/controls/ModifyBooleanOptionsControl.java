package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.ModifySelection;
import com.group_91.administrator.entities.BooleanOptionModification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.administrator.boundaries.ModifyBooleanOptions}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class ModifyBooleanOptionsControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton confirmButton, backButton;
    private final JComboBox<String> comboBox;
    private final JRadioButton availableRadioButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.administrator.boundaries.ModifyBooleanOptions}
     * @param confirmButton Select confirm button
     * @param backButton Select back button
     * @param comboBox {@link JComboBox} for manager to select boolean option
     * @param availableRadioButton {@link JRadioButton} indicating manager decesion on stock in
     */
    public ModifyBooleanOptionsControl(JPanel contentPane, JButton confirmButton, JButton backButton,
                                       JComboBox<String> comboBox, JRadioButton availableRadioButton) {
        this.contentPane = contentPane;
        this.confirmButton = confirmButton;
        this.backButton = backButton;
        this.comboBox = comboBox;
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
            String selectedOption = (String) comboBox.getSelectedItem();
            boolean isAvailable = availableRadioButton.isSelected();
            try {
                BooleanOptionModification.setBooleanChoiceAvailability(selectedOption, isAvailable);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            String inOrOut;
            if (isAvailable) {
                inOrOut = "IN";
            }
            else inOrOut = "OUT";
            JOptionPane.showMessageDialog(null, "Successfully set " + selectedOption + " to STOCK " + inOrOut +".");
        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifySelection().getModifyPanel());
            contentPane.revalidate();
        }
    }
}
