package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.AdminWelcomePage;
import com.group_91.administrator.entities.Manager;
import com.group_91.drive.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class for {@link com.group_91.administrator.boundaries.AdminSignIn}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class AdminSignInControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton signInButton, quitButton;
    private final JTextField managerIDTextField;
    private final JPasswordField passwordField;
    private final JLabel loginMessageLabel;
    private boolean isCorrectInfo = false;


    /**
     * Constructs the frame.
     * @param contentPane main panel for {@link com.group_91.administrator.boundaries.AdminSignIn}
     * @param managerIDTextField id field for manager to input
     * @param passwordField {@link JPasswordField}
     * @param signInButton Select sign-in button
     * @param quitButton Select quit button
     * @param loginMessageLabel {@link JLabel} of some info
     */
    public AdminSignInControl(JPanel contentPane, JTextField managerIDTextField, JPasswordField passwordField, JButton signInButton, JButton quitButton, JLabel loginMessageLabel) {
        this.contentPane = contentPane;
        this.managerIDTextField = managerIDTextField;
        this.passwordField = passwordField;
        this.signInButton = signInButton;
        this.quitButton = quitButton;
        this.loginMessageLabel = loginMessageLabel;
    }


    /**
     * Get message to be shown in {@link AdminSignInControl#loginMessageLabel}
     *
     * @return String of message info
     */
    private String getMessage() {
        if (isCorrectInfo) {
            return  "Sign In successfully!";
        }
        else return  "Please check your id or password!";
    }


    /**
     * {@link AdminSignInControl#loginMessageLabel} setter.
     * @param message some message to be shown in this {@link JLabel}.
     */
    private void setLoginMessageLabel(String message) {
        loginMessageLabel.setText(message);

        if (isCorrectInfo) {
            loginMessageLabel.setForeground(Color.GREEN);
        } else {
            loginMessageLabel.setForeground(Color.RED);
        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            String username = managerIDTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
            try {
                isCorrectInfo = Manager.checkIDPassword(username, password);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            setLoginMessageLabel(getMessage());

            if (isCorrectInfo) {
                // delay 1000 ms
                Timer t = new Timer(1000, e1 -> {
                    contentPane.removeAll();
                    contentPane.repaint();
                    contentPane.add(new AdminWelcomePage().getAdminWelcomePagePanel());
                    contentPane.revalidate();
                });
                t.setRepeats(false);
                t.start();

            }


        }
        else if (e.getSource() == quitButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new HomePage("REST0001").getHomePagePanel());
            contentPane.revalidate();

        }

    }
}
