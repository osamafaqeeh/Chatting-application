package com.osama.chatting.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager  {

    public static final String NONE_MESSAGE_KEY = "NONE";
    public static final String REGISTRATION_FAILED_MESSAGE_KEY = "message.registration_failed";
    public static final String INVALID_INPUT_DATA_MESSAGE_KEY = "message.invalid_input_data";
    public static final String LOGIN_NOT_AVAILABLE_MESSAGE_KEY = "message.login_not_available";
    public static final String REGISTRATION_SUCCESSFUL_MESSAGE_KEY = "message.registration_success";
    public static final String LOGIN_ERROR_MESSAGE_KEY = "message.login_error";




    public static final Locale DEFAULT_LOCALE = new Locale("","");
    public static final String RESOURCE_FILE_NAME = "message";

    public static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME, DEFAULT_LOCALE);

    public MessageManager() {
    }

    public static String getProperty(String key){
       return resourceBundle.getString(key);

    }

    public static void changLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME,locale);
    }
}
