package com.group_91.customer.boundaries;

import com.group_91.customer.controls.StampsViewingControl;
import com.group_91.customer.entities.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Interface for viewing stamps function.
 *
 * @author Yingke Ding
 */
public class StampsViewing extends JFrame {
    private final JPanel contentPane;

    /**
     * Constructor.
     */
    public StampsViewing() {
        this.setTitle("Membership");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);



        JLabel backgroundLabel = new JLabel(new ImageIcon("./img/tou.png"));
        backgroundLabel.setBounds(200, 89, 100, 100);
        contentPane.add(backgroundLabel);

        if (Customer.getStatus()) {  // signed-in customer
            JLabel lblNewLabel = new JLabel(Customer.getUsername(),JLabel.LEFT);
            lblNewLabel.setBounds(200, 190, 204, 15);
            contentPane.add(lblNewLabel);

            JLabel nameLabel = new JLabel(Customer.getName(),JLabel.LEFT);
            nameLabel.setBounds(200, 220, 300, 15);
            contentPane.add(nameLabel);

            JLabel stampsLabel = new JLabel(Customer.getStamps() + " Stamps remaining",JLabel.LEFT);
            stampsLabel.setBounds(200, 250, 204, 15);
            contentPane.add(stampsLabel);

        }

        else {
            JLabel signedInLabel = new JLabel("Please sign in to use stamps.");
            signedInLabel.setBounds(200, 250, 204, 15);
            contentPane.add(signedInLabel);
        }

        JButton payButton = new JButton("Pay now");
        payButton.setBounds(190, 333, 120, 23);
        contentPane.add(payButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 403, 120, 23);
        contentPane.add(backButton);

        StampsViewingControl control = new StampsViewingControl(contentPane, payButton, backButton);
        payButton.addActionListener(control);
        backButton.addActionListener(control);



        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);
    }


    /**
     * Get this panel.
     * @return JPanel
     */
    public JPanel getStampsPane() {
        return contentPane;
    }

}
