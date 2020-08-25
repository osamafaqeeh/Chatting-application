package com.osama.chatting.commands;

import javax.servlet.http.HttpServletRequest;
import static com.osama.chatting.commands.ActionCommand.COMMAND_PARAMETER;

public class CommandFactory {

    public ActionCommand defineCommand(HttpServletRequest request){
        String command = request.getParameter(COMMAND_PARAMETER);
        ActionCommand actionCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
        return  actionCommand;

    }
}
