package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.customer.entities.SuitInfo;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import java.util.HashMap;

/**
 * This class defines weekly General Options stats of the orders.
 * After initiated, all static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class GeneralOptionsStats {
    private static HashMap<String, HashMap<String, Integer[]>> weeklyStats;


    /**
     * Constructor.
     */
    public GeneralOptionsStats() {
        setWeeklyStats();
    }


    /**
     * Initiate <code>HashMap.</code>
     * Invoke {@link Jsons#getJSONArray(String)}, {@link WeeklyDates}, and some tools.
     * Set {@link GeneralOptionsStats#weeklyStats} by calculating spiciness count in the orders.
     */
    private void setWeeklyStats() {
        weeklyStats = new HashMap<>();

        for (String generalOptionName : SuitInfo.getGeneralOptionsNames()) {
            HashMap<String, Integer[]> choicesHashMap = new HashMap<>();
            for (String name : SuitInfo.getGeneralChoicesNames(generalOptionName)) {
                Integer[] integers = {0, 0, 0, 0, 0, 0, 0};
                choicesHashMap.put(name, integers);
            }
            weeklyStats.put(generalOptionName, choicesHashMap);
        }


        JSONArray orders = Jsons.getJSONArray("orders");

        for (int i = 0; i < 7 ; ++i) {
            for (Object orderObject : orders) {
                String date = Utils.convertLongTimeToDate((long) (((JSONObject) orderObject).get("createTime")));

                if (date.equals(WeeklyDates.getWeeklyDates()[i])) {
                    JSONArray suitsInOrder = (JSONArray) ((JSONObject) orderObject).get("suits");

                    for (Object suitObject : suitsInOrder) {
                        JSONObject suit = (JSONObject) suitObject;

                        for (String key : suit.keySet()) {
                            if (weeklyStats.containsKey(key)) {
                                weeklyStats.get(key).get((String) suit.get(key))[i] += 1;
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Get a specific general option item weekly stats.
     * @param optionName String
     * @return an Integer array of last week's sale on this general option.
     */
    public static HashMap<String, Integer[]> getWeeklyStats(String optionName) {
        return weeklyStats.get(optionName);
    }


}
