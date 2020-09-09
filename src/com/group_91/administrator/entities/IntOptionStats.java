package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.group_91.utils.Jsons;

import java.util.*;

/**
 * This class defines weekly int option stats of the orders. Specifically, it is the "Spiciness" in Totoro Ramen Kiosk.
 * After initiated, all static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class IntOptionStats {
    private static HashMap<Integer, Integer> allPopularity;


    /**
     * Constructor.
     */
    public IntOptionStats() {
        setAllPopularity();
    }


    /**
     * Initiate <code>HashMap.</code>
     * Invoke {@link Jsons#getJSONArray(String)}, {@link WeeklyDates}, and some tools.
     * Set {@link IntOptionStats#allPopularity} by calculating spiciness count in the orders.
     */
    private void setAllPopularity() {
        allPopularity = new HashMap<>();
        for (int i = 0; i <= 5; ++i) {  // spiciness range from 0 to 5 (included)
            allPopularity.put(i, 0);
        }

        JSONArray orders = Jsons.getJSONArray("orders");

        for (Object orderObject : orders) {
            JSONObject order = (JSONObject) orderObject;
            JSONArray suitsInOrder = (JSONArray) order.get("suits");

            for (Object suitObject : suitsInOrder) {
                JSONObject suit = (JSONObject) suitObject;

                int value = (int) suit.get("Spiciness");
                allPopularity.put(value, allPopularity.get(value) + 1);
            }
        }
    }


    /**
     * Get the most popular int option in the restaurant. Specifically, it is the spiciness.
     * Will return a HashMap of a single key-value pair indicating the most popular index and the percentage.
     * @return {@link HashMap}
     */
    public static HashMap<Integer, Double> getMostPopular() {
        HashMap<Integer, Double> result = new HashMap<>();
        Collection<Integer> values = allPopularity.values();
        int suitsCount = 0;
        for (int value : values) {
            suitsCount += value;
        }
        int maxValue = Collections.max(values);
        int maxIndex = Arrays.asList(values.toArray(new Integer[0])).indexOf(maxValue);
        double percentage = (double) maxValue / suitsCount;

        result.put(maxIndex, percentage);

        return result;
    }


    /**
     * Getter.
     * @return {@link IntOptionStats#allPopularity}
     */
    public static HashMap<Integer, Integer> getAllPopularity() {
        return allPopularity;
    }
}
