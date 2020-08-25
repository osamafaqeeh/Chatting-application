package com.osama.chatting.service;
import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.dao.impl.NotificationDAOImpl;
import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.dao.NotificationDao;
import com.osama.chatting.entities.notifications.NotificationStatus;
import com.osama.chatting.entities.notifications.NotificationType;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;

import java.util.Date;
import java.util.List;


/**
 * Service class for notification entity.
 *
 * @author Osama Alfaqeeh.
 * @see Notification
 * @see NotificationDao
 * @see ConnectionManager
 */
public class NotificationService {

    public boolean pushNotificationInDatabase(int senderId, int receiverId, NotificationType notificationType) throws ServiceExceptions {
        try(ConnectionManager connectionManager = new ConnectionManager()) {
            AbstractCommonDaoImpl<Notification> notificationDao = new NotificationDAOImpl(connectionManager.getConnection());
            Notification notification = new Notification();
            Date date =new Date();

            notification.setNotificationSenderId(senderId);
            notification.setNotificationReceiverId(receiverId);
            notification.setNotificationType(notificationType);
            notification.setNotificationStatus(NotificationStatus.NOT_SEEN);
            notification.setNotificationDate(new java.sql.Date(date.getTime()));
            return notificationDao.insertEntities(notification);
        }
        catch (DAOExceptions exceptions){
            throw new ServiceExceptions("Exception during push notification in database operation.", exceptions);
        }
    }
    public List<Notification> popFriendShipsNotificationFromDatabase(int userId)throws ServiceExceptions{
        try(ConnectionManager connectionManager = new ConnectionManager()) {

            NotificationDao notificationDao = new NotificationDAOImpl(connectionManager.getConnection());
            return notificationDao.selectAllFriendshipNotificationByUserId(userId);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during pop friendships notification from database operation.", exception);
        }
    }

    /**
     *
     * @param notificationId
     * @return
     * @throws ServiceExceptions
     */
    public boolean deleteNotificationFromDatabase(int notificationId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            AbstractCommonDaoImpl<Notification> notificationDao = new NotificationDAOImpl(connectionManager.getConnection());
           return notificationDao.deleteById(notificationId);
        }
        catch (DAOExceptions exceptions){
            System.out.println(exceptions);
            throw new ServiceExceptions("Exception during delete friendships notification from database operation.", exceptions);
        }
    }

    /**
     *
     * @param userId
     * @return
     * @throws ServiceExceptions
     */
    public List<Notification> findFriendRequestByUserId(int userId) throws ServiceExceptions {
        try(ConnectionManager connectionManager = new ConnectionManager()) {
            NotificationDao notificationDao = new NotificationDAOImpl(connectionManager.getConnection());
            return notificationDao.selectAllFriendshipNotificationByUserId(userId);
        }
        catch (DAOExceptions exceptions){
            throw new ServiceExceptions("Exception during fiend friendships notification from database operation.", exceptions);
        }
    }
}
