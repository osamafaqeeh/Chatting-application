package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.UserService;
import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.FRIEND_PAGE_PATH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class FindUserFriendsCommand implements ActionCommand {
    @Override
    public Page execute(HttpServletRequest request) {
        UserService userService = new UserService();
        int userId = Integer.parseInt(request.getParameter(USER_ID_PARAMETER));
        try {
            Map<Integer, String> friends = userService.findUserFriends(userId);
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute(USERS_NAME_AND_ID,friends);
            return new Page(FRIEND_PAGE_PATH,true);
        }
        catch (ServiceExceptions exceptions){
            return new Page(ERROR_PAGE_PATH,true);
        }

    }
}
