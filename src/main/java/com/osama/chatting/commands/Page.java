package com.osama.chatting.commands;
import static com.osama.chatting.utils.MessageManager.NONE_MESSAGE_KEY;

public class Page {
    private String urlPage;
    private boolean isDirect;
    private String messageKey;

    public static String LOGIN_PAGE = "/jsp/common/login.jsp";
    public static String REGISTER_PAGE = "/jsp/common/register.jsp";
    public static String ERROR_PAGE_PATH = "/jsp/common/error.jsp";
    public static  String CHAT_BOX_PAGE_PATH = "/jsp/user/chatBox.jsp";
    public static String MAIN_PAGE_PATH = "/jsp/common/main.jsp";
    public static String FRIEND_PAGE_PATH = "/jsp/user/friends.jsp";
    public static String NEW_FRIENDS_PATH = "/jsp/user/newFriends.jsp";
    public static String NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH = "/jsp/user/Notifications_friendship_requests.jsp";


    public Page(String urlPage, boolean isDirect, String messageKey) {
        this.urlPage = urlPage;
        this.isDirect = isDirect;
        this.messageKey = messageKey;
    }

    public Page(String urlPage, boolean isDirect) {
        this.urlPage = urlPage;
        this.isDirect = isDirect;
        this.messageKey = NONE_MESSAGE_KEY;
    }

    public String getUrlPage() {
        return urlPage;
    }

    public void setUrlPage(String urlPage) {
        this.urlPage = urlPage;

    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }


}
