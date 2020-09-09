package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.AdminWelcomePageControl;

import javax.swing.*;
import java.awt.*;


/**
 * Interface class for Admin-Welcome-Page panel.
 *
 * @author Yongchang Li, Zhiyu Liu, Bojun Wang, Yingke Ding
 */
public class AdminWelcomePage extends JFrame {
    private final JPanel contentPane;


    /**
     * Create the frame.
     */
    public AdminWelcomePage() {
        // initiating
        AdminWelcomePageControl.initiateEntities();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel txtpnWelcome = new JLabel();
        txtpnWelcome.setForeground(Color.BLACK);
        txtpnWelcome.setFont(new Font("Times New Roman",Font.ITALIC,18));
        txtpnWelcome.setText("  Welcome,manager");
        txtpnWelcome.setBounds(180, 78, 197, 24);
        contentPane.add(txtpnWelcome);

        JLabel txtpnPleaseChooseWhat = new JLabel();
        txtpnPleaseChooseWhat.setFont(new Font("Times New Roman",Font.ITALIC,18));
        txtpnPleaseChooseWhat.setText("Please choose what you want to do");
        txtpnPleaseChooseWhat.setBounds(127, 132, 303, 53);
        contentPane.add(txtpnPleaseChooseWhat);


        JButton modifyButton = new JButton("Modify");
        modifyButton.setBounds(188, 333, 120, 33);
        contentPane.add(modifyButton);

        JButton queryButton = new JButton("Query");
        queryButton.setBounds(188, 383, 120, 33);
        contentPane.add(queryButton);


        AdminWelcomePageControl control = new AdminWelcomePageControl(contentPane, modifyButton, queryButton);
        modifyButton.addActionListener(control);
        queryButton.addActionListener(control);


        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("./img/guan.png"));
        lblNewLabel.setBounds(190, 185, 120, 120);
        contentPane.add(lblNewLabel);

        contentPane.setBackground(Color.white);
        this.setSize(500,575);
        this.getContentPane().add(contentPane);
        this.setResizable(false);
    }


    /**
     * Getter.
     * @return {@link AdminWelcomePage#contentPane}
     */
    public JPanel getAdminWelcomePagePanel() {
        return contentPane;
    }

}
