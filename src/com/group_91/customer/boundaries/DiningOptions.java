package com.group_91.customer.boundaries;

import com.group_91.customer.controls.DiningOptionsControl;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for dining options selection function.
 *
 * @author Zhiyu Liu, Bojun Wang
 */
public class DiningOptions extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructor.
     */
    public DiningOptions() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();

        contentPane.setSize(500,575);
        //contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel label= new JLabel("Please Select Your Dining Option");
        label.setBounds(110, 120, 400, 150);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        contentPane.add(label);


        JButton eatInButton = new JButton("Eat-In");
        eatInButton.setBounds(110, 280, 100, 30);

        JLabel p1 = new JLabel(new ImageIcon("./img/eating.png"));
        p1.setBounds(110, 310, 100, 100);
        contentPane.add(p1);

        JButton takeAwayButton = new JButton("Take-Away");
        takeAwayButton.setBounds(300, 280, 100, 30);

        JLabel p2 = new JLabel(new ImageIcon("./img/waim.png"));
        p2.setBounds(300, 310, 100, 100);
        contentPane.add(p2);

        JButton backButton = new JButton("Back");
        backButton.setBounds(200, 430, 100, 30);


        contentPane.add(eatInButton);
        contentPane.add(takeAwayButton);
        contentPane.add(backButton);

        DiningOptionsControl control = new DiningOptionsControl(contentPane, eatInButton, takeAwayButton, backButton);
        eatInButton.addActionListener(control);
        takeAwayButton.addActionListener(control);
        backButton.addActionListener(control);


        contentPane.setBackground(Color.white);
        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);
    }


    /**
     * Get this panel.
     * @return JPanel.
     */
    public JPanel getDiningOptionsPanel() {
        return contentPane;
    }

}
