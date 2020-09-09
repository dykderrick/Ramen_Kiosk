package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class defines customer's payment ticket.
 *
 * @author Yingke Ding
 */
public class OrderTicket {
    private final String ticketInfo;
    private final boolean useStampsDiscount;


    /**
     * Constructor.
     * @param useStampsDiscount boolean type indicating customer's selection of whether using stamps.
     * @param customerInfo {@link JSONObject} of customer's information.
     * @param restaurantInfo {@link JSONObject} of restaurant's information.
     * @param orderInfo {@link JSONObject} of order's information.
     */
    public OrderTicket(boolean useStampsDiscount, JSONObject customerInfo, JSONObject restaurantInfo, JSONObject orderInfo) {
        this.useStampsDiscount = useStampsDiscount;
        ticketInfo = getHeadlineInTicket(customerInfo, restaurantInfo, orderInfo)
                   + getSuitsInTicket(orderInfo.getJSONArray("suits"))
                   + getStampsDiscountInTicket()
                   + getEndingInTicket(orderInfo);
    }


    /**
     * Writes headline for the ticket. <p></p>
     * Contains customer's name, email/phone, stamps info, and restaurant's name.
     * @param customerInfo {@link JSONObject} of customer's information.
     * @param restaurantInfo {@link JSONObject} of restaurant's information.
     * @param orderInfo {@link JSONObject} of order's information.
     * @return String of ticket headline.
     */
    public String getHeadlineInTicket(JSONObject customerInfo, JSONObject restaurantInfo, JSONObject orderInfo) {
        String firstName, surname, username, usernameType;
        int stamps;
        if (!Customer.getStatus()) {  // 尚未注册用户
            firstName = "";
            surname = "";
            username = "";
            usernameType = "";
            stamps = 0;
        }
        else {
            firstName = (String) customerInfo.get("First_Name");
            surname = (String) customerInfo.get("Last_Name");
            //String memberNo = (String) customerInfo.get("memberNo");
            username = (String) ((JSONObject) customerInfo.get("UserLoginID")).get("ID");
            usernameType = (String) ((JSONObject) customerInfo.get("UserLoginID")).get("type");
            stamps = (int) customerInfo.get("Stamps");
        }


        String restaurantName = (String) restaurantInfo.get("Name");

        Date orderCreateTime = new Date((long) orderInfo.get("createTime"));
        int callNo = (int) orderInfo.get("callNo");
        String diningOption = (String) orderInfo.get("diningOption");
        //String paymentMethod = (String) orderInfo.get("paymentMethod");
        //double money = ((BigDecimal) orderInfo.get("money")).doubleValue();


        String ticketString = "";

        ticketString += "          " + restaurantName + "     \n-------------------------------\n";
        ticketString += "             " + callNo + "\n\n";
        ticketString += "         " + firstName + " " + surname + "\n";
        ticketString += "      " + usernameType + ": " + username + "\n";
        ticketString += "       " + stamps + " Stamps Remaining\n\n";
        ticketString += "       Delivery " + new SimpleDateFormat("yyyy-MM-dd").format(orderCreateTime) + "\n";
        ticketString += "       Time Ordered " + new SimpleDateFormat("HH:mm").format(orderCreateTime) + "\n\n";
        //ticketString += " Time Promised " + new SimpleDateFormat("HH:mm").format(orderCreateTime + )
        ticketString += "            " + diningOption.toUpperCase() + "\n-------------------------------\n";
        ticketString += "AMOUNT DETAIL               MONEY\n";


        return ticketString;
    }


    /**
     * Writes customer's selected suits.
     * @param suits {@link JSONArray}
     * @return String of suit info in the ticket.
     */
    public String getSuitsInTicket(JSONArray suits) {
        StringBuilder details = new StringBuilder();

        for (Object orderSuit : suits) {
            JSONObject suit = (JSONObject) orderSuit;

            details.append("1      [SOUP]   ").append(suit.get("soup")).append("   ").append(suit.get("money")).append("\n");
            details.append("       [NOODLE] ").append(suit.get("noodles")).append("\n");
            details.append("       [ONION]  ").append(suit.get("onion")).append("\n\n");
            details.append("       [NORI]   ").append(suit.get("Nori")).append("\n");
            details.append("       [CHASHU] ").append(suit.get("Chashu")).append("\n");
            details.append("       [EGG]    ").append(suit.get("Boiled Egg")).append("\n\n");

            details.append("       Spiciness   x").append(suit.get("Spiciness")).append("\n\n");
            details.append(getAddOnsInSuit(suit)).append("\n\n");

        }

        return details.toString();
    }


    /**
     * Writes customer's selected add on to tickets.
     * @param suit {@link JSONObject}
     * @return String of add on in suit.
     */
    public String getAddOnsInSuit(JSONObject suit) {
        StringBuilder details = new StringBuilder("[ADD-ONs]\n");
        HashMap<String, Integer> suitAddOns = (HashMap<String, Integer>) suit.get("add_ons");
        Set<String> keys = suitAddOns.keySet();
        Iterator<String> iterator = keys.iterator();
        boolean allZeros = true;

        while (iterator.hasNext()) {
            String key = iterator.next();
            if (!(suitAddOns.get(key) == 0)) {
                details.append(key).append(" x").append(suitAddOns.get(key)).append("\n");
                allZeros = false;
            }

        }

        if (allZeros) {
            return "";
        }
        else {
            return details.toString();
        }

    }


    /**
     * Writes stamps discount info to ticket.
     * @return String of stamps info.
     */
    public String getStampsDiscountInTicket() {
        if (useStampsDiscount) {
            return "[STAMPS DISCOUNT]      -" + SuitInfo.getPrice();
        }
        else return "";
    }


    /**
     * Writes the ending part to ticket.
     * @param orderInfo {@link JSONObject}
     * @return String of ending.
     */
    public String getEndingInTicket(JSONObject orderInfo) {
        String ending = "";
        double money = (double) orderInfo.get("totalMoney");
        String paymentMethod = (String) orderInfo.get("paymentMethod");

        ending += "\n\n[TOTAL]                   " + money + "\n-------------------------------\n";
        ending += paymentMethod + "                      " + money + "\n";

        ending += "\n\n******************************\n";
        ending += "          THANK YOU!      \n";
        ending += "******************************\n";

        return ending;
    }


    /**
     * Getter.
     * @return {@link OrderTicket#ticketInfo}
     */
    public String getTicketInfo() {
        return ticketInfo;
    }

}
