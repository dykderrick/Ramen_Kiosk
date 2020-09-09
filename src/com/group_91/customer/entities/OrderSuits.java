package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class defines suits in an order by customer.
 *
 * @author Yingke Ding
 */
public class OrderSuits {
    private static HashMap<String, String> generalOptionsSelected;  // "soup" : "Shoyu", ...
    private static HashMap<String, Boolean> booleanOptionsSelected;  // "Nori" : true, ...
    private static HashMap<String, Integer> intOptionsSelected;  // "Spiciness: : 3
    private static HashMap<String, Integer> addOnsSelected;  // "Bamboo Shoots" : 0, ...
    private static JSONArray suitsRecord;


    /**
     * Constructor. Initiating here.
     */
    public OrderSuits() {
        generalOptionsSelected = new HashMap<>();
        booleanOptionsSelected = new HashMap<>();
        intOptionsSelected = new HashMap<>();
        addOnsSelected = new HashMap<>();
        suitsRecord = new JSONArray();
    }


    /**
     * {@link OrderSuits#suitsRecord} setter. <p></p>
     * save {@link OrderSuits#generalOptionsSelected}, {@link OrderSuits#booleanOptionsSelected}
     * and {@link OrderSuits#intOptionsSelected} into {@link OrderSuits#suitsRecord}.
     */
    public static void setSuitsRecord() {
        JSONObject aSuit = new JSONObject();

        // Merge
        aSuit.putAll(generalOptionsSelected);
        aSuit.putAll(booleanOptionsSelected);
        aSuit.putAll(intOptionsSelected);
        aSuit.put("add_ons", addOnsSelected);

        aSuit.put("money", getCurrentSuitMoney());
        aSuit.put("suitID", SuitInfo.getSuitID());
        suitsRecord.add(aSuit);

        // Init.
        generalOptionsSelected = new HashMap<>();
        booleanOptionsSelected = new HashMap<>();
        intOptionsSelected = new HashMap<>();
        addOnsSelected = new HashMap<>();
    }


    /**
     * Delete the last suit record in {@link OrderSuits#suitsRecord}. <p></p>
     * Invoked when the panel refers to the previous.
     */
    public static void deleteLastSuitRecord() {
        suitsRecord.remove(suitsRecord.size() - 1);  // delete the last one
    }


    /**
     * Delete the index suit in {@link OrderSuits#suitsRecord}.
     * @param index int
     */
    public static void deleteSuit(int index) {
        suitsRecord.remove(index);
    }


    /**
     * Add an item into {@link OrderSuits#generalOptionsSelected}.
     * @param name general option name
     * @param selection user's choice
     */
    public static void putAGeneralOptionSelected(String name, String selection) {
        OrderSuits.generalOptionsSelected.put(name, selection);
    }


    /**
     * Add an item into {@link OrderSuits#booleanOptionsSelected}.
     * @param name boolean option name
     * @param selection user's choice
     */
    public static void putABooleanOptionSelected(String name, boolean selection) {
        OrderSuits.booleanOptionsSelected.put(name, selection);
    }


    /**
     * Add an item into {@link OrderSuits#intOptionsSelected}.
     * @param name int option name
     * @param value user's choice
     */
    public static void putAnIntOptionSelected(String name, int value) {
        OrderSuits.intOptionsSelected.put(name, value);
    }


    /**
     * Initiation procedure for {@link OrderSuits#addOnsSelected}.
     * @param names add on name.
     */
    public static void initAddOnsSelected(ArrayList<String> names) {
        if (addOnsSelected.isEmpty()) {
            for (String name : names) {
                addOnsSelected.put(name, 0);
            }
        }
    }


    /**
     * Getter.
     * @return {@link OrderSuits#generalOptionsSelected}
     */
    public static HashMap<String, String> getGeneralOptionsSelected() {
        return generalOptionsSelected;
    }


    /**
     * Getter.
     * @return {@link OrderSuits#booleanOptionsSelected}
     */
    public static HashMap<String, Boolean> getBooleanOptionsSelected() {
        return booleanOptionsSelected;
    }


    /**
     * Getter.
     * @return {@link OrderSuits#intOptionsSelected}
     */
    public static HashMap<String, Integer> getIntOptionsSelected() {
        return intOptionsSelected;
    }


    /**
     * Getter.
     * @return {@link OrderSuits#addOnsSelected}
     */
    public static HashMap<String, Integer> getAddOnsSelected() {
        return addOnsSelected;
    }


    /**
     * Getter.
     * @return {@link OrderSuits#suitsRecord}
     */
    public static JSONArray getSuitsRecord() {
        return suitsRecord;
    }


    /**
     * Get a specific ordered suit information. Will use html string for {@link javax.swing.JLabel} to read.
     * @param index the location of suit in {@link OrderSuits#suitsRecord}.
     * @return String of suit information.
     */
    public static String getOrderSuitInfoString(int index) {
        JSONObject suit = getSuit(index);
        StringBuilder info = new StringBuilder("<html>");

        info.append("Ramen<br/>");
        for (String key : SuitInfo.getGeneralOptionsNames()) {
            info.append(key.toUpperCase()).append("    ");
        }
        info.append("<br/>");
        for (String key : SuitInfo.getGeneralOptionsNames()) {
            info.append(suit.get(key)).append("    ");
        }
        info.append("<br/><br/>");
        for (String key : SuitInfo.getBooleanOptionsNames()) {
            if (((boolean) suit.get(key))) {
                info.append(key).append(" ");
            }
        }
        info.append("<br/>Spiciness: ").append(suit.get("Spiciness")).append("<br/>");

        String addOns = "";
        HashMap<String, Integer> addOnsSelected = (HashMap<String, Integer>) suit.get("add_ons");

        if (addOnsSelected.get("Bamboo Shoots") != 0) {
            addOns += addOnsSelected.get("Bamboo Shoots") + " Bamboo Shoots   ";
        }

        if (addOnsSelected.get("Extra Chashu") != 0) {
            addOns += addOnsSelected.get("Extra Chashu") + " Extra Chashu   ";
        }

        if (addOnsSelected.get("Extra Nori") != 0) {
            addOns += addOnsSelected.get("Extra Nori") + " Extra Nori   ";
        }

        if (addOnsSelected.get("Extra Boiled Egg") != 0) {
            addOns += addOnsSelected.get("Extra Boiled Egg") + " Extra Boiled Eggs.";
        }
        info.append("--------------<br/>").append(addOns);
        info.append("</html>");


        return info.toString();
    }



    /**
     * Getter.
     * @return the last record of {@link OrderSuits#suitsRecord}
     */
    public static JSONObject getLastSuitRecord() {
        return (JSONObject) suitsRecord.get(suitsRecord.size() - 1);
    }


    /**
     * Getter.
     * @param index location of suits in record.
     * @return the index suit in {@link OrderSuits#suitsRecord}.
     */
    public static JSONObject getSuit(int index) {
        return (JSONObject) suitsRecord.getJSONObject(index);
    }


    /**
     * Getter.
     * @return calculated suit money of a specific suit.
     */
    public static double getCurrentSuitMoney() {
        double money = 0.0d;

        // Fixed Price
        money += SuitInfo.getPrice();

        // Add Ons Price
        for (Map.Entry<String, Integer> entry : addOnsSelected.entrySet()) {
            String addOnName = entry.getKey();
            int addOnNum = entry.getValue();

            money += ((BigDecimal) AddOnsInfo.getAddOnsInfo().get(addOnName)).doubleValue() * addOnNum;

        }

        return money;
    }


    /**
     * Getter.
     * @return total moneu of all suit records.
     */
    public static double getTotalMoney() {
        double totalMoney = 0.0d;

        for (Object suit : suitsRecord) {
            totalMoney += (double) ((JSONObject) suit).get("money");
        }

        return totalMoney;
    }

}
