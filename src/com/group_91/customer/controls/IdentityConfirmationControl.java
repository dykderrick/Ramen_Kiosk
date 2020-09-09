package com.group_91.customer.controls;

import com.group_91.customer.boundaries.CustomerSignIn;
import com.group_91.customer.boundaries.DiningOptions;
import com.group_91.customer.boundaries.RequestSignUp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.IdentityConfirmation}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class IdentityConfirmationControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton isMemberButton;
    private final JButton isNotMemberButton;
    private final JButton backButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.IdentityConfirmation}.
     * @param isMemberButton Select is-member button.
     * @param isNotMemberButton Select is-not-member button.
     * @param backButton Select back-to-previous-panel button.
     */
    public IdentityConfirmationControl(JPanel contentPane, JButton isMemberButton, JButton isNotMemberButton, JButton backButton) {
        this.contentPane = contentPane;
        this.isMemberButton = isMemberButton;
        this.isNotMemberButton = isNotMemberButton;
        this.backButton = backButton;
    }


    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.IdentityConfirmation} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new DiningOptions().getDiningOptionsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == isMemberButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new CustomerSignIn().getCustomerSignInPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == isNotMemberButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new RequestSignUp().getMemberQuesPane());
            contentPane.revalidate();
        }
    }
}
