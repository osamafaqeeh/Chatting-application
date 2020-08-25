package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.NotificationDao;
import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.notifications.NotificationStatus;
import com.osama.chatting.entities.notifications.NotificationType;
import com.osama.chatting.exceptions.DAOExceptions;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that provide access to the database and deal with application Notification entity.
 *
 * @author Osama Alfaqeeh.
 * @see AbstractCommonDaoImpl
 * @see NotificationDao
 * @see Notification
 */
public class NotificationDAOImpl extends AbstractCommonDaoImpl<Notification> implements NotificationDao {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.notification WHERE id = ? ";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM chatting.notification WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.notification";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE chatting.notification SET notification_sender_id = ?, notification_receiver_id = ?," +
            "notification_type = ?,notification_status = ?, notification_date = ? WHERE id = ?";

    private static final String UPDATE_ALL_STATUS_ENTITY_QUERY = "UPDATE chatting.notification SET notification_status = ? WHERE notification_receiver_id = ? AND notification_type = ?";

    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.notification (notification_sender_id, notification_receiver_id, notification_type," +
            " notification_status, notification_date) VALUES (?,?,?,?,?)";

    private static final String SELECT_ALL_USER_MESSAGE_NOTIFICATION_BY_USER_ID = "SELECT * FROM chatting.notification WHERE notification_receiver_id = ?" +
            " AND notification_type = 'USER_MASSAGE' ";

    private static final String SELECT_ALL_GROUP_MESSAGE_NOTIFICATION_BY_USER_ID = "SELECT * FROM chatting.notification WHERE notification_receiver_id = ?" +
            " AND notification_type = 'GROUP_MASSAGE' ";

    private static final String SELECT_ALL_FRIENDSHIP_NOTIFICATION_BY_USER_ID = "SELECT * FROM chatting.notification WHERE notification_receiver_id = ?" +
            " AND notification_type = 'FRIEND_SHIP' ";

    private static final String ID_COLUMN_LABEL = "id";
    private static final String NOTIFICATION_SENDER_ID = "notification_sender_id";
    private static final String NOTIFICATION_RECEIVER_ID = "notification_receiver_id";
    private static final String NOTIFICATION_TYPE = "notification_type";
    private static final String NOTIFICATION_STATUS = "notification_status";
    private static final String NOTIFICATION_DATE = "notification_date";

    public NotificationDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method get all user message notification By user id.
     *
     * @param userId Thw user id.
     * @return List of Message notification.
     */
    @Override
    public List<Notification> selectAllUserMessageNotificationByUserId(int userId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_ALL_USER_MESSAGE_NOTIFICATION_BY_USER_ID,userId)){
            List<Notification>userMessagesNotifications = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Notification notification;
                notification = buildEntity(resultSet);
                userMessagesNotifications.add(notification);
            }

            return userMessagesNotifications;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method get all group message notification By user id.
     *
     * @param userId Thw user id.
     * @return List of Message notification.
     */
    @Override
    public List<Notification> selectAllGroupMessageNotificationByUserId(int userId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_ALL_GROUP_MESSAGE_NOTIFICATION_BY_USER_ID,userId)){
            List<Notification>userMessagesNotifications = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Notification notification;
                notification = buildEntity(resultSet);
                userMessagesNotifications.add(notification);
            }

            return userMessagesNotifications;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method get all friendship notification By user id.
     *
     * @param userId Thw user id.
     * @return List of friendship notification.
     */
    @Override
    public List<Notification> selectAllFriendshipNotificationByUserId(int userId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_ALL_FRIENDSHIP_NOTIFICATION_BY_USER_ID,userId)){
            List<Notification>userMessagesNotifications = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Notification notification;
                notification = buildEntity(resultSet);
                userMessagesNotifications.add(notification);
            }

            return userMessagesNotifications;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

    }

    public boolean updateAllStatusByNotificationTypeAndUserId(NotificationStatus notificationStatus, NotificationType notificationType, int userId) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(UPDATE_ALL_STATUS_ENTITY_QUERY, String.valueOf(notificationStatus), userId, String.valueOf(notificationType))) {

            int queryResult = preparedStatement.executeUpdate();

            return queryResult != EMPTY_RESULT;
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
    public Notification buildEntity(ResultSet resultSet) throws DAOExceptions {
        Notification notification = new Notification();

        try {
            int id = resultSet.getInt(ID_COLUMN_LABEL);
            notification.setId(id);

            int notificationSenderId = resultSet.getInt(NOTIFICATION_SENDER_ID);
            notification.setNotificationSenderId(notificationSenderId);

            int notificationReceiverId = resultSet.getInt(NOTIFICATION_RECEIVER_ID);
            notification.setNotificationReceiverId(notificationReceiverId);

            String notificationTypeValue = resultSet.getString(NOTIFICATION_TYPE);
            NotificationType notificationType = NotificationType.valueOf(notificationTypeValue);
            notification.setNotificationType(notificationType);

            String notificationStatusValue = resultSet.getString(NOTIFICATION_STATUS);
            NotificationStatus notificationStatus = NotificationStatus.valueOf(notificationStatusValue);
            notification.setNotificationStatus(notificationStatus);

            Date notificationDate = resultSet.getDate(NOTIFICATION_DATE);
            notification.setNotificationDate(notificationDate);
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

        return notification;
    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(Notification entity) {
        List<String> parameterList = new ArrayList<>();

        int notificationSenderId = entity.getNotificationSenderId();
        String notificationSenderIdValue = String.valueOf(notificationSenderId);
        parameterList.add(notificationSenderIdValue);

        int notificationReceiverId = entity.getNotificationReceiverId();
        String notificationReceiverIdValue = String.valueOf(notificationReceiverId);
        parameterList.add(notificationReceiverIdValue);

        NotificationType notificationType = entity.getNotificationType();
        String notificationTypeValue = String.valueOf(notificationType);
        parameterList.add(notificationTypeValue);

        NotificationStatus notificationStatus = entity.getNotificationStatus();
        String notificationStatusValue = String.valueOf(notificationStatus);
        parameterList.add(notificationStatusValue);

        Date notificationDate = entity.getNotificationDate();
        String notificationDateValue = String.valueOf(notificationDate);
        parameterList.add(notificationDateValue);

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
