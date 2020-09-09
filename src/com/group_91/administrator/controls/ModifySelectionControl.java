package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.AdminWelcomePage;
import com.group_91.administrator.boundaries.ModifyAddOns;
import com.group_91.administrator.boundaries.ModifyBooleanOptions;
import com.group_91.administrator.boundaries.ModifyGeneralOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.administrator.boundaries.ModifySelection}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class ModifySelectionControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton modifyGeneralOptionButton, modifyBooleanOptionButton, modifyAddOnButton, backButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.administrator.boundaries.ModifySelection}
     * @param modifyGeneralOptionButton Select modify-general-option button
     * @param modifyBooleanOptionButton Select modify-boolean-option button
     * @param modifyAddOnButton Select modify-add-on button
     * @param backButton Select back button
     */
    public ModifySelectionControl(JPanel contentPane, JButton modifyGeneralOptionButton, JButton modifyBooleanOptionButton, JButton modifyAddOnButton, JButton backButton) {
        this.contentPane = contentPane;
        this.modifyGeneralOptionButton = modifyGeneralOptionButton;
        this.modifyBooleanOptionButton = modifyBooleanOptionButton;
        this.modifyAddOnButton = modifyAddOnButton;
        this.backButton = backButton;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyGeneralOptionButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifyGeneralOptions().getModifyGeneralOptionsPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == modifyBooleanOptionButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifyBooleanOptions().getModifyBooleanOptionsPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == modifyAddOnButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifyAddOns().getModifyAddOnsPane());
            contentPane.revalidate();
        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new AdminWelcomePage().getAdminWelcomePagePanel());
            contentPane.revalidate();
        }
    }
}
