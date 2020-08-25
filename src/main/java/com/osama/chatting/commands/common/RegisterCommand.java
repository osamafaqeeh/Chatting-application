package com.osama.chatting.commands.common;

import com.osama.chatting.commands.ActionCommand;
import com.osama.chatting.commands.Page;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.service.UserService;
import javax.servlet.http.HttpServletRequest;

import com.osama.chatting.utils.PasswordEncoder;
import com.osama.chatting.utils.UserDateValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.osama.chatting.commands.Page.*;
import static com.osama.chatting.utils.MessageManager.REGISTRATION_FAILED_MESSAGE_KEY;
import static com.osama.chatting.utils.MessageManager.INVALID_INPUT_DATA_MESSAGE_KEY;
import static com.osama.chatting.utils.MessageManager.LOGIN_NOT_AVAILABLE_MESSAGE_KEY;
import static com.osama.chatting.utils.MessageManager.REGISTRATION_SUCCESSFUL_MESSAGE_KEY;
public class RegisterCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class.getName());
    @Override
    public Page execute(HttpServletRequest request) {
      try {
          UserService userService = new UserService();
          UserDateValidator userDateValidator = new UserDateValidator();

          String firstName = request.getParameter(FIRST_NAME_PARAMETER);
          String lastName = request.getParameter(LAST_NAME_PARAMETER);
          String email = request.getParameter(EMAIL_PARAMETER);
          String password = request.getParameter(PASSWORD_PARAMETER);
          String phoneNumber = request.getParameter(PHONE_NUMBER_PARAMETER);


          boolean isUserDataValid = userDateValidator.checkData(firstName,lastName,email,password,phoneNumber);
          if (!isUserDataValid){
              return new Page(REGISTER_PAGE,true, INVALID_INPUT_DATA_MESSAGE_KEY);
          }

          boolean isEmailNotUnique = userService.checkUserEmailForUnique(email);
          if (isEmailNotUnique){
              return new Page(REGISTER_PAGE,true, LOGIN_NOT_AVAILABLE_MESSAGE_KEY);
          }

          password = PasswordEncoder.encode(password);
          boolean isOperationSuccessful = userService.register(firstName, lastName, email, password, phoneNumber);

          if (!isOperationSuccessful){
              return new Page(REGISTER_PAGE,true,REGISTRATION_FAILED_MESSAGE_KEY);
          }

          return new Page(LOGIN_PAGE,false,REGISTRATION_SUCCESSFUL_MESSAGE_KEY);

      }
      catch (ServiceExceptions exception){
          LOGGER.log(Level.WARNING,exception.getMessage(),exception);
          return new Page(ERROR_PAGE_PATH, true);
      }
    }
}
