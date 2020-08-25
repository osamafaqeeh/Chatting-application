package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.GroupMembersDao;
import com.osama.chatting.entities.groupMumbers.GroupMembers;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMembersDAOImpl extends AbstractCommonDaoImpl<GroupMembers> implements GroupMembersDao {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.group_members WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE chatting.group_members WHERE id = ? ";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.group_members ";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE FROM chatting.group_members SET group_id = ?, join_date = ?, user_id = ? WHERE id = ? ";
    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.group_members (group_id, join_date, user_id) VALUES (?,?,?,?,?,?)";

    private static final String ID_COLUMN_LABEL = "id";
    private static final String GROUP_ID_COLUMN_LABEL = "group_id";
    private static final String JOIN_DATE_COLUMN_LABEL = "join_date";
    private static final String USER_ID_COLUMN_LABEL = "user_id";

    /**
     * Instantiates a new AbstractDAOImpl.
     *
     * @param connection the connection to database.
     */
    public GroupMembersDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method build entity from resultSet object.
     *
     * @param resultSet the resultSet from preparedStatement.
     * @return the entity.
     */
    @Override
    public GroupMembers buildEntity(ResultSet resultSet) throws DAOExceptions {
        GroupMembers groupMembers = new GroupMembers();
        try {
            int id = resultSet.getInt(ID_COLUMN_LABEL);
            groupMembers.setId(id);

            int groupId = resultSet.getInt(GROUP_ID_COLUMN_LABEL);
            groupMembers.setGroupId(groupId);

            Date joinDate = resultSet.getDate(JOIN_DATE_COLUMN_LABEL);
            groupMembers.setJoinDate(joinDate);

            int userId = resultSet.getInt(USER_ID_COLUMN_LABEL);
            groupMembers.setUserId(userId);

            return groupMembers;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(GroupMembers entity) {
        List<String> entityParameter = new ArrayList<>();

        int groupId = entity.getGroupId();
        String groupIdValue = String.valueOf(groupId);
        entityParameter.add(groupIdValue);

        Date joinDate = entity.getJoinDate();
        String joinDateValue = String.valueOf(joinDate);
        entityParameter.add(joinDateValue);

        int userId = entity.getUserId();
        String userIdValue = String.valueOf(userId);
        entityParameter.add(userIdValue);
        return entityParameter;
    }

    /**
     * This method build common Query
     *
     * @return Map of common query key and common query.
     */
    @Override
    public Map<String, String> initializeCommonQuery() {
        Map<String, String> commonQueryMap = new HashMap<>();

        commonQueryMap.put(SELECT_BY_ID_QUERY_KEY, SELECT_BY_ID_QUERY);
        commonQueryMap.put(SELECT_ALL_QUERY_KEY, SELECT_ALL_QUERY);
        commonQueryMap.put(INSERT_ENTITY_QUERY_KEY, INSERT_ENTITY_QUERY);
        commonQueryMap.put(UPDATE_ENTITY_QUERY_KEY, UPDATE_ENTITY_QUERY);
        commonQueryMap.put(DELETE_BY_ID_QUERY_KEY, DELETE_BY_ID_QUERY);

        return commonQueryMap;
    }
}
