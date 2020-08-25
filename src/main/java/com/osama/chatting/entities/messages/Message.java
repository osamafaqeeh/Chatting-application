package com.osama.chatting.entities.messages;
import  com.osama.chatting.entities.Entity;

import java.sql.Date;
import java.util.Objects;

/**
 * This class describes message for application.
 *
 * @author Osama Alfaqeeh.
 * @see Entity
 * @see MessageStatus
 * @see MessageType
 */
public class Message extends Entity {

    private int senderId;
    private int receiverId;
    private String messageText;
    private Date messageDate;
    private MessageType messageType;
    private MessageStatus messageStatus;

    /**
     * Instantiates a new message.
     */
    public Message() {
    }

    /**
     * Get's the usersId who sent the message.
     *
     * @returnthe usersId who sent the message.
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * Set's the usersId who sent the message.
     *
     * @param senderId the usersId who sent the message.
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    /**
     * Get's the usersId who Receiver the message.
     *
     * @returnthe usersId who Receiver the message.
     */
    public int getReceiverId() {
        return receiverId;
    }

    /**
     * Set's the usersId who Receiver the message.
     *
     * @param receiverId usersId who Receiver the message.
     */
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * Get's messages text.
     *
     * @return messages text.
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Set's the messages text.
     *
     * @param messageText the messages text.
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Get's message date.
     * @return message date.
     */
    public Date getMessageDate() {
        return messageDate;
    }

    /**
     * Set's the message date.
     *
     * @param messageDate the message date.
     */
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    /**
     * Get's message type -- GROUP_MESSAGE OR USERS_MESSAGE.
     *
     * @return The message type .
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Set's the message type -- GROUP_MESSAGE OR USERS_MESSAGE.
     *
     * @param messageType the message type.
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * Get's messages status seen or not seen.
     * @return the message status.
     */
    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    /**
     * Set's the messages status seen or not seen .
     * @param messageStatus the messages status .
     */
    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    /**
     * this method equals two object's.
     *
     * @param object the object.
     * @return true if object's are equal and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Message message = (Message) object;
        return senderId == message.senderId &&
                receiverId == message.receiverId &&
                Objects.equals(messageText, message.messageText) &&
                Objects.equals(messageDate, message.messageDate) &&
                messageType == message.messageType &&
                messageStatus == message.messageStatus;
    }

    /**
     * this method calculate object's hashcode.
     * @return
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + senderId;
        result = 31 * result + receiverId;
        result = 31 * result + (messageDate != null ? messageDate.hashCode() : 0);
        result = 31 * result + (messageStatus != null ? messageStatus.hashCode() : 0);
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        result = 31 * result + (messageText != null ? messageText.hashCode() : 0);

        return result;
    }

    /**
     * this method build information about object's.
     *
     * @return String information about object's.
     */
    @Override
    public String toString() {
        return "Message{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageText='" + messageText + '\'' +
                ", messageDate=" + messageDate +
                ", messagesType=" + messageType +
                ", messageStatus=" + messageStatus +
                '}';
    }
}
