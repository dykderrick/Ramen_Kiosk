package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import java.util.Date;
import java.util.Random;

/**
 * This class defines an order transaction of the customer.
 *
 * @author Yingke Ding
 */
public class Order {
    private static String diningOption;
    private static String paymentMethod;
    private static JSONObject order;


    /**
     * Constructor. Some initiating here.
     */
    public Order() {
        diningOption = "";
        paymentMethod = "";
        order = new JSONObject();
    }


    /**
     * {@link Order#diningOption} setter.
     * @param diningOption String
     */
    public static void setDiningOption(String diningOption) {
        Order.diningOption = diningOption;
    }


    /**
     * {@link Order#paymentMethod} setter.
     * @param paymentMethod String
     */
    public static void setPaymentMethod(String paymentMethod) {
        Order.paymentMethod = paymentMethod;
    }


    /**
     * Save info into {@link Order#order}.
     */
    public static void setOrder() {
        String orderID = "ORDER" + Utils.zeroPad(Jsons.getJSONFileSize("orders") + 1, Utils.ID_NUMBER_LENGTH);
        String customerID;
        if (Customer.getCustomerID() != null) {
            customerID = Customer.getCustomerID();
        }
        else {
            customerID = "NO_SIGNED_IN_CUSTOMER";
        }
        long currentTime = new Date().getTime();
        int callNo = 30000 + new Random().nextInt(10000);;

        order.put("orderID", orderID);
        order.put("customerID", customerID);
        order.put("createTime", currentTime);
        order.put("callNo", callNo);
        order.put("diningOption", diningOption);
        order.put("paymentMethod", paymentMethod);
        order.put("totalMoney", OrderSuits.getTotalMoney());
        order.put("suits", OrderSuits.getSuitsRecord());

    }


    /**
     * modify {@link Order#order} money info if stamps are used.
     * @param money double
     */
    public static void discountOrderTotalMoney(double money) {
        order.put("totalMoney", OrderSuits.getTotalMoney() - money);
    }


    /**
     * Getter.
     * @return {@link Order#order}
     */
    public static JSONObject getOrder() {
        return order;
    }
}
