package com.group_91.customer.controls;

import com.group_91.customer.boundaries.IdentityConfirmation;
import com.group_91.customer.boundaries.Menu;
import com.group_91.customer.entities.Order;
import com.group_91.customer.entities.OrderSuits;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.DiningOptions}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class DiningOptionsControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton eatInButton;
    private final JButton takeAwayButton;
    private final JButton backButton;

    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.DiningOptions}
     * @param eatInButton Select eat-in button.
     * @param takeAwayButton Select take-away button.
     * @param backButton Select back-to-previous-panel button.
     */
    public DiningOptionsControl(JPanel contentPane, JButton eatInButton, JButton takeAwayButton, JButton backButton) {
        this.contentPane = contentPane;
        this.eatInButton = eatInButton;
        this.takeAwayButton = takeAwayButton;
        this.backButton = backButton;
    }

    /**
     * Invoked when buttons in the {@link DiningOptionsControl#contentPane} are clicked.
     *
     * @param e the clicking event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Rollback
        if (e.getSource() == backButton) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new Menu().getOriginalPane(OrderSuits.getLastSuitRecord()));
            contentPane.revalidate();

            OrderSuits.deleteLastSuitRecord();  // 删除上一次保存数据

        }
        else {
            if (e.getSource() == eatInButton) {
                Order.setDiningOption("eat-in");
            }
            else if (e.getSource() == takeAwayButton) {
                Order.setDiningOption("take-away");
            }

            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new IdentityConfirmation().getIdConfirmationPane());
            contentPane.revalidate();
        }
    }
}
