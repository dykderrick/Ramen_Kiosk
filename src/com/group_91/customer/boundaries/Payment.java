package com.group_91.customer.boundaries;

import com.group_91.customer.controls.PaymentControl;
import com.group_91.customer.entities.Customer;
import com.group_91.customer.entities.Order;
import com.group_91.customer.entities.OrderSuits;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Interface for payment panel.
 *
 * @author Yingke Ding
 */
public class Payment extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructor.
     */
    public Payment() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setSize(500, 575);
        //contentPane.setLayout(new GridLayout(2, 1));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel introLabel = new JLabel(Customer.getName() + ", this is your order", JLabel.CENTER);
        introLabel.setBounds(0, 0, 500, 50);
        introLabel.setFont(new Font("", Font.BOLD,16));
        contentPane.add(introLabel);

        JPanel suitsDetailPane = new JPanel();
        suitsDetailPane.setLayout(new BoxLayout(suitsDetailPane, BoxLayout.Y_AXIS));
        suitsDetailPane.setSize(300, 150 * OrderSuits.getSuitsRecord().size());
        suitsDetailPane.setOpaque(false);

        for (int i = 0; i < OrderSuits.getSuitsRecord().size(); ++i) {
            JPanel aSuitDetail = new JPanel();
            aSuitDetail.setLayout(new FlowLayout());

            JLabel imageLabel = new JLabel();
            JPanel imagePanel = new JPanel();
            ImageIcon suitImage = new ImageIcon("./img/ramen.jpeg");
            suitImage.setImage(suitImage.getImage().getScaledInstance(80, 100, Image.SCALE_DEFAULT));  // 缩放
            imageLabel.setIcon(suitImage);
            imagePanel.add(imageLabel);
            imagePanel.setOpaque(false);

            JLabel infoLabel = new JLabel(OrderSuits.getOrderSuitInfoString(i));

            JLabel priceLabel = new JLabel("Price: " + OrderSuits.getSuit(i).get("money").toString(), SwingConstants.LEFT);


            aSuitDetail.add(imagePanel);
            aSuitDetail.add(infoLabel);
            aSuitDetail.add(priceLabel);
            aSuitDetail.setBounds(70, 50 + 200 * i, 300, 150);
            aSuitDetail.setOpaque(false);

            suitsDetailPane.add(aSuitDetail);

        }

        JScrollPane suitsScrollPane = new JScrollPane(suitsDetailPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        suitsScrollPane.setBounds(50, 50, 400, 400);
        suitsScrollPane.setOpaque(false);


        JLabel totalPriceLabel = new JLabel("Totoal Price: " + OrderSuits.getTotalMoney());
        totalPriceLabel.setFont(new Font("", Font.BOLD, 15));
        totalPriceLabel.setBounds(180, 450, 150, 30);
        contentPane.add(totalPriceLabel);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 480, 500, 30);
        buttonsPanel.setOpaque(false);

        JButton cardButton = new JButton("Card");
        JButton cashButton = new JButton("Cash");

        buttonsPanel.add(cardButton);
        buttonsPanel.add(cashButton);

        JToggleButton useStampsButton = new JToggleButton("Use " + Customer.STAMPS_DISCOUNT_NUM + " Stamps for " + SuitInfo.getPrice() + " discount");
        // 小功能不需要放入control
        useStampsButton.addItemListener(e -> {
            PaymentControl.setUseStampsDiscount(e.getStateChange() == ItemEvent.SELECTED);
            if (e.getStateChange() == ItemEvent.SELECTED) {
                totalPriceLabel.setText("Total Price: " + (OrderSuits.getTotalMoney() - SuitInfo.getPrice()));
            }
            else {
                totalPriceLabel.setText("Totoal Price: " + OrderSuits.getTotalMoney());
            }
        });
        useStampsButton.setVisible(Customer.isStampsDiscount());  // visible if 10 stamps
        buttonsPanel.add(useStampsButton);

        // Use HTML to get new line
        JLabel endLabel = new JLabel("<html>PLEASE CHECK YOUR TICKET IN THE FOLDER!" +
                "<br/>YOUR STAMPS INFO HAS BEEN SENT TO YOUR EMAIL/PHONE.</html>");
        endLabel.setBounds(30, 490, 400, 60);
        endLabel.setForeground(Color.GREEN);
        endLabel.setVisible(false);
        contentPane.add(endLabel);


        PaymentControl control = new PaymentControl(contentPane, cardButton, cashButton, endLabel);
        cardButton.addActionListener(control);
        cashButton.addActionListener(control);

        contentPane.add(suitsScrollPane);
        contentPane.add(buttonsPanel);

    }


    /**
     * Get this panel.
     * @return {@link Payment#contentPane}
     */
    public JPanel getCustomerPayPane() {
        return contentPane;
    }

}
