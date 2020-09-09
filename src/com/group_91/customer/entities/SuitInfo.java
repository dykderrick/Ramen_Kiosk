package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;

import java.math.BigDecimal;
import java.util.Set;

/**
 * This class defines a suit object.
 * It is NOT the suit customer ordered, but a particular information of a suit.
 * After instantiated, static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class SuitInfo {
    private static JSONObject suitInfo;


    /**
     * Constructor.
     * @param suitID id for suit. In this project, it should be "SUIT00001" for ramen.
     */
    public SuitInfo(String suitID) {
        setSuitInfo(suitID);
    }


    /**
     * {@link SuitInfo#suitInfo} setter.
     * @param suitID String
     */
    public void setSuitInfo(String suitID) {
        JSONArray suitsArray = Jsons.getJSONArray("suits");
        boolean isFound = false;

        for (Object suitObject : suitsArray) {
            JSONObject suit = (JSONObject) suitObject;

            if (suit.get("suitID").equals(suitID)) {
                suitInfo = suit;
                isFound = true;
            }
        }

        if (!isFound) {
            suitInfo = null;
        }
    }


    /**
     * Getter.
     * @return {@link SuitInfo#suitInfo}
     */
    public static JSONObject getSuitInfo() {
        return suitInfo;
    }


    /**
     * Get choices' name and status of a specific general option.
     * Example: "soup" ---- "Tonkotsu", true; "Shoyu", true; "Shio", true.
     * @param generalOptionName Example: "soup".
     * @return a JSONArray.
     */
    public static JSONArray getGeneralChoicesNameAndStatus(String generalOptionName) {
        return (JSONArray) getGeneralOptions().get(generalOptionName);
    }


    /**
     * Get choices names of a specific general option.
     * @param generalOptionName Example: "soup".
     * @return String array.
     */
    public static String[] getGeneralChoicesNames(String generalOptionName) {
        JSONArray nameStatus = getGeneralChoicesNameAndStatus(generalOptionName);
        String[] names = new String[nameStatus.size()];
        int index = 0;
        for (Object elementObject : nameStatus) {
            JSONObject element = (JSONObject) elementObject;
            names[index] = (String) element.get("name");
            index++;
        }
        return names;
    }


    /**
     * Get all general options' names.
     * @return String array.
     */
    public static String[] getGeneralOptionsNames() {
        return getGeneralOptions().keySet().toArray(new String[0]);
    }


    /**
     * Getter.
     * @return the <code>generalOptions</code> field in {@link SuitInfo#suitInfo}
     */
    public static JSONObject getGeneralOptions() {
        return (JSONObject) suitInfo.get("generalOptions");
    }


    /**
     * Getter.
     * @return the <code>intOptions</code> field in {@link SuitInfo#suitInfo}
     */
    public static JSONArray getIntOptions() {
        return (JSONArray) suitInfo.get("intOptions");
    }


    /**
     * Getter.
     * @return the <code>booleanOptions</code> field in {@link SuitInfo#suitInfo}
     */
    public static JSONArray getBooleanOptions() {
        return (JSONArray) suitInfo.get("booleanOptions");
    }


    /**
     * Getter.
     * @return String array of boolean options' names.
     */
    public static String[] getBooleanOptionsNames() {
        JSONArray options = getBooleanOptions();
        String[] names = new String[options.size()];
        int index = 0;

        for (Object optionObject : options) {
            JSONObject option = (JSONObject) optionObject;

            names[index] = (String) option.get("name");
            index++;
        }


        return names;
    }


    /**
     * Getter.
     * @return the <code>price</code> field in {@link SuitInfo#suitInfo}
     */
    public static double getPrice() {
        return ((BigDecimal) suitInfo.get("price")).doubleValue();
    }


    /**
     * Getter.
     * @return the <code>suitID</code> field in {@link SuitInfo#suitInfo}
     */
    public static String getSuitID() {
        return (String) suitInfo.get("suitID");
    }


    /**
     * Getter.
     * @return the <code>name</code> field in {@link SuitInfo#suitInfo}
     */
    public static String getSuitName() {
        return (String) suitInfo.get("name");
    }

}
