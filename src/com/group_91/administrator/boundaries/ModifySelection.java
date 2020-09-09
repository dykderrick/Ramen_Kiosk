package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.ModifySelectionControl;

import javax.swing.*;
import java.awt.*;

/**
 * General interface for modification function of the project.
 * Panels, Buttons initiated here.
 *
 * @author Yingke Ding
 */
public class ModifySelection extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructs the frame.
     * Will use {@link JButton}s for user decision input.
     */
    public ModifySelection() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("./img/mod.png"));
        lblNewLabel.setBounds(85, 20, 330, 180);
        contentPane.add(lblNewLabel);


        JButton modifyGeneralOptionButton = new JButton("Modify General Options Status");
        modifyGeneralOptionButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        modifyGeneralOptionButton.setBounds(90, 230, 300, 40);
        contentPane.add(modifyGeneralOptionButton);


        JButton modifyBooleanOptionButton = new JButton("Modify Boolean Options Status");
        modifyBooleanOptionButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        modifyBooleanOptionButton.setBounds(90, 280, 300, 40);
        contentPane.add(modifyBooleanOptionButton);


        JButton modifyAddOnButton = new JButton("Modify Add On Price and Status");
        modifyAddOnButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        modifyAddOnButton.setBounds(90, 330, 300, 40);
        contentPane.add(modifyAddOnButton);


        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        backButton.setBounds(90, 420, 300, 40);
        contentPane.add(backButton);


        // Register control here
        ModifySelectionControl control = new ModifySelectionControl(contentPane, modifyGeneralOptionButton,
                modifyBooleanOptionButton, modifyAddOnButton, backButton);
        modifyGeneralOptionButton.addActionListener(control);
        modifyBooleanOptionButton.addActionListener(control);
        modifyAddOnButton.addActionListener(control);
        backButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500, 575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);


    }


    /**
     * Getter.
     * @return {@link ModifySelection#contentPane}
     */
    public JPanel getModifyPanel() {
        return contentPane;
    }

}
