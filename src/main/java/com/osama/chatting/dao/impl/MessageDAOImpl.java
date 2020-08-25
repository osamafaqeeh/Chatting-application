package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.MessageDao;
import com.osama.chatting.entities.messages.Message;
import com.osama.chatting.entities.messages.MessageStatus;
import com.osama.chatting.entities.messages.MessageType;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;

/**
 * Class that provide access to the database and deal with application User entity.
 *
 * @author Osama Alfaqeeh
 * @see AbstractCommonDaoImpl
 * @see MessageDao
 * @see Message
 */
public class MessageDAOImpl extends AbstractCommonDaoImpl<Message> implements MessageDao {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.message WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE chatting.message WHERE id = ? ";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.message";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE chatting.message SET sender_id = ?, receiver_id = ?, message_text = ?, message_date = ?" +
            "message_type = ?, message_status = ? WHERE id = ?";

    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.message (sender_id, receiver_id, message_text, message_date, message_type, message_status)" +
            "VALUES (?,?,?,?,?,?)";

    private static final String SELECT_ALL_MESSAGE_FROM_FRIEND ="SELECT * FROM chatting.message WHERE ( chatting.receiver_id = ? " +
            "AND chatting.sender_id = ? ) OR ( chatting.receiver_id = ? AND chatting.sender_id = ? ) AND message.type = 'USER_MESSAGE' ";

    private static final String SELECT_ALL_MESSAGE_FROM_GROUP ="SELECT * FROM chatting.message WHERE  chatting.receiver_id = ? " +
            "AND chatting.sender_id = ? AND message_status = 'SEEN' AND message.type = 'GROUP_MESSAGE'  ";

    private static final String ID_COLUMN_LABEL = "id";
    private static final String SENDER_ID_COLUMN_LABEL = "sender_id";
    private static final String RECEIVER_ID_COLUMN_LABEL = "receiver_id";
    private static final String MESSAGE_TEXT_COLUMN_LABEL = "message_text";
    private static final String MESSAGE_DATE_COLUMN_LABEL = "message_date";
    private static final String MESSAGE_TYPE_COLUMN_LABEL = "message_type";
    private static final String MESSAGE_STATUS_COLUMN_LABEL = "message_status";





    /**
     * Instantiates a new AbstractDAOImpl.
     *
     * @param connection the connection to database.
     */
    public MessageDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method get all old message form friend.
     *
     * @param userId the user id.
     * @param friendId the friend user id.
     * @return list of message that send from friend to user.
     */
    @Override
    public List<Message> selectAllOldMessagesFormFriend(int userId, int friendId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_ALL_MESSAGE_FROM_FRIEND, userId ,friendId)){
            List<Message>friendMessagesList =new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Message message = buildEntity(resultSet);
                friendMessagesList.add(message);
            }

            return friendMessagesList;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

    }

    /**
     * This method get all old message in group.
     *
     * @param groupId the group id.
     * @return List of message in group.
     */
    @Override
    public List<Message> selectAllOldMessagesFormGroup(int groupId) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_ALL_MESSAGE_FROM_GROUP, groupId)){
            List<Message>groupMessagesList =new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Message message = buildEntity(resultSet);
                groupMessagesList.add(message);
            }

            return groupMessagesList;
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
    public Message buildEntity(ResultSet resultSet) throws DAOExceptions {

        Message message = new Message();
       try {
           int id = resultSet.getInt(ID_COLUMN_LABEL);
           message.setId(id);

           int senderId = resultSet.getInt(SENDER_ID_COLUMN_LABEL);
           message.setSenderId(senderId);

           int receiverId = resultSet.getInt(RECEIVER_ID_COLUMN_LABEL);
           message.setReceiverId(receiverId);

           String messageText = resultSet.getString(MESSAGE_TEXT_COLUMN_LABEL);
           message.setMessageText(messageText);

           Date messageDate = resultSet.getDate(MESSAGE_DATE_COLUMN_LABEL);
           message.setMessageDate(messageDate);

           String messagesTypeValue = resultSet.getString(MESSAGE_TYPE_COLUMN_LABEL);
           MessageType messageType = MessageType.valueOf(messagesTypeValue);
           message.setMessageType(messageType);

           String messageStatusValue = resultSet.getString(MESSAGE_STATUS_COLUMN_LABEL);
           MessageStatus messageStatus = MessageStatus.valueOf(messageStatusValue);
           message.setMessageStatus(messageStatus);

       }
       catch (SQLException exception){
           throw new DAOExceptions(exception.getMessage(), exception);
       }
       return message;
    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(Message entity) {
        List<String> parameterList = new ArrayList<>();

        int senderId = entity.getSenderId();
        String senderIdValue = String.valueOf(senderId);
        parameterList.add(senderIdValue);

        int receiverId = entity.getReceiverId();
        String receiverIdValue = String.valueOf(receiverId);
        parameterList.add(receiverIdValue);

        String messageText = entity.getMessageText();
        parameterList.add(messageText);

        Date messageDate = entity.getMessageDate();
        String dateValue = String.valueOf(messageDate);
        parameterList.add(dateValue);

        MessageType messageType = entity.getMessageType();
        String messageTypeValue = String.valueOf(messageType);
        parameterList.add(messageTypeValue);

        MessageStatus messageStatus = entity.getMessageStatus();
        String messageStatusValue = String.valueOf(messageStatus);
        parameterList.add(messageStatusValue);

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
