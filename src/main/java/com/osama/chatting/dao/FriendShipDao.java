package com.osama.chatting.dao;

import com.osama.chatting.exceptions.DAOExceptions;

/**
 *  User DAO interface has method to work with friendShip.
 */
public interface FriendShipDao {

    /**
     * This method check if the first user is friend for second user.
     * @param firstUserId The user id.
     * @param secondUserId The user id.
     * @return true If they are friends and false otherwise.
     */
    boolean checkIfFriends(int firstUserId, int secondUserId) throws DAOExceptions;

}
