package com.group_91.customer.entities;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.utils.Jsons;
import com.group_91.utils.Utils;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class defines a customer object.
 * After instantiated in {@link com.group_91.customer.boundaries.CustomerSignIn} class, static variables can be accessed all the time.
 * Access static variable in all other Objects by {@link Customer#getCustomerInfo()}.
 *
 * @author Yingke Ding
 */
public class Customer {
    public static final int STAMPS_DISCOUNT_NUM = 10;
    private static JSONObject customerInfo;  // contains every attributes in a JSON file tuple.
    private static boolean status;  // true for signed in; false for non-customer


    /**
     * Normal customer constructor.
     * @param ID user id.
     * @param idType <code>username</code> or <code>memberNo</code>.
     */
    public Customer(String ID, String idType) {
        setStatus(true);
        if (idType.equals("memberNo")) {
            setCustomerInfoByMemberNo(ID);
        }
        else if (idType.equals("username")) {
            setCustomerInfoByUsername(ID);
        }
        customerInfo.put("status", true);
    }


    /**
     * Non-Membership customer constructor.
     */
    public Customer() {
        setStatus(false);
        customerInfo = new JSONObject();
        customerInfo.put("status", false);
    }


    /**
     * {@link Customer#customerInfo} setter. <p></p>
     * Will call {@link Jsons#getJSONArray(String)} to get all customers list.
     * @param memberNo 8-digit membership number.
     */
    public void setCustomerInfoByMemberNo(String memberNo) {
        JSONArray customerArray = Jsons.getJSONArray("customers");
        boolean isFound = false;

        for (Object customerObject : customerArray) {
            JSONObject customer = (JSONObject) customerObject;

            if (customer.get("memberNo").equals(memberNo)) {
                customerInfo = customer;
                isFound = true;
            }
        }

        if (!isFound) {
            customerInfo = null;
        }

    }


    /**
     * {@link Customer#customerInfo} setter.<p></p>
     * Will call {@link Jsons#getJSONArray(String)} to get all customers list.
     * Different procedure with {@link Customer#setCustomerInfoByMemberNo(String)}, so we don't refactor two methods.
     * @param username phone / email of user.
     */
    public void setCustomerInfoByUsername(String username) {
        JSONArray customerArray = Jsons.getJSONArray("customers");
        boolean isFound = false;

        for (Object customerObject : customerArray) {
            JSONObject customer = (JSONObject) customerObject;
            JSONObject userLoginIDField = (JSONObject) customer.get("UserLoginID");

            if (userLoginIDField.get("ID").equals(username)) {
                isFound = true;
                customerInfo = customer;
            }
        }

        if (!isFound) {
            customerInfo = null;
        }


    }


    /**
     * Getter.
     * @return {@link Customer#customerInfo}
     */
    public static JSONObject getCustomerInfo() {
        return customerInfo;
    }


    /**
     * Name checker. When signing in, first name and last name fields cannot be null.
     * @param name String
     * @return true if names are valid otherwise false.
     */
    public static boolean isValidName(String name) {
        return !name.equals("");
    }


    /**
     * Gender checker. When signing in, gender field cannot be null.
     * @param gender String
     * @return true if not null
     */
    public static boolean isValidGender(String gender) {
        return !gender.equals("");
    }


    /**
     * Invoke {@link Utils#isValidPhoneOrEmail(String)} to check validation of <code>loginID</code> when signing up.
     * @param loginID String
     * @return true if valid otherwise false.
     */
    public static boolean isValidUserID(String loginID) {
        return Utils.isValidPhoneOrEmail(loginID);
    }


    /**
     * Checking procedure for password field when customer signing up. <p></p>
     * Password should be at least 6-characters.
     * @param password String
     * @return true if matches constraints.
     */
    public static boolean isValidPassword(String password) {
        // v4: Add 6-char constraint.
        String regex = "^[a-zA-Z0-9]{6,}$";
        return password.matches(regex);
    }


    /**
     * Invoke a {@link JSONObject} to save info of customer.
     * @param firstName String
     * @param lastName String
     * @param gender String
     * @param userLoginID String
     * @param password String (MD5 encrypted)
     * @return {@link JSONObject}
     * @throws Exception thrown from {@link Utils#MD5EncryptedPassword(String)} or {@link Jsons#getJSONFileSize(String)}.
     */
    public static JSONObject createCustomer(String firstName, String lastName, String gender, String userLoginID, String password) throws Exception {
        JSONObject customer = new JSONObject();

        customer.put("First_Name", firstName);
        customer.put("Last_Name", lastName);
        customer.put("Gender", gender);

        HashMap<String, String> userLoginIDField = new HashMap<>();
        if (userLoginID.contains("@")) {  // naive classification. Test cases has already been passed before.
            userLoginIDField.put("type", "Email");
        }
        else {
            userLoginIDField.put("type", "Phone");
        }
        userLoginIDField.put("ID", userLoginID);
        customer.put("UserLoginID", userLoginIDField);

        customer.put("Password", Utils.MD5EncryptedPassword(password));  // v4: add MD5 encryption
        String memberNo = Utils.zeroPad(Jsons.getJSONFileSize("customers") + 1, Utils.ID_NUMBER_LENGTH);
        customer.put("memberNo", memberNo);
        customer.put("customerID", "CUST" + memberNo);
        customer.put("Stamps", 0);

        return customer;
    }


    /**
     * Get customer's stamps.
     * @return int
     */
    public static int getStamps() {
        return (int) customerInfo.get("Stamps");
    }


    /**
     * Get customer's username.
     * @return String
     */
    public static String getUsername() {
        return (String) ((JSONObject) customerInfo.get("UserLoginID")).get("ID");
    }


    /**
     * Get customer's name.
     * @return String
     */
    public static String getName() {
        if (status) {
            return customerInfo.get("First_Name").toString() + " " + customerInfo.get("Last_Name").toString();
        }
        else {
            return "Dear Customer";  // fake name
        }
    }


    /**
     * Get status.
     * @return true if customer have signed in or false if not signed in.
     */
    public static boolean getStatus() {
        return status;
    }


    /**
     * {@link Customer#status} setter. <p></p>
     * Invoked after customer decide to sign in or not.
     * @param type boolean
     */
    private void setStatus(boolean type) {
        status = type;
    }


    /**
     * Get the current customerID. If no customer signs in, return null.
     * @return String.
     */
    public static String getCustomerID() {
        return (String) customerInfo.get("customerID");
    }


    /**
     * Check if this customer satisfies stamps discounting condition.
     * @return true if satisfaction otherwise false.
     */
    public static boolean isStampsDiscount() {
        if (status) {
            return getStamps() >= STAMPS_DISCOUNT_NUM;
        }
        else return false;
    }


    /**
     * Invoked if orders are finished. <p></p>
     * Will modify customers.json to increase stamps count of the specific customer.
     * @param amount int
     */
    public static void addStamps(int amount) {
        int originalStamps = (int) customerInfo.get("Stamps");
        customerInfo.put("Stamps", originalStamps + amount);

        // Save to file.
        try {
            customerInfo.remove("status");
            Jsons.update(customerInfo, getCustomerID(), "customerID", "customers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Invoked if orders are finished.
     * Will modify customers.json to decrease stamps count of the specific customer.
     * @param amount int
     * @throws Exception if stamps remaining is less than amount.
     */
    public static void minusStamps(int amount) throws Exception {
        int originalStamps = (int) customerInfo.get("Stamps");

        if (originalStamps >= amount) {
            customerInfo.put("Stamps", originalStamps - amount);
        }

        else {
            throw new Exception("STAMPS NOT ENOUGH!");
        }

        // Save to file.
        try {
            customerInfo.remove("status");
            Jsons.update(customerInfo, getCustomerID(), "customerID", "customers");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
