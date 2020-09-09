package com.group_91.customer.boundaries;

import com.group_91.customer.controls.RequestSignUpControl;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to query the customer to sign up or not.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class RequestSignUp extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructor.
     */
    public RequestSignUp() {
        this.setTitle("Home page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setSize(500,575);
        //contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel label= new JLabel("Do You Want to Join the Member?");
        label.setBounds(110, 120, 400, 150);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        contentPane.add(label);


        JButton wantMemberButton = new JButton("Yes");
        wantMemberButton.setBounds(100, 210, 100, 30);
        wantMemberButton.setBounds(110, 280, 100, 30);


        JButton notWantMemberButton = new JButton("No");
        notWantMemberButton.setBounds(280, 210, 100, 30);
        notWantMemberButton.setBounds(300, 280, 100, 30);


        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 270, 100, 30);
        backButton.setBounds(200, 410, 100, 30);

        contentPane.add(wantMemberButton);
        contentPane.add(notWantMemberButton);
        contentPane.add(backButton);

        RequestSignUpControl control = new RequestSignUpControl(contentPane, wantMemberButton, notWantMemberButton, backButton);
        wantMemberButton.addActionListener(control);
        notWantMemberButton.addActionListener(control);
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
    public JPanel getMemberQuesPane() {
        return contentPane;
    }
}
