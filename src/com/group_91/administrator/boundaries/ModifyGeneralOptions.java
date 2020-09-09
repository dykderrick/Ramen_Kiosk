package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.ModifyGeneralOptionsControl;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Interface for Modify General Options panel.
 * Panel, Buttons, comboBox defined here.
 *
 * @author Yingke Ding
 */
public class ModifyGeneralOptions extends JFrame {
    private final JPanel contentPane;
    private String selectedGeneralOptionName = "soup";  // In case of NullPointerException


    /**
     * Constructs the frame.
     * two {@link JComboBox} are used to show the general option name and choices for manager to select.
     * two {@link JRadioButton}s for manager to decide stock in/out.
     * two {@link JButton}s are used to receive manager decision of actions.
     */
    public ModifyGeneralOptions() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("./img/genmod.png"));
        lblNewLabel.setBounds(85, 20, 330, 180);
        contentPane.add(lblNewLabel);

        String[] generalOptionNames = SuitInfo.getGeneralOptionsNames();
        JComboBox<String> generalOptionNamesComboBox = new JComboBox<>(generalOptionNames);
        generalOptionNamesComboBox.setBounds(100, 240, 80, 80);
        contentPane.add(generalOptionNamesComboBox);

        String[] choicesNames = SuitInfo.getGeneralChoicesNames(selectedGeneralOptionName);
        JComboBox<String> choicesComboBox = new JComboBox<>(choicesNames);
        choicesComboBox.setBounds(300, 240, 80, 80);
        contentPane.add(choicesComboBox);

        // comboBox listener. Will not separated to control class due to tiny functionality.
        generalOptionNamesComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedGeneralOptionName = (String) generalOptionNamesComboBox.getSelectedItem();
                choicesComboBox.removeAllItems();
                String[] choicesNames1 = SuitInfo.getGeneralChoicesNames(selectedGeneralOptionName);
                for (String name : choicesNames1) {
                    choicesComboBox.addItem(name);
                }
            }
        });


        ButtonGroup group = new ButtonGroup();

        JRadioButton availableRadioButton = new JRadioButton("STOCK IN");
        availableRadioButton.setBounds(200, 320, 130, 30);
        availableRadioButton.setOpaque(false);
        group.add(availableRadioButton);
        contentPane.add(availableRadioButton);

        JRadioButton notAvailableRadioButton = new JRadioButton("STOCK OUT");
        notAvailableRadioButton.setBounds(200, 360, 130, 30);
        notAvailableRadioButton.setOpaque(false);
        group.add(notAvailableRadioButton);
        contentPane.add(notAvailableRadioButton);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 400, 100, 40);
        contentPane.add(confirmButton);


        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 460, 100, 40);
        contentPane.add(backButton);


        // Register control here.
        ModifyGeneralOptionsControl control = new ModifyGeneralOptionsControl(contentPane, confirmButton, backButton,
                generalOptionNamesComboBox, choicesComboBox, availableRadioButton);
        confirmButton.addActionListener(control);
        backButton.addActionListener(control);


        this.setSize(500, 575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);

    }


    /**
     * Getter.
     * @return {@link ModifyGeneralOptions#contentPane}
     */
    public JPanel getModifyGeneralOptionsPane() {
        return contentPane;
    }

}
