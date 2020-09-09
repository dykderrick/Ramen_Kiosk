package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.customer.entities.AddOnsInfo;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * This class defines weekly Add on stats of the orders.
 * After initiated, all static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class AddOnsStats {
    private static HashMap<String, Integer[]> weeklyStats;


    /**
     * Constructor.
     */
    public AddOnsStats() {
        setWeeklyStats();
    }


    /**
     * Initiate <code>HashMap.</code>
     * Invoke {@link Jsons#getJSONArray(String)}, {@link WeeklyDates}, and some tools.
     * Set {@link AddOnsStats#weeklyStats} by calculating spiciness count in the orders.
     */
    private void setWeeklyStats() {
        weeklyStats = new HashMap<>();

        for (String name : AddOnsInfo.getAddOnsNames()) {
            Integer[] values = {0, 0, 0, 0, 0, 0, 0};
            weeklyStats.put(name, values);
        }

        JSONArray orders = Jsons.getJSONArray("orders");

        for (Object orderObject : orders) {
            JSONObject order = (JSONObject) orderObject;

            String date = Utils.convertLongTimeToDate((long) (order.get("createTime")));

            if (Arrays.asList(WeeklyDates.getWeeklyDates()).contains(date)) {
                int index = Arrays.asList(WeeklyDates.getWeeklyDates()).indexOf(date);
                JSONArray suitsInOrder = (JSONArray) order.get("suits");

                for (Object suitObject : suitsInOrder) {
                    JSONObject suit = (JSONObject) suitObject;
                    JSONObject addOnsInSuit = (JSONObject) suit.get("add_ons");

                    for (String key : addOnsInSuit.keySet()) {
                        weeklyStats.get(key)[index] += (int) addOnsInSuit.get(key);
                    }
                }
            }
        }
    }


    /**
     * Get a specific add-on item weekly stats.
     * @param addOnName String
     * @return an Integer array of last week's sale on this add-on.
     */
    public static Integer[] getSpecificAddOnWeeklyStats(String addOnName) {
        return weeklyStats.get(addOnName);
    }

}
