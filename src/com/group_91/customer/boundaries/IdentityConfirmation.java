package com.group_91.customer.boundaries;

import com.group_91.customer.controls.IdentityConfirmationControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for identity confirmation function.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class IdentityConfirmation extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructor.
     */
    public IdentityConfirmation() {
        this.setTitle("Home page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setSize(500,575);
        contentPane.setOpaque(false);
        //contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);

        JLabel label= new JLabel("Have You Registered As A Member?");
        label.setBounds(100, 120, 400, 150);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        contentPane.add(label);


        JButton isMemberButton = new JButton("Yes");
        isMemberButton.setBounds(110, 280, 100, 30);



        JButton isNotMemberButton = new JButton("No");
        isNotMemberButton.setBounds(300, 280, 100, 30);



        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 430, 100, 30);

        contentPane.add(isMemberButton);
        contentPane.add(isNotMemberButton);
        contentPane.add(backButton);


        // Register listener here.
        IdentityConfirmationControl control = new IdentityConfirmationControl(contentPane, isMemberButton, isNotMemberButton, backButton);
        isMemberButton.addActionListener(control);
        isNotMemberButton.addActionListener(control);
        backButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);

    }


    /**
     * Get this panel.
     * @return JPanel
     */
    public JPanel getIdConfirmationPane() {
        return contentPane;
    }

}
