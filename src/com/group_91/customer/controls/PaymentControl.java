package com.group_91.customer.controls;

import com.group_91.drive.HomePage;
import com.group_91.customer.entities.*;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link com.group_91.customer.boundaries.Payment}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class PaymentControl implements ActionListener {
    private final JPanel contentPane;
    private final JButton cardButton, cashButton;
    private final JLabel endLabel;
    private static boolean useStampsDiscount = false;


    /**
     * Constructor.
     * @param contentPane main panel of {@link com.group_91.customer.boundaries.Payment}.
     * @param cardButton Select using card button.
     * @param cashButton Select using cash button.
     * @param endLabel A label showing some info when finished.
     */
    public PaymentControl(JPanel contentPane, JButton cardButton, JButton cashButton, JLabel endLabel) {
        this.contentPane = contentPane;
        this.cardButton = cardButton;
        this.cashButton = cashButton;
        this.endLabel = endLabel;
    }


    /**
     * Invoked when buttons in {@link com.group_91.customer.boundaries.Payment} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardButton) {
            Order.setPaymentMethod("Card");
        }

        else if (e.getSource() == cashButton) {
            Order.setPaymentMethod("Cash");
        }

        Order.setOrder();  // 把OrderSuits中的内容保存到Order中

        // 10 stamps discount
        if (useStampsDiscount) {
            Order.discountOrderTotalMoney(SuitInfo.getPrice());
            try {
                Customer.minusStamps(Customer.STAMPS_DISCOUNT_NUM);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        try {
            Jsons.saveJson(Order.getOrder(), "orders");
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        // Update stamps. One suit is worth one stamp.
        int stampsAccumulated;
        if (useStampsDiscount) {
            stampsAccumulated = OrderSuits.getSuitsRecord().size() - 1;
        }
        else {
            stampsAccumulated = OrderSuits.getSuitsRecord().size();
        }

        if (Customer.getStatus()) {
            Customer.addStamps(stampsAccumulated);
        }


        String ticketInfo = new OrderTicket(useStampsDiscount, Customer.getCustomerInfo(), Restaurant.getRestaurantInfo(), Order.getOrder()).getTicketInfo();
        String filePath = "./tickets/order_tickets/" + Order.getOrder().get("orderID") + ".txt";
        Utils.saveStringToTextFile(ticketInfo, filePath);
        try {
            Utils.openTextFile(filePath);
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        // end ordering. Prepare for the next customer.
        endLabel.setVisible(true);
        Timer t = new Timer(3000, e1 -> {
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(new HomePage("REST0001").getHomePagePanel());
            contentPane.revalidate();
        });
        t.setRepeats(false);
        t.start();




    }


    /**
     * Set a flag in the class indicating whether the ToggleButton in {@link com.group_91.customer.boundaries.Payment} is selected or not.
     *
     * @param useStampsDiscount boolean setter
     */
    public static void setUseStampsDiscount(boolean useStampsDiscount) {
        PaymentControl.useStampsDiscount = useStampsDiscount;
    }

}
