package com.osama.chatting.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Util class for encoding the password.
 */
public class PasswordEncoder {

    /**
     * encode the password using sha256Hex algorithm.
     * @param password the user's password
     * @return the encoded user password.
     */
    public static String encode(String password){
       return DigestUtils.sha256Hex(password);
    }
}
