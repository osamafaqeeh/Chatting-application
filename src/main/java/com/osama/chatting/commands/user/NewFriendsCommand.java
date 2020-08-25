package com.osama.chatting.commands.user;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.osama.chatting.commands.Page.ERROR_PAGE_PATH;
import static com.osama.chatting.commands.Page.NEW_FRIENDS_PATH;

public class NewFriendsCommand implements ActionCommand {
    private static final  Logger LOGGER = Logger.getLogger(NewFriendsCommand.class.getName());
    @Override
    public Page execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String fullName = request.getParameter(FULL_NAME_PARAMETER);
        System.out.println(fullName);
        try {
          List<User> users =  userService.findUserByFullName(fullName);
          request.setAttribute(LIST_ATTRIBUTE,users);
          return new Page(NEW_FRIENDS_PATH,false);

        }
        catch (ServiceExceptions exception){
            LOGGER.log(Level.WARNING,exception.getMessage(),exception);
            return new Page(ERROR_PAGE_PATH,true);
        }
    }
}
