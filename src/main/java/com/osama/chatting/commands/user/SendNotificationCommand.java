package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.entities.notifications.NotificationType;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.NotificationService;
import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.NEW_FRIENDS_PATH;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendNotificationCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(SendNotificationCommand.class.getName());
    @Override
    public Page execute(HttpServletRequest request) {
        NotificationService notificationService = new NotificationService();
        int senderId = Integer.parseInt(request.getParameter(SENDER_ID_PARAMETER));
        int receiverId = Integer.parseInt(request.getParameter(RECEIVER_ID_PARAMETER));
        NotificationType notificationType = NotificationType.valueOf(request.getParameter(NOTIFICATION_TYPE_PARAMETER));

        try {
            boolean isOperationSuccessful = notificationService.pushNotificationInDatabase(senderId, receiverId, notificationType) ;
            return new Page(NEW_FRIENDS_PATH,false);
        }
        catch (ServiceExceptions exceptions){
            LOGGER.log(Level.WARNING,exceptions.getMessage(),exceptions);
            return new Page(ERROR_PAGE_PATH,false);

        }
    }

}
