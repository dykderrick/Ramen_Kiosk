package com.group_91.customer.controls;

import com.group_91.customer.boundaries.Payment;
import com.group_91.customer.boundaries.StampsViewing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.MembershipFunctions}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class MembershipFunctionsControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton viewStampsButton;
    private final JButton payButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.MembershipFunctions}.
     * @param viewStampsButton Select view-stamps button.
     * @param payButton Select pay button.
     */
    public MembershipFunctionsControl(JPanel contentPane, JButton viewStampsButton, JButton payButton) {
        this.contentPane = contentPane;
        this.viewStampsButton = viewStampsButton;
        this.payButton = payButton;
    }


    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.MembershipFunctions} are clicked.
     *
     * @param e the clicking event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewStampsButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new StampsViewing().getStampsPane());
            contentPane.revalidate();
        }

        else if (e.getSource() == payButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new Payment().getCustomerPayPane());
            //contentPane.add(new Payment2().getPaymentPanel());
            contentPane.revalidate();
        }
    }
}
