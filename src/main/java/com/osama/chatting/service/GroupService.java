package com.osama.chatting.service;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.dao.GroupDao;
import com.osama.chatting.dao.impl.GroupDAOImpl;
import com.osama.chatting.entities.group.Group;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;


import java.util.Map;

/**
 * Service class for group entity.
 *
 * @author Osama Alfaqeeh.
 * @see Group
 * @see GroupDAOImpl
 * @see ConnectionManager
 */
public class GroupService {

    /**
     * The method creat group into database.
     *
     * @param group the group.
     * @return true if operation was made successful and false otherwise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public boolean creatGroup(Group group) throws ServiceExceptions {

        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<Group> groupDao = new GroupDAOImpl(connectionManager.getConnection());

            return groupDao.insertEntities(group);

        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during creat group operation.", exception);
        }
    }

    /**
     * The method Find the groups the user has joined.
     *
     * @param userId the user id.
     * @return Map of group id and user id.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public Map<Integer,String> FindTheGroupsUserJoined(int userId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            GroupDao groupDao = new GroupDAOImpl(connectionManager.getConnection());

            return groupDao.selectGroupsNameAndIdByUserId(userId);

        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during Find The Groups User Joined group operation.", exception);
        }
    }
}
