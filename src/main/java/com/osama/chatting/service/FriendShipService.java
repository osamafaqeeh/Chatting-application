package com.osama.chatting.service;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.dao.impl.FriendShipDAOImpl;
import com.osama.chatting.entities.FriendShip.FriendShip;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;

import java.util.Date;

/**
 * Service class for friendShip entity.
 *
 * @author Osama Alfaqeeh.
 * @see FriendShip
 * @see FriendShipDAOImpl
 * @see ConnectionManager
 */
public class FriendShipService {

    /**
     * This method creat friendship between two users.
     *
     * @param firstUserId the first user in friendship.
     * @param secondUserId the first user in friendship.
     * @return true if the friendship created successfully and false otherwise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public boolean creatFriendShip(int firstUserId, int secondUserId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<FriendShip> friendShipDao = new FriendShipDAOImpl(connectionManager.getConnection());
            Date date = new Date();
            FriendShip friendShip = new FriendShip();
            friendShip.setFirstUserId(firstUserId);
            friendShip.setSecondUserID(secondUserId);
            friendShip.setFriendShipDate(new java.sql.Date(date.getTime()));
            return friendShipDao.insertEntities(friendShip);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during creat friend ship operation.", exception);
        }
    }
}
