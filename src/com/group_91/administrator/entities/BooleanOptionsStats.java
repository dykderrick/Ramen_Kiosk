package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.customer.entities.SuitInfo;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * This class defines weekly Boolean Options stats of the orders.
 * After initiated, all static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class BooleanOptionsStats {
    private static HashMap<String, Integer[]> weeklyStats;


    /**
     * Constructor.
     */
    public BooleanOptionsStats() {
        setWeeklyStats();
    }


    /**
     * Initiate <code>HashMap.</code>
     * Invoke {@link Jsons#getJSONArray(String)}, {@link WeeklyDates}, and some tools.
     * Set {@link BooleanOptionsStats#weeklyStats} by calculating spiciness count in the orders.
     */
    private void setWeeklyStats() {
        weeklyStats = new HashMap<>();

        for (String optionName : SuitInfo.getBooleanOptionsNames()) {
            Integer[] integers = {0, 0, 0, 0, 0, 0, 0};
            weeklyStats.put(optionName, integers);
        }

        JSONArray orders = Jsons.getJSONArray("orders");

        // O(n) complexity
        for (Object orderObject : orders) {
            JSONObject order = (JSONObject) orderObject;
            String date = Utils.convertLongTimeToDate((long) (order.get("createTime")));

            if (Arrays.asList(WeeklyDates.getWeeklyDates()).contains(date)) {
                int index = Arrays.asList(WeeklyDates.getWeeklyDates()).indexOf(date);
                JSONArray suitsInOrder = (JSONArray) (order.get("suits"));

                for (Object suitObject : suitsInOrder) {
                    JSONObject suit = (JSONObject) suitObject;

                    for (String key : suit.keySet()) {
                        if (weeklyStats.containsKey(key)) {
                            if ((boolean) suit.get(key)) {
                                weeklyStats.get(key)[index] += 1;
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Get a specific boolean option item weekly stats.
     * @param choiceName String
     * @return an Integer array of last week's sale on this boolean option.
     */
    public static Integer[] getChoiceWeeklyStats(String choiceName) {
        return weeklyStats.get(choiceName);
    }

}
