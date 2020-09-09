package com.group_91.customer.controls;

import com.group_91.customer.boundaries.MembershipFunctions;
import com.group_91.customer.boundaries.Payment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.StampsViewing}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class StampsViewingControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton payButton, backButton;


    /**
     * Constructor.
     * @param contentPane main panel for {@link com.group_91.customer.boundaries.StampsViewing}.
     * @param payButton Select pay button.
     * @param backButton Select back button.
     */
    public StampsViewingControl(JPanel contentPane, JButton payButton, JButton backButton) {
        this.contentPane = contentPane;
        this.payButton = payButton;
        this.backButton = backButton;
    }

    /**
     * Invoked when buttons of {@link com.group_91.customer.boundaries.StampsViewing} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new MembershipFunctions().getMembershipChoicesPane());
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
