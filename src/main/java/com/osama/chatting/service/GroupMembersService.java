package com.osama.chatting.service;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.dao.impl.GroupMembersDAOImpl;
import com.osama.chatting.entities.groupMumbers.GroupMembers;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;

/**
 * Service class for group members entity.
 *
 * @author Osama Alfaqeeh.
 * @see GroupMembers
 * @see GroupMembersDAOImpl
 * @see ConnectionManager
 */
public class GroupMembersService {

    /**
     * This method Add a user to a group.
     *
     * @param groupMember The group member.
     * @return true if the user add successfully to a group and false otherwise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public boolean joinToGroup(GroupMembers groupMember) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<GroupMembers> groupMembersDao = new GroupMembersDAOImpl(connectionManager.getConnection());

            return groupMembersDao.insertEntities(groupMember);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during creat friend ship operation.", exception);
        }
    }

}
