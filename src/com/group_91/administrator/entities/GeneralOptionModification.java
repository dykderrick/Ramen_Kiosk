package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.group_91.customer.entities.SuitInfo;
import com.group_91.utils.Jsons;

/**
 * This class defines entity for general options modification function.
 *
 * @author Yingke Ding
 */
public class GeneralOptionModification {

    /**
     * Read and update information of general options to suits.json
     * @param optionName a specific general option name
     * @param choiceName a specific general choice name
     * @param isAvailable stock in/out
     * @throws Exception if updating files fails in
     * {@link Jsons#update(JSONObject, String, String, String)}
     */
    public static void setChoiceAvailability(String optionName,
                                             String choiceName, boolean isAvailable) throws Exception {
        JSONObject modifiedSuitInfo = SuitInfo.getSuitInfo();
        JSONObject originalGeneralOptions = SuitInfo.getGeneralOptions();
        JSONObject modifiedGeneralOptions = SuitInfo.getGeneralOptions();
        JSONArray originalChoicesStatus = (JSONArray) originalGeneralOptions.get(optionName);
        JSONArray modifiedChoicesStatus = new JSONArray();

        for (Object choiceStatusObject : originalChoicesStatus) {
            JSONObject choiceStatus = (JSONObject) choiceStatusObject;

            if (choiceStatus.get("name").equals(choiceName)) {
                choiceStatus.put("status", isAvailable);  // update
            }

            modifiedChoicesStatus.add(choiceStatus);
        }

        modifiedGeneralOptions.put(optionName, modifiedChoicesStatus);
        modifiedSuitInfo.put("generalOptions", modifiedGeneralOptions);

        // updating
        Jsons.update(modifiedSuitInfo, "SUIT00001", "suitID", "suits");
    }


}
