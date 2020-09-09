package com.group_91.tests;

import com.group_91.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class defines a TDD class for registration function of the project.
 *
 * @author Yingke Ding
 */
public class RegistrationTest {

    /**
     * Email format should be as "example@me.com".
     * @see Utils#isValidEmail(String) 
     */
    @Test
    void testEmail() {
        String[] invalidEmails = {"", "myEmail.com", "his@", "@st.com", "1241451", "myEmail@bupt", "myEmail@bupt.", "myEmail@bupt..com"};
        String[] validEmails = {"her@icloud.com", "his@bupt.edu.cn"};
        for (String invalidEmail : invalidEmails) {
            assertFalse(Utils.isValidEmail(invalidEmail));
        }

        for (String validEmail : validEmails) {
            assertTrue(Utils.isValidEmail(validEmail));
        }
    }


    /**
     * Phone format should be as 11-digit numbers.
     * @see Utils#isValidPhoneNumber(String) 
     */
    @Test
    void testPhoneNumber() {
        String[] invalidPhones = {"12345", "1234567890", "123456789012", "asdas", "asdf123"};
        String[] validPhones = {"12345678901", "13602324568", "13955186341", "15101045842"};

        for (String invalidPhone : invalidPhones) {
            assertFalse(Utils.isValidPhoneNumber(invalidPhone));
        }

        for (String validPhone : validPhones) {
            assertTrue(Utils.isValidPhoneNumber(validPhone));
        }
    }




}
