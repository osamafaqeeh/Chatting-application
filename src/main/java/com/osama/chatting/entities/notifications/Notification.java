package com.osama.chatting.entities.notifications;

import com.osama.chatting.entities.Entity;

import java.sql.Date;
import java.util.Objects;

/**
 * This class describes notification for application.
 *
 * @author Osama Alafaqeeh.
 * @see Entity
 * @see NotificationType
 */
public class Notification extends Entity {

    private int notificationSenderId;
    private int notificationReceiverId;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private Date notificationDate;

    /**
     * Instantiates a new Notification.
     */
    public Notification() {
    }

    /**
     * Get's the usersId who sent the notification.
     *
     * @return usersId who sent the notification.
     */
    public int getNotificationSenderId() {
        return notificationSenderId;
    }

    /**
     * Set's the usersId who sent the notification.
     *
     * @param notificationSenderId the usersId who sent the notification.
aaa     */
    public void setNotificationSenderId(int notificationSenderId) {
        this.notificationSenderId = notificationSenderId;
    }

    /**
     * Get's the usersId who receiver the notification.
     *
     * @return usersId who receiver the notification.
     */
    public int getNotificationReceiverId() {
        return notificationReceiverId;
    }

    /**
     * Set's the usersId who receiver the notification.
     *
     * @param notificationReceiverId the usersId who receiver the notification.
     */
    public void setNotificationReceiverId(int notificationReceiverId) {
        this.notificationReceiverId = notificationReceiverId;
    }

    /**
     * Get's the notification Type.
     *
     * @return notification Type.
     */
    public NotificationType getNotificationType() {
        return notificationType;
    }

    /**
     * Set's the notifications type.
     *
     * @param notificationType the notifications type.
     */
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Get's the notification status.
     *
     * @return the notification status.
     */
    public NotificationStatus getNotificationStatus() { return notificationStatus; }

    /**
     * Set's the notification status.
     * @param notificationStatus the notification status.
     */
    public void setNotificationStatus(NotificationStatus notificationStatus) { this.notificationStatus = notificationStatus; }

    /**
     * Get's the notifications date.
     *
     * @return notification date.
     */

    public Date getNotificationDate() {
        return notificationDate;
    }

    /**
     * Set's the notifications Date.
     *
     * @param notificationDate  the notifications Date.
     */
    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    /**
     * This method equals two objects.
     *
     * @param object the object.
     * @return true if objects are equals and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Notification that = (Notification) object;
        return notificationSenderId == that.notificationSenderId &&
                notificationReceiverId == that.notificationReceiverId &&
                notificationType == that.notificationType &&
                notificationStatus == that.notificationStatus &&
                Objects.equals(notificationDate, that.notificationDate);
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return object's hashcode.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (notificationReceiverId);
        result = 31 * result + (notificationSenderId);
        result = 31 * result + (notificationDate != null ? notificationDate.hashCode() : 0);
        result = 31 * result + (notificationType != null ? notificationType.hashCode() : 0);
        result = 31 * result + (notificationStatus != null ? notificationStatus.hashCode() : 0);

        return result;

    }

    /**
     * This method build information about object's.
     *
     * @return String information about object's.
     */
    @Override
    public String toString() {
        return "Notification{" +
                "notificationSenderId=" + notificationSenderId +
                ", notificationReceiverId=" + notificationReceiverId +
                ", notificationType=" + notificationType +
                ", notificationStatus=" + notificationStatus +
                ", notificationDate=" + notificationDate +
                '}';
    }
}
