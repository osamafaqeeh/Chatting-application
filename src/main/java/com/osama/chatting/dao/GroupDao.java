package com.osama.chatting.dao;

import com.osama.chatting.entities.group.Group;
import com.osama.chatting.exceptions.DAOExceptions;

import java.util.List;
import java.util.Map;

/**
 *  User DAO interface has method to work with Group.
 */
public interface GroupDao {

    /**
     * This method get all groups name and id from database by user id.
     *
     * @param userId The user Id.
     * @return Map of groups name and id for user.
     */
    Map<Integer,String> selectGroupsNameAndIdByUserId(int userId) throws DAOExceptions;
}
