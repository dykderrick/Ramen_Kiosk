package com.group_91.customer.boundaries;

import com.group_91.customer.controls.SignUpControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for Customer Sign-up panel.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class CustomerSignUp extends JFrame {
    private final JPanel contentPane;


    public CustomerSignUp() throws HeadlessException {
        this.setTitle("Sign Up");
        contentPane = new JPanel();
        contentPane.setSize(500,575);
        contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel j3 = new JLabel(new ImageIcon("./img/signup.png"));
        j3.setBounds(140, 410, 200, 200);
        contentPane.add(j3);

        JLabel lb = new JLabel("Sign Up",JLabel.CENTER);
        lb.setBounds(190, 50, 100, 30);
        lb.setFont(new Font("Times New Roman",Font.BOLD,25));
        contentPane.add(lb);

        JLabel lb_enter = new JLabel("Please enter the following information",JLabel.CENTER);
        lb_enter.setBounds(90, 100, 290, 30);
        lb_enter.setFont(new Font("Times New Roman",Font.BOLD,16));
        contentPane.add(lb_enter);

        JLabel lb_notice = new JLabel("(Notice: Fill in all of the blanks.)",JLabel.CENTER);
        lb_notice.setBounds(10, 120, 470, 30);
        lb_notice.setFont(new Font("Times New Roman",Font.BOLD,12));
        lb_notice.setForeground(Color.RED);
        contentPane.add(lb_notice);

        JLabel lb_firstName = new JLabel("First Name: ",JLabel.LEFT);
        lb_firstName.setBounds(105, 161, 100, 20);
        lb_firstName.setFont(new Font("Times New Roman", Font.BOLD,16));
        contentPane.add(lb_firstName);
        JTextField firstNameTextField = new JTextField(15);
        firstNameTextField.setBounds(218, 163, 150, 20);
        contentPane.add(firstNameTextField);

        JLabel lb_lastName = new JLabel("Last Name: ",JLabel.LEFT);
        lb_lastName.setBounds(105, 211, 100, 20);
        lb_lastName.setFont(new Font("Times New Roman", Font.BOLD,16));
        contentPane.add(lb_lastName);
        JTextField lastNameTextField = new JTextField(15);
        lastNameTextField.setBounds(218, 213, 150, 20);
        contentPane.add(lastNameTextField);

        JLabel lb_Gender = new JLabel("Gender: ",JLabel.LEFT);
        lb_Gender.setBounds(105, 261, 100, 20);
        lb_Gender.setFont(new Font("Times New Roman", Font.BOLD,16));
        contentPane.add(lb_Gender);

        JLabel nan= new JLabel(new ImageIcon("./img/nan.png"));
        nan.setBounds(150, 261, 150, 20);
        JLabel nv= new JLabel(new ImageIcon("./img/nv.png"));
        nv.setBounds(240, 261, 150, 20);
        contentPane.add(nv);
        contentPane.add(nan);


        ButtonGroup group = new ButtonGroup();
        JRadioButton maleButton = new JRadioButton();
        JRadioButton femaleButton = new JRadioButton();

        maleButton.setBounds(232, 261, 30, 20);
        maleButton.setFont(new Font("Times New Roman", Font.BOLD,16));

        femaleButton.setBounds(320, 261, 30, 20);
        femaleButton.setFont(new Font("Times New Roman", Font.BOLD,16));

        group.add(maleButton);
        group.add(femaleButton);
        contentPane.add(maleButton);
        contentPane.add(femaleButton);


        JLabel lb_phoneOrEmail = new JLabel("Mobile / Email: ",JLabel.LEFT);
        lb_phoneOrEmail.setBounds(105, 311, 120, 20);
        lb_phoneOrEmail.setFont(new Font("Times New Roman", Font.BOLD,16));
        contentPane.add(lb_phoneOrEmail);
        JTextField phoneEmailTextField = new JTextField(15);
        phoneEmailTextField.setBounds(218, 313, 150, 20);
        contentPane.add(phoneEmailTextField);


        JLabel lb_password = new JLabel("Password: ",JLabel.LEFT);
        lb_password.setBounds(105, 381, 100, 20);
        lb_password.setFont(new Font("Times New Roman", Font.BOLD,16));
        contentPane.add(lb_password);

        // v3修改: 改为JPasswordField
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(218, 381, 150, 20);
        contentPane.add(passwordField);

        JLabel errorMessageLabel = new JLabel("", JLabel.LEFT);
        errorMessageLabel.setBounds(100, 350, 300, 20);
        errorMessageLabel.setForeground(Color.RED);
        contentPane.add(errorMessageLabel);

        // use HTML to get new line in label.
        JLabel successMessageLabel = new JLabel("<html>SIGN UP SUCCESSFULLY!<br/>" +
                "YOUR REGISTRATION DETAILS HAS BEEN SAVED TO THE FOLDER.</html>");
        successMessageLabel.setBounds(120, 480, 300, 70);
        successMessageLabel.setForeground(Color.GREEN);
        successMessageLabel.setVisible(false);
        contentPane.add(successMessageLabel);


        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(90, 420, 120, 40);
        contentPane.add(signUpButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(260, 420, 120, 40);
        contentPane.add(quitButton);


        // Register listener here.
        SignUpControl control = new SignUpControl(contentPane, firstNameTextField, lastNameTextField, phoneEmailTextField, passwordField, maleButton, femaleButton, errorMessageLabel, successMessageLabel, signUpButton, quitButton);
        signUpButton.addActionListener(control);
        quitButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500,575);

    }


    /**
     * Get this panel.
     * @return JPanel.
     */
    public JPanel getCustomerSignUpPane() {
        return contentPane;
    }


}
