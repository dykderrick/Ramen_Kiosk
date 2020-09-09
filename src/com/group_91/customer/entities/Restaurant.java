package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;

/**
 * This class defines a restaurant object.
 * After instantiated, static variables can be accessed all the time.
 * For the restaurant, we usually just use its name, location and other info to be shown in GUI.
 *
 * @author Yingke Ding
 */
public class Restaurant {
    public static JSONObject restaurantInfo;


    /**
     * Constructor.
     * @param restaurantID String of id of restaurant.
     */
    public Restaurant(String restaurantID) {
        setRestaurantInfo(restaurantID);
    }


    /**
     * {@link Restaurant#restaurantInfo} setter.
     * @param restaurantID String
     */
    public void setRestaurantInfo(String restaurantID) {
        JSONArray restaurantArray = Jsons.getJSONArray("restaurants");
        boolean isFound = false;

        for (Object restaurantObject : restaurantArray) {
            JSONObject restaurant = (JSONObject) restaurantObject;

            if (restaurant.get("restaurantID").equals(restaurantID)) {
                restaurantInfo = restaurant;
                isFound = true;
            }
        }

        if (!isFound) {
            restaurantInfo = null;
        }
    }


    /**
     * Getter.
     * @return the name field of {@link Restaurant#restaurantInfo}.
     */
    public static String getRestaurantName() {
        return (String) restaurantInfo.get("Name");
    }


    /**
     * Getter.
     * @return {@link Restaurant#restaurantInfo}
     */
    public static JSONObject getRestaurantInfo() {
        return restaurantInfo;
    }


    /**
     * Getter.
     * @return the id field of {@link Restaurant#restaurantInfo}.
     */
    public static String getRestaurantID() {
        return (String) restaurantInfo.get("restaurantID");
    }

}
