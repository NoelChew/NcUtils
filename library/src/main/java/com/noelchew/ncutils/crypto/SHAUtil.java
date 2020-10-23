package com.noelchew.ncutils.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static String getSha1Hash(final String s) {
        return getShaHash(s, "SHA-1");
    }

    public static String getSha256Hash(final String s) {
        return getShaHash(s, "SHA-256");
    }
    public static String getShaHash(final String s, String algorithm) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance(algorithm);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            return encodeHexString(messageDigest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String encodeHexString(byte[] messageDigest) {
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return hexString.toString();
    }
}
