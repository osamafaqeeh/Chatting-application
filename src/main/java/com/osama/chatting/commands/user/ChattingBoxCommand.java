package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.entities.messages.Message;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.MessageService;
import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.CHAT_BOX_PAGE_PATH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChattingBoxCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        MessageService messageService = new MessageService();
        int friendId = Integer.parseInt(request.getParameter(FRIEND_ID_PARAMETER));
        int userId = Integer.parseInt(request.getParameter(USER_ID_PARAMETER));
        try {
            List<Message> messages = messageService.popFriendMessagesFromDatabase(userId,friendId);
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute(LIST_ATTRIBUTE,messages);
            return new Page(CHAT_BOX_PAGE_PATH,true);
        }
        catch (ServiceExceptions exceptions){
            return new Page(ERROR_PAGE_PATH,true);
        }
    }
}
