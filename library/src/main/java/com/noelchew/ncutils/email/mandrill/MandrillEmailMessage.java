package com.noelchew.ncutils.email.mandrill;

import java.util.ArrayList;

/**
 * Created by noelchew on 9/1/15.
 */
public class MandrillEmailMessage {
    private String text;
    private String subject;
    private String from_email;
    private String from_name;
    private ArrayList<MandrillRecipient> to;

    public MandrillEmailMessage(String subject, String message, ArrayList<MandrillRecipient> recipients, MandrillSender sender) {
        this.text = message;
        this.subject = subject;
        this.from_email = sender.getEmail();
        this.from_name = sender.getName();
        this.to = recipients;
    }
}
