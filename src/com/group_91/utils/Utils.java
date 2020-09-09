package com.group_91.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.input.DataFormat;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Useful tools here.
 *
 * @author Yingke Ding
 */
public class Utils {
    public static final int ID_NUMBER_LENGTH = 8;


    /**
     * Supplement zeros before some number.
     * @param num number to be processed.
     * @param totalDigits how many digits including zeros.
     * @return a string.
     */
    public static String zeroPad(int num, int totalDigits) {
        return String.format("%" + totalDigits + "d", num).replace(" ", "0");
    }


    /**
     * Check user inputs: username (email or phone no.) and password.
     * @param type id_type.
     * @param id email, phone no. or member no.
     * @param password string from JPasswordField.
     * @return 1 if both username and password correct; -1 if password incorrect; 0 if username not found and -2 if something missing.
     */
    public static int checkUserIDPassword(String type, String id, String password) throws Exception {
        if (id.equals("") || password.equals("")) {
            return -2;
        }
        String encryptedPassword = MD5EncryptedPassword(password);  // MD5 Encryption

        JSONArray customers = Jsons.getJSONArray("customers");
        boolean isInList = false;
        boolean isValid = false;

        for (int i = 0; i < customers.size(); ++i) {
            String userLoginID;
            if (type.equals("username")) {
                userLoginID = (String) ((JSONObject) customers.getJSONObject(i).get("UserLoginID")).get("ID");
            }
            else {
                userLoginID = (String) customers.getJSONObject(i).get("memberNo");
            }

            if (id.equals(userLoginID)) {
                isInList = true;

                // Match.
                if (encryptedPassword.equals(customers.getJSONObject(i).get("Password"))) {
                    isValid = true;
                }
                break;
            }

        }

        if (isInList) {
            if (isValid) {  // 都正确
                return 1;
            }
            else {  // 密码错误
                return -1;
            }
        }

        else {  // 不在表中
            return 0;
        }

    }


    /**
     * Check a String is valid phone no. or email address or not.
     * @param string String to be checked.
     * @return Boolean.
     */
    public static boolean isValidPhoneOrEmail(String string) {
        if (isAllNumbers(string)) {  // Possible Phone No.
            return isValidPhoneNumber(string);
        }
        else {
            return isValidEmail(string);
        }
    }


    /**
     * Check a String is numeric or not.
     * @param string String to be checked.
     * @return Boolean.
     */
    public static boolean isAllNumbers(String string) {
        String regexStr = "^[0-9]*$";

        return string.matches(regexStr);

    }


    /**
     * Check if a phone is 11-digit number or not.
     * See TDD cases: "./tests/RegistrationTest"
     * @param phone a String phone No.
     * @return Boolean.
     */
    public static boolean isValidPhoneNumber(String phone) {
        String regexStr = "^[0-9]{11}$";  // 中国大陆11位数字

        Pattern pat = Pattern.compile(regexStr);
        if (phone == null) {
            return false;
        }
        return pat.matcher(phone).matches();
    }


    /**
     * Check if an email address is valid or invalid.
     * See reference: <a href="url">https://www.geeksforgeeks.org/check-email-address-valid-not-java/</a>
     * See TDD case: "./tests/RegistrationTest"
     * @param email address.
     * @return Boolean.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }


    /**
     * Check if a text is a positive integer or float. <p></p>
     * Example -- true: 1.5, 6, 3.14
     *            false: -1.3, asd, 3.x
     * @param text String to be tested
     * @return true if passed otherwise false
     */
    public static boolean isPositiveNumeric(String text) {
        String regex = "^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";

        return text.matches(regex);
    }


    /**
     * Get MD5 encrypted password.
     * @param password raw password.
     * @return Encrypted String.
     * @throws Exception if errors.
     */
    public static String MD5EncryptedPassword(String password) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误 "+e.toString());
        }
    }


    /**
     * Save a String to a File. If file not exists, create one.
     * @param string Contents to be saved.
     * @param path File path.
     */
    public static void saveStringToTextFile(String string, String path) {
        FileWriter writer = null;
        if (Files.notExists(Paths.get(path))) {
            new File(path);
        }

        try {
            writer = new FileWriter(path);
            writer.write("");
            writer.write(string);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Open Text file in os.
     * See reference: <a>https://www.yiibai.com/java/java-open-file.html</a>
     * @param path Text file path.
     * @throws Exception if file doesn't exist or the OS cannot support opening txt file.
     */
    public static void openTextFile(String path) throws Exception {
        File file = new File(path);

        // 首先检查平台是否支持Desktop
        if(!Desktop.isDesktopSupported()){
            throw new Exception("OS CANNOT OPEN " + path);
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            desktop.open(file);
        }
    }


    /**
     * Wait some time.
     * @param ms millisecond.
     */
    public static void waitTime(int ms) {

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * Convert the long type time (in millisecond) to a yyyy-MM-dd date.
     * @param time millisecond
     * @return String of yyyy-MM-dd date
     */
    public static String convertLongTimeToDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date result = new Date(time);

        return simpleDateFormat.format(result);

    }

}
