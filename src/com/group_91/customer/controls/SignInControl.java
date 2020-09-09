package com.group_91.customer.controls;

import com.group_91.drive.HomePage;
import com.group_91.customer.boundaries.MembershipFunctions;
import com.group_91.customer.entities.Customer;
import com.group_91.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.CustomerSignIn}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class SignInControl implements ActionListener {
    private final JTextField usernameTextField;
    private final JPasswordField passwordField;
    private final JPanel contentPane;
    private final JButton signInButton;
    private final JButton quitButton;
    private final JToggleButton memberNoSignInButton;
    private final JLabel loginMessageLabel;
    private static boolean isCorrectInfo = false;
    private static boolean usePhoneOrEmail = true;


    /**
     * Constructor.
     * @param usernameTextField A {@link JTextField} collecting user input of <code>username</code> (or <code>memberNo</code>).
     * @param passwordField a {@link JPasswordField} collecting user input of <code>password</code>.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.CustomerSignIn}.
     * @param signInButton Select sign-in button
     * @param quitButton Select quit button.
     * @param memberNoSignInButton a {@link JToggleButton} indicating whether customer uses <code>memberNo</code> to sign in or not.
     * @param loginMessageLabel Shows some info when successfully logged in.
     */
    public SignInControl(JTextField usernameTextField, JPasswordField passwordField, JPanel contentPane, JButton signInButton, JButton quitButton, JToggleButton memberNoSignInButton, JLabel loginMessageLabel) {
        this.usernameTextField = usernameTextField;
        this.passwordField = passwordField;
        this.contentPane = contentPane;
        this.signInButton = signInButton;
        this.quitButton = quitButton;
        this.memberNoSignInButton = memberNoSignInButton;
        this.loginMessageLabel = loginMessageLabel;
    }


    /**
     * Invoke username-password checking procedure {@link Utils#checkUserIDPassword(String, String, String)}. <p></p>
     * Get different message based on the return results.
     * @param id <code>username</code> or <code>memberNo</code>
     * @param password the raw password collected. Will be MD5 encrypted in the following procedure.
     * @return String error message.
     * @throws Exception if something wrong happened in {@link Utils#checkUserIDPassword(String, String, String)}
     */
    public String getMessage(String id, String password) throws Exception {
        String type;
        if (memberNoSignInButton.isSelected()) {
            type = "memberNo";
        }
        else {
            type = "username";
        }

        switch (Utils.checkUserIDPassword(type, id, password)) {
            case 1:  // Both correct
                isCorrectInfo = true;
                return "Login Successfully!";
            case -1:  // Incorrect password
                return "Incorrect Password!";
            case 0:  // username not found
                return "You haven't signed up yet!";
            case -2:  // blank username or password
                return "You must fill in all the blank.";
            default:
                return null;
        }

    }


    /**
     * Set {@link SignInControl#loginMessageLabel} of a specific message.
     * @param message String to be set.
     */
    public void setLoginMessageLabel(String message) {
        loginMessageLabel.setText(message);
    }

    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.CustomerSignIn} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            String username = usernameTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // v3修改: 简化提醒方式
            try {
                setLoginMessageLabel(getMessage(username, password));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            // isCorrectInfo will be toggled true if correct in getErrorMessage.
            if (isCorrectInfo) {
                if (usePhoneOrEmail) {
                    new Customer(username, "username");  // 让Customer活起来
                }
                else {
                    new Customer(username, "memberNo");
                }

                contentPane.removeAll();
                contentPane.repaint();
                contentPane.add(new MembershipFunctions().getMembershipChoicesPane());
                contentPane.revalidate();
            }

        }

        else if (e.getSource() == quitButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new HomePage("REST0001").getHomePagePanel());  // 默认
            contentPane.revalidate();
        }
    }


    /**
     * Set the flag for indicating whether use <code>username</code> or <code>memberNo</code>.
     * @param setter boolean
     */
    public static void setUsePhoneOrEmail(boolean setter) {
        usePhoneOrEmail = setter;
    }

}
