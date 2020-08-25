package com.osama.chatting.service;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.dao.MessageDao;
import com.osama.chatting.dao.impl.MessageDAOImpl;
import com.osama.chatting.entities.messages.Message;
import com.osama.chatting.entities.messages.MessageStatus;
import com.osama.chatting.entities.messages.MessageType;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Message entity.
 *
 */
public class MessageService {

    /**
     * The method push the message from the user to a friend in the database.
     *
     * @param senderId The friend id.
     * @param receiverId The user id.
     * @param messageText The message text.
     * @return true if the message push in database and false otherWise.
     * @throws ServiceExceptions  ServiceException object if execution of method is failed.
     */
    public boolean pushFriendShipsMessagesInDatabase(int senderId, int receiverId, String messageText) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<Message> messageDao = new MessageDAOImpl(connectionManager.getConnection());

            Message message = new Message();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setMessageType(MessageType.USER_MESSAGE);
            message.setMessageText(messageText);
            message.setMessageStatus(MessageStatus.NOT_SEEN);

            return messageDao.insertEntities(message);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during send message to user operation.", exception);
        }
    }

    /**
     * The method push the message from the user to a group in the database.
     *
     * @param senderId The friend id.
     * @param groupId The group id.
     * @param messageText The message text.
     * @return true if the message push in database and false otherWise.
     * @throws ServiceExceptions  ServiceException object if execution of method is failed.
     */
    public boolean pushGroupsMessagesInDataBase(int senderId, int groupId, String messageText) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<Message> messageDao = new MessageDAOImpl(connectionManager.getConnection());

            Message message = new Message();
            message.setSenderId(senderId);
            message.setReceiverId(groupId);
            message.setMessageType(MessageType.GROUP_MESSAGE);
            message.setMessageText(messageText);
            message.setMessageStatus(MessageStatus.NOT_SEEN);

            return messageDao.insertEntities(message);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during Push message from friend in database operation.", exception);
        }
    }

    /**
     * The method Pop the old messages from the database to a user.
     *
     * @param userId The user id.
     * @param friendId The friend id.
     * @return true if the old messages pop from database to user and false otherWise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public List<Message> popFriendMessagesFromDatabase(int userId, int friendId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            MessageDao messageDao = new MessageDAOImpl(connectionManager.getConnection());

            return messageDao.selectAllOldMessagesFormFriend(userId, friendId);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during pop old message from friend to user from database operation.", exception);
        }
    }

    /**
     *  The method Pop the old messages from the database to a group.
     *
     * @param groupId The group Id.
     * @return true if the old messages pop from database to group and false otherWise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public List<Message> popGroupMessagesFromDatabase(int groupId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            MessageDao messageDao = new MessageDAOImpl(connectionManager.getConnection());

            return messageDao.selectAllOldMessagesFormGroup(groupId);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during pop old message from user to group from database operation.", exception);
        }
    }

}
