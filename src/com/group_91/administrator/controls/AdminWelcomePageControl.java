package com.group_91.administrator.controls;

import com.group_91.administrator.boundaries.FunctionSelection;
import com.group_91.administrator.boundaries.ModifySelection;
import com.group_91.administrator.entities.*;
import com.group_91.customer.entities.AddOnsInfo;
import com.group_91.customer.entities.Restaurant;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class for {@link com.group_91.administrator.boundaries.AdminWelcomePage}.
 * Button listeners here.
 *
 * @author Yingke Ding
 */
public class AdminWelcomePageControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton modifyButton, queryButton;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.administrator.boundaries.AdminWelcomePage}
     * @param modifyButton Select modify button.
     * @param queryButton Select query button.
     */
    public AdminWelcomePageControl(JPanel contentPane, JButton modifyButton, JButton queryButton) {
        this.contentPane = contentPane;
        this.modifyButton = modifyButton;
        this.queryButton = queryButton;
    }


    /**
     * Entity initiating method. <p></p>
     * Invoke {@link Restaurant}, {@link SuitInfo}, {@link AddOnsInfo}, {@link WeeklyDates},
     * {@link SuitStats}, {@link GeneralOptionsStats}, {@link BooleanOptionsStats},
     * {@link AddOnsStats} and {@link IntOptionStats} here.
     */
    public static void initiateEntities() {
        new Restaurant("REST0001");
        new SuitInfo("SUIT00001");
        new AddOnsInfo("REST0001");
        new WeeklyDates();
        new SuitStats();
        new GeneralOptionsStats();
        new BooleanOptionsStats();
        new AddOnsStats();
        new IntOptionStats();
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new ModifySelection().getModifyPanel());
            contentPane.revalidate();
        }
        else if (e.getSource() == queryButton) {
            /*
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new FunctionSelection().getQueryPane());
            contentPane.revalidate();

             */

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            topFrame.setVisible(false);
            //contentPane.setVisible(false);

            FunctionSelection panel = new FunctionSelection();
            panel.setTitle("Totoro Ramen Kiosk");
            panel.setSize(600, 725);
            panel.setVisible(true);

            //new FunctionSelection().setVisible(true);



        }
    }
}
