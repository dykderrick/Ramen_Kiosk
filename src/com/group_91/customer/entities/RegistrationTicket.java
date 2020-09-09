package com.group_91.customer.entities;

/**
 * This class defines registration ticket when customer signing in.
 *
 * @author Yingke Ding
 */
public class RegistrationTicket {
    private final String firstName, lastName, gender, userLoginID, memberNo, password;
    private String ticket;


    /**
     * Constructor.
     * @param firstName String
     * @param lastName String
     * @param gender String
     * @param userLoginID String
     * @param memberNo String
     * @param password String (before MD5 encryption)
     */
    public RegistrationTicket(String firstName, String lastName, String gender, String userLoginID, String memberNo, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userLoginID = userLoginID;
        this.memberNo = memberNo;
        this.password = password;
        setTicket();
    }


    /**
     * {@link RegistrationTicket#ticket} setter.
     */
    private void setTicket() {
        ticket = "";
        ticket += "[NAME]           " + firstName + " " + lastName + "\n";
        ticket += "[GENDER]         " + gender + "\n";
        ticket += "[PHONE/EMAIL]    " + userLoginID + "\n";
        ticket += "[MEMBERSHIP NO.] " + memberNo + "\n";
        ticket += "[PASSWORD]       " + password + "\n\n\n";

        ticket += "You can use either your Phone/Email\n" +
                "or your membership number with password to login.";
    }


    /**
     * Getter.
     * @return {@link RegistrationTicket#ticket}
     */
    public String getTicket() {
        return ticket;
    }

}
