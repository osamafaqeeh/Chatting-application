package com.osama.chatting.commands;

import com.osama.chatting.commands.common.*;
import com.osama.chatting.commands.user.*;


public enum CommandType {
    COMMON_LOGIN{
        {
            this.command = new LoginCommand();
        }
    },

    COMMON_REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },

    USER_CREATE_FRIENDSHIP{
        {
            this.command = new CreateFriendshipCommand();
        }
    },
    USER_NEW_FRIENDS{
        {
            this.command = new NewFriendsCommand();
        }
    },
    USER_SEND_NOTIFICATION{
        {
           this.command = new SendNotificationCommand();
        }
    },
    USER_NOTIFICATION_FRIENDSHIP_REQUESTS{
        {
            this.command = new NotificationsFriendshipRequestsCommand();
        }
    },
    USER_FIND_USER_FRIENDS{
        {
            this.command = new FindUserFriendsCommand();
        }
    },
    USER_CHATTING_BOX{
        {
            this.command = new ChattingBoxCommand();
        }
    };

    public ActionCommand command;

    public ActionCommand getCommand(){
        return this.command;
    }
}
