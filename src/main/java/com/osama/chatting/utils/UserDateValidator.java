package com.osama.chatting.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDateValidator {

    private static final String NAME_PATTERN = "[A-Za-z]+";
    private static final String SPACE_PATTERN = "\\s";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,40})";
    private static final String PHONE_NUMBER_PATTERN = "([0-9]+){10}";

    /**
     * Check user registration data for errors.
     *
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email.
     * @param password The user's password.
     * @param phoneNumber The user's phone number.
     * @return result of validation.
     */
    public boolean checkData(String firstName, String lastName, String email, String password, String phoneNumber){
        if (firstName == null || firstName.isEmpty()){
            return false;
        }
        if (lastName == null || lastName.isEmpty()){
            return false;
        }
        if (email == null || email.isEmpty()){
            return false;
        }
        if (password == null || password.isEmpty()){
            return false;
        }
        if (phoneNumber == null || phoneNumber.isEmpty()){
            return false;
        }

        boolean isFirstNameValid = matchPattern(firstName,NAME_PATTERN);
        boolean isLastNameValid = matchPattern(lastName,NAME_PATTERN);
        boolean isEmailValid = matchPattern(email,EMAIL_PATTERN);
        boolean isPasswordValid = matchPattern(password,PASSWORD_PATTERN);
        boolean isPhoneNumberValid = matchPattern(phoneNumber,PHONE_NUMBER_PATTERN);

        return isFirstNameValid && isLastNameValid && isEmailValid && isPasswordValid && isPhoneNumberValid;

    }
    /**
     * is method check name to include first name and last name.Also checks it's syntax.
     *
     * @param name the user's name.
     * @return true if data is valid and false otherwise.
     */
    public boolean isFullName (String name){
        if (name == null || name.isEmpty()){
            return false;
        }

        String pattern = NAME_PATTERN + SPACE_PATTERN + NAME_PATTERN;

        return matchPattern(name, pattern);

    }
    private boolean matchPattern(String data, String currentPattern){
        Pattern pattern = Pattern.compile(currentPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.find();
    }
}
