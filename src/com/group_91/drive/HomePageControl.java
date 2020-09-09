package com.group_91.drive;

import com.group_91.administrator.boundaries.AdminSignIn;
import com.group_91.customer.boundaries.Menu;
import com.group_91.customer.entities.Order;
import com.group_91.customer.entities.OrderSuits;
import com.group_91.customer.entities.Restaurant;
import com.group_91.customer.entities.SuitInfo;
import com.group_91.drive.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Control class of {@link HomePage}. <p></p>
 * Button listeners, JSON File I/O here.
 *
 * @author Yingke Ding
 */
public class HomePageControl implements ActionListener {
    private final JPanel homePagePanel;
    private final JButton orderingButton, adminButton;


    /**
     * Constructs the control class.
     * @param homePagePanel main panel of {@link HomePage}.
     * @param orderingButton button for entering customer program.
     * @param adminButton buttons for entering admin program.
     */
    public HomePageControl(JPanel homePagePanel, JButton orderingButton, JButton adminButton) {
        this.homePagePanel = homePagePanel;
        this.orderingButton = orderingButton;
        this.adminButton = adminButton;
    }


    /**
     * Call and initiate {@link Restaurant}, {@link SuitInfo}, {@link OrderSuits} and {@link Order} classes,
     * make static variables alive when the program is running.
     * @param restaurantID defines a specific restaurant id. For <b>Totoro Ramen</b>, it is "REST0001".
     */
    public static void initEntity(String restaurantID) {
        new Restaurant(restaurantID);  // 让restaurant活起来
        new SuitInfo("SUIT00001");  // v3默认只能有一个菜
        new OrderSuits();  // 准备好订单中的suits
        new Order();  // 准备好一个订单
        Order.setDiningOption("");  // init String
    }


    /**
     * Invoked when the button in the {@link HomePage} is clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderingButton) {
            homePagePanel.removeAll();
            homePagePanel.repaint();
            homePagePanel.add(new Menu().getMenuPanel());
            homePagePanel.revalidate();
        }
        else if (e.getSource() == adminButton) {
            homePagePanel.removeAll();
            homePagePanel.repaint();
            homePagePanel.add(new AdminSignIn().getAdminSignInPanel());
            homePagePanel.revalidate();
        }
    }
}
