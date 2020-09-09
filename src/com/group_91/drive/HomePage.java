package com.group_91.drive;

import com.group_91.customer.entities.Restaurant;

import javax.swing.*;
import java.awt.*;

/**
 * HomePage Interface for Ramen Kiosk Group Project.
 *
 * @author Zhiyu Liu, Bojun Wang, Yingke Ding
 */
public class HomePage extends JFrame {
    private final JPanel homePagePanel;


    /**
     * Constructor.
     * @param restaurantID String to be used for reading menu, suits, add_on data in JSON files.
     */
    public HomePage(String restaurantID) {
        ImageIcon img = new ImageIcon("./img/mback.png");		//Ҫ���õı���ͼƬ
        JLabel imgLabel = new JLabel(img);		//������ͼ���ڱ�ǩ�
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));		//��������ǩ��ӵ�jfram��LayeredPane����
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());		// ���ñ�����ǩ��λ��
        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);

        homePagePanel = new JPanel();
        HomePageControl.initEntity(restaurantID);  // init customer, order, suits...

        this.setTitle(Restaurant.getRestaurantName() + " Kiosk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        homePagePanel.setBounds(50, 75, 500, 575);
        homePagePanel.setOpaque(false);
        homePagePanel.setBorder(BorderFactory.createTitledBorder(" "));
        homePagePanel.setLayout(null);

        Font font = new Font("Times New Roman", Font.ITALIC, 18);
        JLabel j1 = new JLabel("Welcome to " + Restaurant.getRestaurantName());  // Welcome to Totoro Ramen!
        j1.setBounds(150, 170, 400, 150);
        j1.setFont(font);
        j1.setForeground(Color.black);
        homePagePanel.add(j1);

        JLabel j2 = new JLabel("Hope you can have a delicious meal in our restaurant!");
        j2.setBounds(49, 230, 500, 100);
        j2.setFont(font);
        j2.setForeground(Color.black);
        homePagePanel.add(j2);


        JLabel j3 = new JLabel(new ImageIcon("./img/first.png"));
        j3.setBounds(150, 50, 200, 200);
        j3.setFont(font);
        homePagePanel.add(j3);

        JButton orderingButton = new JButton("Starting ordering");
        orderingButton.setBounds(175, 345, 150, 50);
        homePagePanel.add(orderingButton);


        JButton adminButton = new JButton("ADMIN LOGIN");
        adminButton.setBounds(350, 50, 120, 30);
        adminButton.setForeground(Color.RED);
        homePagePanel.add(adminButton);

        JLabel j4 = new JLabel(new ImageIcon("./img/noodles.png"));
        j4.setBounds(150, 380, 200, 200);
        j4.setFont(font);
        homePagePanel.add(j4);


        // Register control here.
        HomePageControl control = new HomePageControl(homePagePanel, orderingButton, adminButton);
        orderingButton.addActionListener(control);
        adminButton.addActionListener(control);


        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 -300, (height / 2 - 362));
        this.setLayout(null);
        this.setSize(600, 725);
        this.getContentPane().add(homePagePanel);
        this.setResizable(false);  // Fixed window
    }


    /**
     * Get this panel.
     * @return JPanel.
     */
    public JPanel getHomePagePanel() {
        homePagePanel.setBounds(0, 0, 500, 575);
        homePagePanel.setOpaque(false);
        homePagePanel.setBorder(BorderFactory.createTitledBorder(" "));
        homePagePanel.setLayout(null);
        return homePagePanel;
    }


}