package com.group_91.customer.boundaries;

import com.group_91.customer.controls.SignInControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for Customer Sign-in panel.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class CustomerSignIn extends JFrame {
    private final JPanel contentPane;

    /**
     * Constructor.
     */
    public CustomerSignIn() {
        contentPane = new JPanel();
        contentPane.setSize(500,575);
        contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        /*
        JLabel photo= new JLabel(new ImageIcon("./img/head.png"));
        photo.setBounds(0, 168, 200, 200);
        photo.setFont(new Font("Times New Roman",Font.ITALIC,18));
        contentPane.add(photo);

         */

        JLabel j3 = new JLabel(new ImageIcon("./img/mao.png"));
        j3.setBounds(50, 0, 200, 200);
        contentPane.add(j3);

        JLabel lb = new JLabel("Sign In",JLabel.CENTER);
        lb.setBounds(190, 100, 100, 30);
        lb.setFont(new Font("Times New Roman",Font.BOLD,25));
        contentPane.add(lb);

        JLabel customerIDLabel = new JLabel("Phone/Email: ",JLabel.LEFT);  // 默认用手机号/邮箱登陆
        customerIDLabel.setBounds(100, 210, 130, 20);
        customerIDLabel.setFont(new Font("Times New ROman", Font.BOLD,16));
        contentPane.add(customerIDLabel);

        JTextField usernameTextField = new JTextField(15);
        usernameTextField.setBounds(230, 212, 150, 20);
        contentPane.add(usernameTextField);

        JLabel lb_password = new JLabel("Password: ",JLabel.LEFT);
        lb_password.setBounds(100, 272, 100, 20);
        lb_password.setFont(new Font("Times New ROman", Font.BOLD,16));
        contentPane.add(lb_password);

        // v3修改: 改成JPasswordField
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(230, 272, 150, 20);
        contentPane.add(passwordField);

        JLabel loginMessageLabel = new JLabel("", JLabel.CENTER);
        loginMessageLabel.setBounds(100, 330, 300, 20);
        loginMessageLabel.setForeground(Color.RED);
        contentPane.add(loginMessageLabel);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(100, 440, 120, 40);
        contentPane.add(signInButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(280, 440, 120, 40);
        contentPane.add(quitButton);


        JToggleButton memberNoSignInButton = new JToggleButton("Use Member Number to sign in");
        memberNoSignInButton.setBounds(100, 370, 300, 30);
        // 小功能不放入control
        memberNoSignInButton.addActionListener(e -> {
            if (memberNoSignInButton.isSelected()) {
                customerIDLabel.setText("Member Number: ");
                //usePhoneOrEmail = false;
                SignInControl.setUsePhoneOrEmail(false);
            }
            else {
                customerIDLabel.setText("Phone/Email: ");
                //usePhoneOrEmail = true;
                SignInControl.setUsePhoneOrEmail(true);
            }
        });
        contentPane.add(memberNoSignInButton);


        // Register listener here.
        SignInControl control = new SignInControl(usernameTextField, passwordField, contentPane, signInButton, quitButton, memberNoSignInButton, loginMessageLabel);
        signInButton.addActionListener(control);
        quitButton.addActionListener(control);


        contentPane.setSize(500,575);
    }


    /**
     * Get this panel.
     * @return JPanel.
     */
    public JPanel getCustomerSignInPane() {
        return contentPane;
    }

}
