package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.FriendShipService;
import com.osama.chatting.service.NotificationService;

import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH;

import javax.servlet.http.HttpServletRequest;

public class CreateFriendshipCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        FriendShipService friendShipService = new FriendShipService();
        int firstUserId = Integer.parseInt(request.getParameter(SENDER_ID_PARAMETER));
        int secondUserID = Integer.parseInt(request.getParameter(RECEIVER_ID_PARAMETER));
        int notificationId = Integer.parseInt(request.getParameter(NOTIFICATION_ID));

       try {
          boolean isOperationSuccessfully = friendShipService.creatFriendShip(firstUserId,secondUserID);

          if (!isOperationSuccessfully){
              return new Page(NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH,false);
          }
           System.out.println(notificationId);
           NotificationService notificationService = new NotificationService();
           boolean isDeletedSuccessfully = notificationService.deleteNotificationFromDatabase(notificationId);
           if (!isDeletedSuccessfully){
               return new Page(NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH,false);
           }
           return new Page(NOTIFICATION_FRIENDSHIP_REQUESTS_PAGE_PATH,false);

       }
       catch (ServiceExceptions exceptions){
           System.out.println(exceptions.getMessage());
           return new Page(ERROR_PAGE_PATH,true);
       }
    }
}
