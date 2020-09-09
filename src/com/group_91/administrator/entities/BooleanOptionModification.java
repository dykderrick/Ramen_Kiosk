package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.group_91.customer.entities.SuitInfo;
import com.group_91.utils.Jsons;

/**
 * This class defines entity of boolean option modification function.
 *
 * @author Yingke Ding
 */
public class BooleanOptionModification {


    /**
     * Read and update information of boolean options to suits.json
     * @param optionName a specific boolean option name
     * @param isAvailable stock in/out selection
     * @throws Exception if updating fails in {@link Jsons#update(JSONObject, String, String, String)}
     */
    public static void setBooleanChoiceAvailability(String optionName, boolean isAvailable) throws Exception {
        JSONObject modifiedSuitInfo = SuitInfo.getSuitInfo();
        JSONArray originalBooleanOptions = SuitInfo.getBooleanOptions();
        JSONArray modifiedBooleanOptions = new JSONArray();


        for (int i = 0; i < originalBooleanOptions.size(); ++i) {
            JSONObject optionStatus = originalBooleanOptions.getJSONObject(i);

            if (optionStatus.get("name").equals(optionName)) {
                optionStatus.put("status", isAvailable);
            }

            modifiedBooleanOptions.add(optionStatus);
        }

        modifiedSuitInfo.put("booleanOptions", modifiedBooleanOptions);

        // updating
        Jsons.update(modifiedSuitInfo, "SUIT00001", "suitID", "suits");

    }


}
