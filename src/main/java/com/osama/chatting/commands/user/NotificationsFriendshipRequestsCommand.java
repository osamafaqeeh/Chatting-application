package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.NotificationService;
import com.osama.chatting.service.UserService;

import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationsFriendshipRequestsCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(NotificationsFriendshipRequestsCommand.class.getName());

    @Override
    public Page execute(HttpServletRequest request) {
        UserService userService = new UserService();
        NotificationService notificationService = new NotificationService();
       int userId = Integer.parseInt(request.getParameter(USER_ID_PARAMETER));
        System.out.println(userId);
       try {
           Map<Integer,String>usersNameAndId = userService.findUsersSendFriendshipRequestsToUser(userId);
           List<Notification> friendShipsNotifications = notificationService.findFriendRequestByUserId(userId);

           HttpSession currentSession = request.getSession();
           currentSession.setAttribute(USERS_NAME_AND_ID,usersNameAndId);
           currentSession.setAttribute(LIST_ATTRIBUTE,friendShipsNotifications);

           return new Page(NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH,true);
       }
       catch (ServiceExceptions exceptions){
           LOGGER.log(Level.WARNING,exceptions.getMessage(),exceptions);
           return new Page(ERROR_PAGE_PATH,true);
       }

    }
}
