package com.group_91.customer.controls;

import com.alibaba.fastjson.JSONObject;
import com.group_91.customer.boundaries.CustomerSignIn;
import com.group_91.customer.boundaries.RequestSignUp;
import com.group_91.customer.entities.Customer;
import com.group_91.customer.entities.RegistrationTicket;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.CustomerSignUp}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class SignUpControl implements ActionListener {
    private final JPanel contentPane;
    private final JTextField firstNameTextField, lastNameTextField, phoneEmailTextField;
    private final JPasswordField passwordField;
    private final JRadioButton maleButton, femaleButton;
    private final JLabel errorMessageLabel, successMessageLabel;
    private final JButton signUpButton, backButton;


    /**
     * Constructor.
     * @param contentPane main panel for {@link com.group_91.customer.boundaries.CustomerSignUp}.
     * @param firstNameTextField {@link JTextField} for user to type in first name.
     * @param lastNameTextField {@link JTextField} for user to type in last name.
     * @param phoneEmailTextField {@link JTextField} for user to type in phone/email (or <code>memberNo</code>).
     * @param passwordField {@link JPasswordField} for user to type in password.
     * @param maleButton Select male button.
     * @param femaleButton Select female button.
     * @param errorMessageLabel Shows some error message if failed to be signed up.
     * @param successMessageLabel Shows some message if succeeded to be signed up.
     * @param signUpButton Select sign-up button.
     * @param backButton Select quit button.
     */
    public SignUpControl(JPanel contentPane, JTextField firstNameTextField, JTextField lastNameTextField, JTextField phoneEmailTextField, JPasswordField passwordField, JRadioButton maleButton, JRadioButton femaleButton, JLabel errorMessageLabel, JLabel successMessageLabel, JButton signUpButton, JButton backButton) {
        this.contentPane = contentPane;
        this.firstNameTextField = firstNameTextField;
        this.lastNameTextField = lastNameTextField;
        this.phoneEmailTextField = phoneEmailTextField;
        this.passwordField = passwordField;
        this.maleButton = maleButton;
        this.femaleButton = femaleButton;
        this.errorMessageLabel = errorMessageLabel;
        this.successMessageLabel = successMessageLabel;
        this.signUpButton = signUpButton;
        this.backButton = backButton;
    }


    /**
     * Set a specific message to the label.
     * @param errorMessageLabel JLabel.
     * @param message String.
     */
    private void setErrorMessageLabel(JLabel errorMessageLabel, String message) {
        errorMessageLabel.setText(message);
    }


    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.CustomerSignUp} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new RequestSignUp().getMemberQuesPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == signUpButton) {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            boolean isMale = maleButton.isSelected();  // 第一个radioButton是male
            boolean isFemale = femaleButton.isSelected();
            String gender = "";
            if (isMale && !isFemale) {
                gender = "M";
            }
            else if (isFemale && !isMale){
                gender = "F";
            }
            String userLoginID = phoneEmailTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // Check info.
            String errorMessage = "ERROR: ";
            boolean isAllCorrect = true;
            if (!Customer.isValidName(firstName)) {
                errorMessage += "Please check your first name. ";
                isAllCorrect = false;
            }

            if (!Customer.isValidName(lastName)) {
                errorMessage += "Please check your last name. ";
                isAllCorrect = false;
            }

            if (!Customer.isValidGender(gender)) {
                errorMessage += "You have to select a gender. ";
                isAllCorrect = false;
            }

            if (!Customer.isValidUserID(userLoginID)) {
                errorMessage += "Please check your phone or email. ";
                isAllCorrect = false;
            }

            if (!Customer.isValidPassword(password)) {
                errorMessage += "Password should be at least 6 characters.";
                isAllCorrect = false;
            }

            if (isAllCorrect) {
                try {
                    JSONObject customer = Customer.createCustomer(firstName, lastName, gender, userLoginID, password);
                    String memberNo = (String) customer.get("memberNo");
                    Jsons.saveJson(customer, "customers");
                    String registrationTicket = new RegistrationTicket(firstName, lastName, gender, userLoginID, memberNo, password).getTicket();
                    String filePath = "./tickets/registration_tickets/" + memberNo + ".txt";
                    Utils.saveStringToTextFile(registrationTicket, filePath);
                    Utils.openTextFile(filePath);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                successMessageLabel.setVisible(true);

                Timer timer = new Timer(3000, e1 -> {
                    contentPane.removeAll();
                    contentPane.repaint();
                    contentPane.add(new CustomerSignIn().getCustomerSignInPane());
                    contentPane.revalidate();
                });
                timer.setRepeats(false);
                timer.start();

            }
            else {  // Show some mistakes.
                setErrorMessageLabel(errorMessageLabel, errorMessage);
            }
        }
    }
}
