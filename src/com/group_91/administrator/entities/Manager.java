package com.group_91.administrator.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

/**
 * This class defines a manager object for admin program.
 *
 * @author Yingke Ding
 */
public class Manager {

    /**
     * Checks manager input id and password.
     * @param id <code>managerID</code>
     * @param password String
     * @return true if both id and password are correct otherwise false
     * @throws Exception if Exceptions are thrown in {@link Utils#MD5EncryptedPassword(String)}
     */
    public static boolean checkIDPassword(String id, String password) throws Exception {
        JSONArray managers = Jsons.getJSONArray("manager");
        JSONObject thisManager = managers.getJSONObject(0);

        return id.equals(thisManager.get("managerID")) && Utils.MD5EncryptedPassword(password).equals(thisManager.get("Password"));
    }
}
