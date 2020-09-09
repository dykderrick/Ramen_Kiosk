package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;

import java.util.Arrays;

/**
 * This class defines an add_on list.
 * It is <b>NOT</b> the add on customer selected, <b>BUT</b> a particular information of add_ons in a restaurant.
 * After instantiated, static variable can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class AddOnsInfo {
    private static JSONObject addOnsInfo;


    /**
     * Constructor.
     * @param restaurantID id of restaurant.
     */
    public AddOnsInfo(String restaurantID) {
        setAddOns(restaurantID);
    }


    /**
     * {@link AddOnsInfo#addOnsInfo} setter.
     * @param restaurantID id of restaurant.
     */
    public void setAddOns(String restaurantID) {
        JSONArray addOnsArray = Jsons.getJSONArray("add_ons");
        boolean isFound = false;

        for (Object addOnsObject : addOnsArray) {
            JSONObject addOns = (JSONObject) addOnsObject;

            if (addOns.get("restaurantID").equals(restaurantID)) {
                addOnsInfo = (JSONObject) addOns.get("addOnsInfo");  // 直接选中内部键值对
                isFound = true;
            }
        }

        if (!isFound) {
            addOnsInfo = null;
        }
    }


    /**
     * Getter.
     * @return {@link AddOnsInfo#addOnsInfo}
     */
    public static JSONObject getAddOnsInfo() {
        return addOnsInfo;
    }


    /**
     * Getter.
     * @return String array of add on's names.
     */
    public static String[] getAddOnsNames() {
        return getAddOnsInfo().keySet().toArray(new String[0]);
    }

}
