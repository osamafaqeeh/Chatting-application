package com.osama.chatting.dao;

import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.notifications.NotificationStatus;
import com.osama.chatting.entities.notifications.NotificationType;
import com.osama.chatting.exceptions.DAOExceptions;

import java.util.List;

/**
 *  User DAO interface has method to work with Notification.
 */
public interface NotificationDao {

    /**
     * This method get all user message notification By user id.
     *
     * @param userId Thw user id.
     * @return List of Message notification.
     *  @throws DAOExceptions object if execution of query is failed.
     */
    List<Notification> selectAllUserMessageNotificationByUserId(int userId) throws DAOExceptions;

    /**
     * This method get all group message notification By user id.
     *
     * @param userId Thw user id.
     * @return List of Message notification.
     * * @throws DAOExceptions object if execution of query is failed.
     */
    List<Notification> selectAllGroupMessageNotificationByUserId(int userId) throws DAOExceptions;

    /**
     * This method get all friendship notification By user id.
     *
     * @param userId Thw user id.
     * @return List of friendship notification.
     */
    List<Notification> selectAllFriendshipNotificationByUserId(int userId) throws DAOExceptions;

    /**
     * This method update notification status.
     *
     * @param notificationStatus the notification status.
     * @param notificationType the notification type.
     * @param userId the user's id.
     * @return true if updated successfully otherwise false.
     * @throws DAOExceptions object if execution of query is failed.
     */
    public boolean updateAllStatusByNotificationTypeAndUserId(NotificationStatus notificationStatus, NotificationType notificationType, int userId) throws DAOExceptions;
}
