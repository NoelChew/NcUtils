package com.noelchew.ncutils.email.mandrill;

/**
 * Created by noelchew on 9/1/15.
 */
public class MandrillSender {
    private String name;
    private String email;
    public MandrillSender(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
