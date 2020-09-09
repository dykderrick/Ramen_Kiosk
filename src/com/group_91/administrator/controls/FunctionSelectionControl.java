package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.*;
import com.group_91.administrator.entities.IntOptionStats;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class for {@link FunctionSelection}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class FunctionSelectionControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton viewSuitStatsButton, viewGeneralOptionsStatsButton, viewBooleanOptionsStatsButton,
            viewAddOnsStatsButton, viewSpicinessButton, emailButton, backButton;


    /**
     * Constructor.
     * @param contentPane the main panel of {@link FunctionSelection}
     * @param viewSuitStatsButton Select view-suit-stats button.
     * @param viewGeneralOptionsStatsButton Select view-general-options-stats button.
     * @param viewBooleanOptionsStatsButton Select view-boolean-options-stats button
     * @param viewAddOnsStatsButton Select view-add-ons-stats button.
     * @param viewSpicinessButton Select view-spiciness button.
     * @param emailButton Select subscribe-email button.
     * @param backButton Select back-to-previous-panel button.
     */
    public FunctionSelectionControl(JPanel contentPane, JButton viewSuitStatsButton,
                                    JButton viewGeneralOptionsStatsButton, JButton viewBooleanOptionsStatsButton,
                                    JButton viewAddOnsStatsButton, JButton viewSpicinessButton,
                                    JButton emailButton, JButton backButton) {
        this.contentPane = contentPane;
        this.viewSuitStatsButton = viewSuitStatsButton;
        this.viewGeneralOptionsStatsButton = viewGeneralOptionsStatsButton;
        this.viewBooleanOptionsStatsButton = viewBooleanOptionsStatsButton;
        this.viewAddOnsStatsButton = viewAddOnsStatsButton;
        this.viewSpicinessButton = viewSpicinessButton;
        this.emailButton = emailButton;
        this.backButton = backButton;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the clicking events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewSuitStatsButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new SuitStatsPanel().getSuitStatsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == viewGeneralOptionsStatsButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new GeneralOptionsStatsPanel().getGeneralOptionsStatsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == viewBooleanOptionsStatsButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new BooleanOptionsStatsPanel().getBooleanOptionsStatsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == viewAddOnsStatsButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new AddOnsStatsPanel().getAddOnsStatsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == viewSpicinessButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new IntOptionStatsPanel().getIntOptionStatsPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == emailButton) {
            JOptionPane.showMessageDialog(null,
                    "You have successfully subscribed weekly report mail.",
                    "Email Subscription",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new AdminWelcomePage().getAdminWelcomePagePanel());
            contentPane.revalidate();
        }
    }
}
