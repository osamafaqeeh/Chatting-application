package com.osama.chatting.commands.common;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.osama.chatting.commands.Page.*;
import static com.osama.chatting.utils.MessageManager.LOGIN_ERROR_MESSAGE_KEY;

public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());
    @Override
    public Page execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        UserService userService = new UserService();
        try {
          User user =userService.login(email, password);

          if (user == null){
              return new Page(LOGIN_PAGE, false, LOGIN_ERROR_MESSAGE_KEY );
          }
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute(USER_ATTRIBUTE,user);
          return new Page(MAIN_PAGE_PATH,true);
        }
        catch (ServiceExceptions exceptions){
            LOGGER.log(Level.WARNING, exceptions.getMessage(), exceptions);
            return new Page(ERROR_PAGE_PATH,true);
        }

    }
}
