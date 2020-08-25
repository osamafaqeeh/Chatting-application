package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.FriendShipDao;
import com.osama.chatting.entities.FriendShip.FriendShip;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that provide access to the database and deal with application FriendShip entity.
 *
 * @author Osama ALfaqeeh.
 * @see AbstractCommonDaoImpl
 * @see FriendShipDao
 * @see FriendShip
 */
public class FriendShipDAOImpl extends AbstractCommonDaoImpl<FriendShip> implements FriendShipDao {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.friendship WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE chatting.friendship WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.friendship ";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE chatting.friendship SET first_user_id = ?, second_user_id = ?, friendSip_date = ?" +
            "WHERE id = ?";
    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.friendship (first_user_id, second_user_id, friendSip_date)" +
            "VALUES (?,?,?)";

    private static final String CHECK_IF_FRIEND = "SELECT * FROM chatting.friendship WHERE first_user_id = ?, second_user_id = ? ";

    private static final String ID_COLUMN_LABEL = "id";
    private static final String FIRST_USER_ID_COLUMN_LABEL = "first_user_id";
    private static final String SECOND_USER_ID_COLUMN_LABEL = "second_user_id";
    private static final String FRIENDSHIP_DATE_COLUMN_LABEL = "friendSip_date";

    public FriendShipDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method check if the first user is friend for second user.
     * @param firstUserId The user id.
     * @param secondUserId The user id.
     * @return true If they are friends and false otherwise.
     */
    @Override
    public boolean checkIfFriends(int firstUserId, int secondUserId) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(CHECK_IF_FRIEND,firstUserId,secondUserId)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
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
    public FriendShip buildEntity(ResultSet resultSet) throws DAOExceptions {
        FriendShip friendShip = new FriendShip();

        try {
            int id = resultSet.getInt(ID_COLUMN_LABEL);
            friendShip.setId(id);

            int firstUserId = resultSet.getInt(FIRST_USER_ID_COLUMN_LABEL);
            friendShip.setFirstUserId(firstUserId);

            int secondUserId = resultSet.getInt(SECOND_USER_ID_COLUMN_LABEL);
            friendShip.setSecondUserID(secondUserId);

            Date friendShipDate = resultSet.getDate(FRIENDSHIP_DATE_COLUMN_LABEL);
            friendShip.setFriendShipDate(friendShipDate);
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

        return friendShip;

    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(FriendShip entity) {
        List<String> parameterList = new ArrayList<>();

        int firstUserId = entity.getFirstUserId();
        String firstUserIdValue = String.valueOf(firstUserId);
        parameterList.add(firstUserIdValue);

        int secondUserId = entity.getSecondUserID();
        String secondUserIdValue = String.valueOf(secondUserId);
        parameterList.add(secondUserIdValue);

        Date friendShipDate = entity.getFriendShipDate();
        String friendShipDateValue = String.valueOf(friendShipDate);
        parameterList.add(friendShipDateValue);

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
