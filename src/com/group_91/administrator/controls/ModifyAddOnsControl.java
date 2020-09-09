package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.ModifySelection;
import com.group_91.administrator.entities.AddOnsModification;
import com.group_91.customer.entities.AddOnsInfo;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.administrator.boundaries.ModifyAddOns}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class ModifyAddOnsControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton confirmButton, backButton;
    private final JTextField[] priceTextFields;
    private final JToggleButton[] stockOutToggleButtons;
    private final JLabel messageLabel;
    private final String[] addOnNames;
    private boolean isValidPrices;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.administrator.boundaries.ModifyAddOns}
     * @param confirmButton Select confirm button
     * @param backButton Select back button
     * @param priceTextFields {@link JTextField} array to receive manager price input
     * @param stockOutToggleButtons {@link JToggleButton} array to receive manager decision of stock in/out
     */
    public ModifyAddOnsControl(JPanel contentPane, JButton confirmButton,
                               JButton backButton, JTextField[] priceTextFields,
                               JToggleButton[] stockOutToggleButtons, JLabel messageLabel) {
        this.contentPane = contentPane;
        this.confirmButton = confirmButton;
        this.backButton = backButton;
        this.priceTextFields = priceTextFields;
        this.stockOutToggleButtons = stockOutToggleButtons;
        this.messageLabel = messageLabel;
        this.addOnNames = AddOnsInfo.getAddOnsNames();
    }


    /**
     * Get message for {@link ModifyAddOnsControl#messageLabel}
     * @return a String to be set
     */
    private String getMessage() {
        if (isValidPrices) {
            return "All changes have been saved.";
        }
        else {
            return "Prices should be in right format!";
        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            double[] prices = new double[priceTextFields.length];
            boolean[] availabilities = new boolean[stockOutToggleButtons.length];

            for (int i = 0; i < prices.length; ++i) {
                String thisPriceText = priceTextFields[i].getText();
                isValidPrices = Utils.isPositiveNumeric(thisPriceText);

                if (isValidPrices) {
                    prices[i] = Double.parseDouble(priceTextFields[i].getText());
                    availabilities[i] = !stockOutToggleButtons[i].isSelected();
                } else break;
            }

            messageLabel.setText(getMessage());

            if (isValidPrices) {
                messageLabel.setForeground(Color.GREEN);

                // updating json file
                try {
                    AddOnsModification.setAddOnsPricesAndStatuses(addOnNames, prices, availabilities);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else {
                messageLabel.setForeground(Color.RED);
            }

            // After 3000 ms, messageLabel will disappear
            Timer t = new Timer(3000, e1 -> messageLabel.setText(""));
            t.setRepeats(false);
            t.start();


        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifySelection().getModifyPanel());
            contentPane.revalidate();
        }
    }
}
