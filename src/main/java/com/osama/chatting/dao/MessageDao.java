package com.osama.chatting.dao;


import com.osama.chatting.entities.messages.Message;
import com.osama.chatting.exceptions.DAOExceptions;

import java.util.List;

/**
 *  User DAO interface has method to work with Message.
 */
public interface MessageDao {

    /**
     * This method get all old message form friend.
     *
     * @param userId the user id.
     * @param friendId the friend user id.
     * @return list of message that send from friend to user.
     */
    List<Message> selectAllOldMessagesFormFriend(int userId, int friendId) throws DAOExceptions;

    /**
     * This method get all old message in group.
     *
     * @param groupId the group id.
     * @return List of message in group.
     */
    List<Message> selectAllOldMessagesFormGroup(int groupId) throws DAOExceptions;
}
