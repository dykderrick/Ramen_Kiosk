package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

/**
 * This class defines weekly Suits sale stats of the orders.
 * After initiated, all static variables can be accessed all the time.
 *
 * @author Yingke Ding
 */
public class SuitStats {
    private static int[] weeklySales;


    /**
     * Constructor.
     */
    public SuitStats() {
        setWeeklySales();
    }


    /**
     * Get the sale count of one specific day.
     * @param date in a format of yyyy-MM-dd. Example: "2020-05-20"
     * @return sales count of the day
     */
    private int getSingleDaySale(String date) {
        JSONArray orders = Jsons.getJSONArray("orders");
        int saleCount = 0;

        for (Object orderObject : orders) {
            JSONObject order = (JSONObject) orderObject;

            if (Utils.convertLongTimeToDate((long) order.get("createTime")).equals(date)) {
                saleCount += ((JSONArray) order.get("suits")).size();
            }

        }

        return saleCount;
    }


    /**
     * {@link SuitStats#weeklySales} setter.
     * Invoke {@link WeeklyDates} to get the last week (from Sunday to Monday) sales data.
     * Save change in {@link SuitStats#weeklySales}
     */
    private void setWeeklySales() {
        weeklySales = new int[7];

        for (int j = 0; j < 7; ++j) {
            weeklySales[j] = getSingleDaySale(WeeklyDates.getWeeklyDates()[j]);
        }

    }


    /**
     * Getter.
     * @return {@link SuitStats#weeklySales}
     */
    public static int[] getWeeklySales() {
        return weeklySales;
    }

}
