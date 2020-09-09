package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.AdminSignInControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface code for Administrator sign-in panel.
 *
 * @author Yingke Ding
 */
public class AdminSignIn extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructs the frame.
     * Procedure is as the same as {@link com.group_91.customer.boundaries.CustomerSignIn}
     */
    public AdminSignIn() {
        this.setTitle("Ramen Kiosk Admin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lb = new JLabel("Sign In",JLabel.CENTER);
        lb.setBounds(190, 100, 100, 30);
        lb.setFont(new Font("Times New Roman",Font.BOLD,25));
        contentPane.add(lb);

        JLabel j3 = new JLabel(new ImageIcon("./img/mao.png"));
        j3.setBounds(50, 0, 200, 200);
        contentPane.add(j3);

        JLabel managerIDLabel = new JLabel("ID: ",JLabel.LEFT);
        managerIDLabel.setBounds(125, 210, 130, 20);
        managerIDLabel.setFont(new Font("Times New ROman", Font.BOLD,16));
        contentPane.add(managerIDLabel);

        JTextField managerIDTextField = new JTextField(15);
        managerIDTextField.setBounds(230, 212, 150, 20);
        contentPane.add(managerIDTextField);

        JLabel lb_password = new JLabel("Password: ",JLabel.LEFT);
        lb_password.setBounds(125, 272, 100, 20);
        lb_password.setFont(new Font("Times New ROman", Font.BOLD,16));
        contentPane.add(lb_password);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(230, 272, 150, 20);
        contentPane.add(passwordField);

        JLabel loginMessageLabel = new JLabel("", JLabel.CENTER);
        loginMessageLabel.setBounds(100, 330, 300, 20);
        contentPane.add(loginMessageLabel);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(125, 380, 100, 40);
        contentPane.add(signInButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(280, 380, 100, 40);
        contentPane.add(quitButton);


        // Register listener here.
        AdminSignInControl control = new AdminSignInControl(contentPane, managerIDTextField, passwordField, signInButton, quitButton, loginMessageLabel);
        signInButton.addActionListener(control);
        quitButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500, 575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);


    }


    /**
     * Getter.
     * @return {@link AdminSignIn#contentPane}
     */
    public JPanel getAdminSignInPanel() {
        return contentPane;
    }



}
