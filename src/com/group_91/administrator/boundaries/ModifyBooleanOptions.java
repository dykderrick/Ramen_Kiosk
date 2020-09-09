package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.ModifyBooleanOptionsControl;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Interface of Modify Boolean Options panel.
 * Panel, Buttons, comboBox defined here.
 *
 * @author Yingke Ding
 */
public class ModifyBooleanOptions extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructs the frame.
     * a {@link JComboBox} is used to get boolean option names.
     * a {@link ButtonGroup} is used to receive manager selection of stock in/out.
     * two {@link JButton}s are used to receive manager decision of actions.
     */
    public ModifyBooleanOptions() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("./img/boomod.png"));
        lblNewLabel.setBounds(85, 20, 330, 180);
        contentPane.add(lblNewLabel);

        String[] booleanOptionsNames = SuitInfo.getBooleanOptionsNames();
        JComboBox<String> comboBox = new JComboBox<>(booleanOptionsNames);
        comboBox.setBounds(200, 240, 100, 80);
        contentPane.add(comboBox);


        ButtonGroup group = new ButtonGroup();

        JRadioButton availableRadioButton = new JRadioButton("STOCK IN");
        availableRadioButton.setBounds(200, 320, 130, 30);
        availableRadioButton.setOpaque(false);
        contentPane.add(availableRadioButton);

        JRadioButton notAvailableRadioButton = new JRadioButton("STOCK OUT");
        notAvailableRadioButton.setBounds(200, 360, 130, 30);
        notAvailableRadioButton.setOpaque(false);
        contentPane.add(notAvailableRadioButton);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 400, 100, 40);
        contentPane.add(confirmButton);


        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 460, 100, 40);
        contentPane.add(backButton);

        ModifyBooleanOptionsControl control = new ModifyBooleanOptionsControl(contentPane, confirmButton, backButton, comboBox, availableRadioButton);
        confirmButton.addActionListener(control);
        backButton.addActionListener(control);



        this.setSize(500, 575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);

    }


    /**
     * Getter.
     * @return {@link ModifyBooleanOptions#contentPane}
     */
    public JPanel getModifyBooleanOptionsPane() {
        return contentPane;
    }


}
