package com.group_91.customer.boundaries;

import com.group_91.customer.controls.CartControl;
import com.group_91.customer.entities.OrderSuits;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Interface for Cart panel of the project.
 * Panels, buttons are initiated here.
 *
 * @author Yingke Ding
 */
public class CartPanel extends JFrame {
    private final JPanel contentPane;
    private JButton[] suitButtons;
    private final JButton backButton;


    /**
     * Constructs the frame.
     */
    public CartPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500,575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);


        int suitsCount = OrderSuits.getSuitsRecord().size();
        if (suitsCount == 0) {
            JLabel messageLabel = new JLabel("Oops! You have no suit ordered yet!");
            messageLabel.setFont(new Font("",Font.BOLD,15));
            messageLabel.setBounds(100, 150, 300, 40);
            contentPane.add(messageLabel);

            backButton = new JButton("Back");
            backButton.setBounds(180, 200, 100, 40);
            contentPane.add(backButton);
            backButton.addActionListener(e -> {
                contentPane.removeAll();
                contentPane.repaint();
                contentPane.add(new Menu().getMenuPanel());
                contentPane.revalidate();
            });

        }
        else {
            JLabel messageLabel = new JLabel("<html>Left click to modify,<br/>Right click to delete.</html>");
            messageLabel.setFont(new Font("",Font.BOLD,15));
            messageLabel.setBounds(150, 10, 200, 40);
            contentPane.add(messageLabel);


            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
            buttonsPanel.setSize(300, 150 * suitsCount);
            buttonsPanel.setOpaque(false);


            suitButtons = new JButton[suitsCount];
            for (int i = 0; i < suitButtons.length; ++i) {
                suitButtons[i] = new JButton(OrderSuits.getOrderSuitInfoString(i), new ImageIcon("./img/ramen_small.jpeg"));
                suitButtons[i].setVerticalTextPosition(SwingConstants.TOP);
                suitButtons[i].setHorizontalTextPosition(SwingConstants.RIGHT);
                suitButtons[i].setBounds(70, 50 + 200 * i, 300, 150);
                suitButtons[i].setOpaque(false);
                buttonsPanel.add(suitButtons[i]);


                CartControl control = new CartControl(contentPane, suitButtons);
                suitButtons[i].addActionListener(control);
                int finalI = i;
                suitButtons[i].addMouseListener(new MouseAdapter() {
                    boolean pressed;
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mousePressed(MouseEvent e) {
                        suitButtons[finalI].getModel().setArmed(true);
                        suitButtons[finalI].getModel().setPressed(true);
                        pressed = true;
                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        suitButtons[finalI].getModel().setArmed(false);
                        suitButtons[finalI].getModel().setPressed(false);

                        if (pressed) {
                            if (SwingUtilities.isRightMouseButton(e)) {
                                if (JOptionPane.showConfirmDialog(null, "Are you sure to delete?") == JOptionPane.YES_OPTION) {
                                    suitButtons[finalI].setVisible(false);
                                    OrderSuits.deleteSuit(finalI);
                                }
                            }
                        }
                        pressed = false;

                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        pressed = true;
                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseExited(MouseEvent e) {
                        pressed = false;
                    }
                });

            }
            JScrollPane scrollPane = new JScrollPane(buttonsPanel,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(50, 50, 400, 400);
            scrollPane.setOpaque(false);
            contentPane.add(scrollPane);


            backButton = new JButton("Back");
            backButton.setBounds(100, 500, 80, 40);
            backButton.setVisible(false);
            backButton.addActionListener(e -> {
                contentPane.removeAll();
                contentPane.repaint();
                contentPane.add(new Menu().getMenuPanel());
                contentPane.revalidate();
            });
            contentPane.add(backButton);


            JButton confirmButton = new JButton("Confirm");
            confirmButton.setBounds(300, 500, 80, 40);
            confirmButton.addActionListener(e -> {
                if (OrderSuits.getSuitsRecord().size() == 0) {
                    JOptionPane.showMessageDialog(null, "You have no suits ordered now!",
                            "Warning", JOptionPane.INFORMATION_MESSAGE);
                    backButton.setVisible(true);
                }
                else {
                    contentPane.removeAll();
                    contentPane.repaint();
                    contentPane.add(new DiningOptions().getDiningOptionsPanel());
                    contentPane.revalidate();
                }
            });
            contentPane.add(confirmButton);

        }



        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);
    }


    /**
     * Getter.
     * @return {@link CartPanel#contentPane}
     */
    public JPanel getCartPanel() {
        return contentPane;
    }

}
