package com.group_91.tests;

import com.group_91.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class defines a TDD test case for signing-in function of the project.
 *
 * @author Yingke Ding
 */
public class SignInTest {

    /**
     * <code>username</code> (Phone/email) or <code>memberNo</code> (membership number) and password should match.
     * @see Utils#checkUserIDPassword(String, String, String)
     */
    @Test
    void testCheckingUserIDPassword() {

        ArrayList<String[]> correctUserInfos = new ArrayList<>();
        correctUserInfos.add(new String[]{"username", "kknahols@qq.com", "testPassword"});
        correctUserInfos.add(new String[]{"username", "13983014651", "testPassword"});
        correctUserInfos.add(new String[]{"username", "jhhnxhu@outlook.com", "testPassword"});
        correctUserInfos.add(new String[]{"username", "13316673982", "testPassword"});
        correctUserInfos.add(new String[]{"username", "myEmail@bupt.com", "123456"});
        correctUserInfos.add(new String[]{"memberNo", "00000001", "testPassword"});
        correctUserInfos.add(new String[]{"memberNo", "00000023", "testPassword"});
        correctUserInfos.add(new String[]{"memberNo", "00000034", "testPassword"});
        correctUserInfos.add(new String[]{"memberNo", "00000145", "testPassword"});

        ArrayList<String[]> wrongPasswordInfos = new ArrayList<>();
        wrongPasswordInfos.add(new String[]{"username", "kknahols@qq.com", "fakePassword"});
        wrongPasswordInfos.add(new String[]{"username", "13983014651", "iForget"});
        wrongPasswordInfos.add(new String[]{"memberNo", "00000145", "123456"});

        ArrayList<String[]> notFoundIDInfos = new ArrayList<>();
        notFoundIDInfos.add(new String[]{"username", "fakeID@what.com", "testPassword"});
        notFoundIDInfos.add(new String[]{"username", "13655556666", "testPassword"});
        notFoundIDInfos.add(new String[]{"memberNo", "12345678", "testPassword"});

        ArrayList<String[]> normalMistakeInfos = new ArrayList<>();
        normalMistakeInfos.add(new String[]{"username", "", "sadad"});
        normalMistakeInfos.add(new String[]{"username", "13083575454", ""});
        normalMistakeInfos.add(new String[]{"memberNo", "", ""});
        normalMistakeInfos.add(new String[]{"memberNo", "00000001", ""});

        try {
            for (String[] correctUserInfo : correctUserInfos) {
                // 1 means id and password match
                assertEquals(Utils.checkUserIDPassword(correctUserInfo[0], correctUserInfo[1], correctUserInfo[2]), 1);
            }

            for (String[] wrongPasswordInfo : wrongPasswordInfos) {
                // -1 means id correct but password fails.
                assertEquals(Utils.checkUserIDPassword(wrongPasswordInfo[0], wrongPasswordInfo[1], wrongPasswordInfo[2]), -1);
            }

            for (String[] notFoundIDInfo : notFoundIDInfos) {
                // 0 means username not found
                assertEquals(Utils.checkUserIDPassword(notFoundIDInfo[0], notFoundIDInfo[1], notFoundIDInfo[2]), 0);
            }

            for (String[] normalMistakeInfo : normalMistakeInfos) {
                // -2 means either username or password missing
                assertEquals(Utils.checkUserIDPassword(normalMistakeInfo[0], normalMistakeInfo[1], normalMistakeInfo[2]), -2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
