package com.group_91.customer.controls;

import com.group_91.customer.boundaries.Menu;
import com.group_91.customer.entities.OrderSuits;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.CartPanel}.
 *
 * @author Yingke Ding
 */
public class CartControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton[] suitButtons;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.CartPanel}
     * @param suitButtons {@link JButton} array.
     */
    public CartControl(JPanel contentPane, JButton[] suitButtons) {
        this.contentPane = contentPane;
        this.suitButtons = suitButtons;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        int index = -1;
        for (int i = 0; i < suitButtons.length; ++i) {
            if (suitButtons[i] == buttonClicked) {
                index = i;
            }
        }

        if (JOptionPane.showConfirmDialog(null, "This suit in your cart will be temporarily deleted.\n" +
                "You can add it back to cart after modifying.\n\n" +
                "Click Y to redirect to Menu, N to cancel.") == JOptionPane.YES_OPTION) {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new Menu().getOriginalPane(OrderSuits.getSuit(index)));
            contentPane.revalidate();

            OrderSuits.deleteSuit(index);  // delete this suit in record
        }

    }
}
