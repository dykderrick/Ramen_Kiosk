package com.group_91.customer.controls;

import com.group_91.customer.boundaries.CustomerSignUp;
import com.group_91.customer.boundaries.IdentityConfirmation;
import com.group_91.customer.boundaries.Payment;
import com.group_91.customer.boundaries.RequestSignUp;
import com.group_91.customer.entities.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.RequestSignUp}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class RequestSignUpControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton wantMemberButton, notWantMemberButton, backButton;


    /**
     * Constructor.
     * @param contentPane main panel for {@link com.group_91.customer.boundaries.RequestSignUp}.
     * @param wantMemberButton Select want-to-be-member button.
     * @param notWantMemberButton Select not-want-to-be-member button.
     * @param backButton Select back-to-previous-panel button.
     */
    public RequestSignUpControl(JPanel contentPane, JButton wantMemberButton, JButton notWantMemberButton, JButton backButton) {
        this.contentPane = contentPane;
        this.wantMemberButton = wantMemberButton;
        this.notWantMemberButton = notWantMemberButton;
        this.backButton = backButton;
    }


    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.RequestSignUp} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wantMemberButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new CustomerSignUp().getCustomerSignUpPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == notWantMemberButton) {
            new Customer();  // non-membership customer

            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new Payment().getCustomerPayPane());  //.......
            contentPane.revalidate();

        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new IdentityConfirmation().getIdConfirmationPane());  //.......
            contentPane.revalidate();
        }
    }
}
