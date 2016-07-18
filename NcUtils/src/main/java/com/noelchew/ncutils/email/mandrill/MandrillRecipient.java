package com.noelchew.ncutils.email.mandrill;

/**
 * Created by noelchew on 9/1/15.
 */
public class MandrillRecipient {
    private String email;
    private String name;
    private String type;

    public MandrillRecipient(String email, String name) {
        this.email = email;
        this.name = name;
        this.type = "to";
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}