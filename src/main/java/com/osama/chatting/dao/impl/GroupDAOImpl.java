package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.GroupDao;
import com.osama.chatting.entities.group.Group;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that provide access to the database and deal with application Group entity.
 *
 * @author Osama ALfaqeeh.
 * @see AbstractCommonDaoImpl
 * @see GroupDao
 * @see Group
 */
public class GroupDAOImpl extends AbstractCommonDaoImpl<Group> implements GroupDao {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.group WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE chatting.group WHERE id = ? ";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.group ";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE FROM chatting.group SET admin_id = ?, name = ?, date_created = ?, group_description = ?" +
            "WHERE id = ? ";
    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.group (admin_id, name, date_created, group_description) VALUES (?,?,?,?,?)";
    private static final String SELECT_GROUPS_NAME_AND_ID_BY_USER_ID = "SELECT * FROM chatting.group a INNER JOIN chatting.group_members b " +
            "ON a.id = b.group_id WHERE b.user_id = ?";
    private static final String ID_COLUMN_LABEL = "id";
    private static final String ADMIN_ID_COLUMN_LABEL = "admin_id";
    private static final String NAME__COLUMN_LABEL = "name";
    private static final String DATE_CREATED_COLUMN_LABEL = "date_created";
    private static final String GROUP_DESCRIPTION_COLUMN_LABEL = "group_description";

    public GroupDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method get all groups name and id from database by user id.
     *
     * @param userId The user Id.
     * @return Map of groups name and id for user.
     */
    @Override
    public Map<Integer,String> selectGroupsNameAndIdByUserId(int userId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_GROUPS_NAME_AND_ID_BY_USER_ID, userId)){
            Map<Integer,String> groups = new HashMap<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int groupId = resultSet.getInt(ID_COLUMN_LABEL);
                String groupName = resultSet.getString(NAME__COLUMN_LABEL);
                groups.put(groupId, groupName);
            }

            return groups;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

    }

    /**
     * This method build entity from resultSet object.
     *
     * @param resultSet the resultSet from preparedStatement.
     * @return the entity.
     */
    @Override
    public Group buildEntity(ResultSet resultSet) throws DAOExceptions {
        Group group = new Group();

        try{
         int id = resultSet.getInt(ID_COLUMN_LABEL);
         group.setId(id);

         int adminId = resultSet.getInt(ADMIN_ID_COLUMN_LABEL);
         group.setAdminId(adminId);

         String name = resultSet.getString(NAME__COLUMN_LABEL);
         group.setGroupName(name);

         Date dateCreate = resultSet.getDate(DATE_CREATED_COLUMN_LABEL);
         group.setDateCreated(dateCreate);

         String groupDescription = resultSet.getString(GROUP_DESCRIPTION_COLUMN_LABEL);
         group.setGroupDescription(groupDescription);
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
        return group;
    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(Group entity) {
        List<String> parameterList = new ArrayList<>();

        int adminId = entity.getAdminId();
        String adminIdValue = String.valueOf(adminId);
        parameterList.add(adminIdValue);

        String name = entity.getGroupName();
        parameterList.add(name);

        Date dateCreated = entity.getDateCreated();
        String dateCreatedValue = String.valueOf(dateCreated);
        parameterList.add(dateCreatedValue);

        String groupDescription = entity.getGroupDescription();
        parameterList.add(groupDescription);

        return parameterList;
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
