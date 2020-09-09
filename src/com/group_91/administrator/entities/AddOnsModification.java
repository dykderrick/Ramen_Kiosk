package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;

/**
 * This class defines entity of add-on-modification function.
 *
 * @author Yingke Ding
 */
public class AddOnsModification {

    /**
     * Read and update information to add_ons.json
     * @param addOnNames add on names array
     * @param prices add on prices array
     * @param availabilities stock in/out selection array
     * @throws Exception if updating files fails in
     * {@link Jsons#update(JSONObject, String, String, String)}
     */
    public static void setAddOnsPricesAndStatuses(String[] addOnNames,
                                                  double[] prices, boolean[] availabilities) throws Exception {

        JSONObject modifiedAddOnsInfo = new JSONObject();

        for (int i = 0; i < addOnNames.length; ++i) {
            if (availabilities[i]) {
                modifiedAddOnsInfo.put(addOnNames[i], prices[i]);
            }
            else {
                modifiedAddOnsInfo.put(addOnNames[i], - 1. * prices[i]);
            }
        }

        JSONObject all = new JSONObject();
        all.put("addOnsInfo", modifiedAddOnsInfo);
        all.put("restaurantID", "REST0001");

        // Updating
        Jsons.update(all, "REST0001", "restaurantID", "add_ons");

    }

}
