package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.ModifyAddOnsControl;
import com.group_91.customer.entities.AddOnsInfo;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;

/**
 * Interface class for Modify add on function.
 * Panel, Buttons initiated here.
 *
 * @author Yingke Ding
 */
public class ModifyAddOns extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructs the frame.
     * Invoke <code>addOnNames.length</code> number of {@link JLabel}s, {@link JTextField}s and {@link JToggleButton}s are defined.
     */
    public ModifyAddOns() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("./img/addmod.png"));
        lblNewLabel.setBounds(85, 20, 330, 180);
        contentPane.add(lblNewLabel);

        String[] addOnNames = AddOnsInfo.getAddOnsNames();

        JLabel[] labels = new JLabel[addOnNames.length];
        JTextField[] priceTextFields = new JTextField[addOnNames.length];
        JToggleButton[] stockOutToggleButtons = new JToggleButton[addOnNames.length];
        for (int i = 0; i < labels.length; ++i) {
            labels[i] = new JLabel(addOnNames[i]);
            labels[i].setBounds(50, 210 + 40 * i, 130, 50);
            contentPane.add(labels[i]);

            double originalPrice = ((BigDecimal) AddOnsInfo.getAddOnsInfo().get(addOnNames[i])).doubleValue();
            if (originalPrice < 0) {
                originalPrice = - originalPrice;  // abs
            }
            priceTextFields[i] = new JTextField(String.valueOf(originalPrice));
            priceTextFields[i].setBounds(200, 220 + 40 * i, 50, 40);
            contentPane.add(priceTextFields[i]);

            stockOutToggleButtons[i] = new JToggleButton("MAKE IT STOCKED OUT");
            stockOutToggleButtons[i].setBounds(300, 220 + 40 * i, 180, 35);

            // state change listener
            stockOutToggleButtons[i].addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JToggleButton button = (JToggleButton) e.getSource();
                    button.setText("STOCKED OUT");
                    button.setForeground(Color.RED);
                }
                else {
                    JToggleButton button = (JToggleButton) e.getSource();
                    button.setText("MAKE IT STOCKED OUT");
                    button.setForeground(Color.black);
                }
            });
            contentPane.add(stockOutToggleButtons[i]);

        }

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(150, 300, 300, 50);
        contentPane.add(messageLabel);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 400, 100, 40);
        contentPane.add(confirmButton);


        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 460, 100, 40);
        contentPane.add(backButton);


        // Register control here.
        ModifyAddOnsControl control = new ModifyAddOnsControl(contentPane, confirmButton, backButton, priceTextFields, stockOutToggleButtons, messageLabel);
        confirmButton.addActionListener(control);
        backButton.addActionListener(control);

        this.setSize(500, 575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);

    }


    /**
     * Getter.
     * @return {@link ModifyAddOns#contentPane}
     */
    public JPanel getModifyAddOnsPane() {
        return contentPane;
    }

}
