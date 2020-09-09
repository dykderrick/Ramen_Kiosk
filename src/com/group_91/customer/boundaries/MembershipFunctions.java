package com.group_91.customer.boundaries;

import com.group_91.customer.controls.MembershipFunctionsControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for Membership functions.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class MembershipFunctions extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructor.
     */
    public MembershipFunctions() {
        this.setTitle("Membership Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setSize(500,575);
        //contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel label= new JLabel("Please choose what you want to do");
        label.setBounds(130, 89, 300, 150);
        label.setFont(new Font("Times New Roman",Font.BOLD,20));
        contentPane.add(label);


        JButton viewStampsButton = new JButton("View Stamps");
        viewStampsButton.setBounds(190, 260, 120, 30);

        JLabel p1 = new JLabel(new ImageIcon("./img/star.png"));
        p1.setBounds(90, 230, 100, 100);
        contentPane.add(p1);

        JButton payButton = new JButton("Pay Now");
        payButton.setBounds(190, 360, 120, 30);

        JLabel p2 = new JLabel(new ImageIcon("./img/qian.png"));
        p2.setBounds(90, 330, 100, 100);
        contentPane.add(p2);

        contentPane.add(viewStampsButton);
        contentPane.add(payButton);

        MembershipFunctionsControl control = new MembershipFunctionsControl(contentPane, viewStampsButton, payButton);
        viewStampsButton.addActionListener(control);
        payButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);
    }


    /**
     * Get this panel.
     * @return JPanel
     */
    public JPanel getMembershipChoicesPane() {
        return contentPane;
    }

}
