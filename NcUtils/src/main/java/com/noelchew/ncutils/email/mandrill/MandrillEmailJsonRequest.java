package com.noelchew.ncutils.email.mandrill;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by noelchew on 8/24/15.
 */
public class MandrillEmailJsonRequest {
    public static final String API_BASE_URL = "https://mandrillapp.com/api/1.0/";
    public static final String EMAIL_API_PATH = "messages/send.json";
    private String key;
    private MandrillEmailMessage message;

    public MandrillEmailJsonRequest(String apiKey, String subject, String message, ArrayList<MandrillRecipient> recipients, MandrillSender sender) {
        this.key = apiKey;
        this.message = new MandrillEmailMessage(subject, message, recipients, sender);
    }

    public MandrillEmailJsonRequest(String apiKey, String subject, String message, String recipientEmail, String recipientName, String senderEmail, String senderName) {
        this.key = apiKey;
        MandrillRecipient recipient = new MandrillRecipient(recipientEmail, recipientName);
        ArrayList<MandrillRecipient> recipients = new ArrayList<>();
        recipients.add(recipient);

        MandrillSender sender = new MandrillSender(senderEmail, senderName);
        this.message = new MandrillEmailMessage(subject, message, recipients, sender);
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
