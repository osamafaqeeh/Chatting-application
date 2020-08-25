package com.osama.chatting.commands;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    //PARAMETER
    String COMMAND_PARAMETER = "command";
    String EMAIL_PARAMETER = "email";
    String PASSWORD_PARAMETER = "password";
    String USER_ID_PARAMETER = "user_id";
    String FIRST_NAME_PARAMETER = "first_name";
    String LAST_NAME_PARAMETER = "last_name";
    String PHONE_NUMBER_PARAMETER = "phone_number";
    String FULL_NAME_PARAMETER = "full_name";
    String SENDER_ID_PARAMETER = "sender_id";
    String RECEIVER_ID_PARAMETER = "receiver_id";
    String NOTIFICATION_TYPE_PARAMETER = "notification_type";
    String NOTIFICATION_ID = "notification_id";
    String FRIEND_ID_PARAMETER = "friend_id";


    //ATTRIBUTE
    String MESSAGE_ATTRIBUTE = "message";
    String USER_ATTRIBUTE = "user";
    String LIST_ATTRIBUTE = "list";
    String USERS_NAME_AND_ID = "usersNameAndId";
    String USER_FRIENDS_ATTRIBUTE = "userFriend";





    public Page execute(HttpServletRequest request);

}
