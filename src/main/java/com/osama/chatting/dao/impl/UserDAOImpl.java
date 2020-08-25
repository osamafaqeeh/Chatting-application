package com.osama.chatting.dao.impl;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.UserDao;
import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.notifications.NotificationStatus;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.entities.user.UserRole;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class that provide access to the database and deal with application User entity.
 *
 * @author Osama Alfaqeeh.
 * @see AbstractCommonDaoImpl
 * @see UserDao
 * @see User
 */
public class UserDAOImpl extends AbstractCommonDaoImpl<User> implements UserDao {

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM chatting.user WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM chatting.user WHERE id = ? ";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM chatting.user";
    private static final String UPDATE_ENTITY_QUERY = "UPDATE chatting.user SET first_name = ?, last_name = ?, user_role = ?, email = ?," +
            " password = ?, phone_number = ? WHERE id = ?";

    private static final String INSERT_ENTITY_QUERY = "INSERT INTO chatting.user (first_name,last_name,user_role,email,password,phone_number) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM chatting.user WHERE email = ? && password = ? ";
    private static final String CHECK_EMAIL_FOR_UNIQUE = "SELECT email FROM chatting.user WHERE email = ?";
    private static final String SELECT_USER_BY_FULL_NAME = "SELECT * FROM chatting.user WHERE first_name = ? && last_name = ? ";
    private static final String SELECT_USER_BY_PART_NAME = "SELECT * FROM chatting.user WHERE first_name LIKE ? OR last_name LIKE ? ";
    private static final String SELECT_USER_ID_AND_FULL_NAME_FOR_USER_FRIENDS = "SELECT a.id, first_name, last_name FROM chatting.user a " +
            "INNER JOIN chatting.friendship b ON a.id = b.second_user_id WHERE b.first_user_id = ?";
    private static final String SELECT_USER_ID_AND_FULL_NAME_FOR_USER_FRIENDS_APP = "SELECT a.id, first_name, last_name FROM chatting.user a " +
            "INNER JOIN chatting.friendship b ON a.id = b.first_user_id WHERE b.second_user_id = ?";

    private static final String SELECT_USER_ID_AND_FULL_NAME_FOR_USERS_IN_GROUP = "SELECT id, first_name, last_name FROM chatting.user a " +
            "INNER JOIN chatting.group_members b ON a.id = b.user_id WHERE group_id = ? ";

    private static final String SELECT_USER_ID_AND_FULL_NAME_FOR_FRIENDS_SEND_MESSAGE_FOR_USER = "SELECT user.id, first_name, last_name FROM chatting.user a " +
            "INNER JOIN chatting.message ON a.id = b.sender_id WHERE receiver_id = ? ";

    private static final String SELECT_USER_ID_AND_FULL_NAME_FOR_USERS_SEND_FRIENDSHIP_REQUESTS_FOR_USER = "SELECT  a.id, first_name, last_name FROM chatting.user a INNER JOIN chatting.notification b ON a.id = b.notification_sender_id" +
            " WHERE b.notification_receiver_id = ? AND b.notification_type = 'FRIEND_SHIP'  ";

    private static final String ID_COLUMN_LABEL = "id";
    private static final String FIRST_NAME_COLUMN_LABEL = "first_name";
    private static final String LAST_NAME_COLUMN_LABEL = "last_name";
    private static final String USER_ROLE_COLUMN_LABEL = "user_role";
    private static final String EMAIL_COLUMN_LABEL = "email";
    private static final String PASSWORD_COLUMN_LABEL = "password";
    private static final String PHONE_NUMBER_COLUMN_LABEL = "phone_number";
    private static final String NOTIFICATION_STATUS_COLUMN_LABEL = "notification_status";


    /**
     * Instantiates a new AbstractDAOImpl.
     *
     * @param connection the connection to database.
     */
    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    /**
     * This method select user from database by email and password.
     *
     * @param email The user email.
     * @param password The user password.
     * @return User The user.
     * @throws DAOExceptions object if execution of query is failed.
     */
    @Override
    public User selectUserByEmailAndPassword(String email, String password) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_BY_EMAIL_AND_PASSWORD, email, password))
        {
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            while (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return user;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     *This method check the email is unique.
     *
     * @return true if email is unique and false otherwise.
     * @throws DAOExceptions object if execution of query is failed.
     */
    @Override
    public boolean checkEmailForUnique(String email) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(CHECK_EMAIL_FOR_UNIQUE, email)){
            ResultSet resultSet = preparedStatement.executeQuery();

           return resultSet.next();

        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method select user from database by first name and last name;
     *
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @throws DAOExceptions object if execution of query is failed.
     * @return The user.
     */
    @Override
    public List<User>  selectUserByFullName(String firstName, String lastName) throws DAOExceptions {
        try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_BY_FULL_NAME, firstName, lastName)){
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("welcome");
            List<User> usersList = new ArrayList<>();

            while (resultSet.next()){

                User user = buildEntity(resultSet);

                usersList.add(user);
            }

            return usersList;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method select user from database by part of name.
     *
     * @param name The user's part name.
     * @return List of users.
     * @throws DAOExceptions object if execution of query is failed.
     */
    @Override
    public List<User> selectUserByPartName(String name) throws DAOExceptions {
       try (PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_BY_PART_NAME,name,name)){
           ResultSet resultSet = preparedStatement.executeQuery();
           List<User> usersList = new ArrayList<>();

           while (resultSet.next()){

               User user = buildEntity(resultSet);

               usersList.add(user);
           }

           return usersList;

       }
       catch (SQLException exception){
           throw new DAOExceptions(exception.getMessage(), exception);
       }
    }

    /**
     * This method select all user id and full name for user friends.
     *
     * @param userId the user id.
     * @return Map of user id and user full name for user friends.
     */
    @Override
    public Map<Integer, String> selectUserIdAndFullNameForUserFriends(int userId) throws DAOExceptions {
        Map<Integer,String> userFriends = new HashMap<>();
        try(PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_ID_AND_FULL_NAME_FOR_USER_FRIENDS, userId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Integer id = resultSet.getInt(ID_COLUMN_LABEL);
                String firstName = resultSet.getString(FIRST_NAME_COLUMN_LABEL);
                String lastName = resultSet.getString(LAST_NAME_COLUMN_LABEL);
                String fullName = firstName + " " + lastName;

                userFriends.put(id, fullName);
            }
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
            try(PreparedStatement preparedStatement2 = preparedStatementForQuery(SELECT_USER_ID_AND_FULL_NAME_FOR_USER_FRIENDS_APP, userId)) {


                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {

                    Integer id = resultSet2.getInt(ID_COLUMN_LABEL);
                    String firstName = resultSet2.getString(FIRST_NAME_COLUMN_LABEL);
                    String lastName = resultSet2.getString(LAST_NAME_COLUMN_LABEL);
                    String fullName = firstName + " " + lastName;

                    userFriends.put(id, fullName);
                }
                return userFriends;
            }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method select user id and full name for all users in group.
     *
     * @param groupId The group id.
     * @return Map of user id and full name for member group.
     */
    @Override
    public Map<Integer, String> selectUserIdAndFullNameForUsersInGroup(int groupId) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_ID_AND_FULL_NAME_FOR_USERS_IN_GROUP, groupId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer,String> usersInGroup = new HashMap<>();

            while (resultSet.next()){

                Integer id = resultSet.getInt(ID_COLUMN_LABEL);
                String firstName = resultSet.getString(FIRST_NAME_COLUMN_LABEL);
                String lastName = resultSet.getString(LAST_NAME_COLUMN_LABEL);
                String fullName = firstName + " " + lastName;

                usersInGroup.put(id, fullName);
            }

            return usersInGroup;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     *This method select user id and full name for all friends Who sent messages to a specific user.
     *
     * @param userId The user id.
     * @return Map of user id and full name Who sent messages to a specific user
     */
    @Override
    public Map<Integer, String> selectUserIdAndFullNameForFriendsSendMessageForUser(int userId) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_ID_AND_FULL_NAME_FOR_FRIENDS_SEND_MESSAGE_FOR_USER, userId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer,String> friendsMessage = new HashMap<>();

            while (resultSet.next()){

                Integer id = resultSet.getInt(ID_COLUMN_LABEL);
                String firstName = resultSet.getString(FIRST_NAME_COLUMN_LABEL);
                String lastName = resultSet.getString(LAST_NAME_COLUMN_LABEL);
                String notificationStatus = resultSet.getString(NOTIFICATION_STATUS_COLUMN_LABEL);
                String fullName = firstName + " " + lastName + "," + notificationStatus;
                System.out.println(notificationStatus);
                System.out.println(fullName);

                friendsMessage.put(id, fullName);
            }

            return friendsMessage;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }

    }

    /**
     *This method select user id and full name for all users Who send friendship request to a specific user.
     *
     * @param userId The user id.
     * @return Map of user id and full name Who Who send friendship request to a specific user
     */
    @Override
    public Map<Integer, String> selectUserIdAndFullNameForUsersSendFriendshipRequestsForUser(int userId) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(SELECT_USER_ID_AND_FULL_NAME_FOR_USERS_SEND_FRIENDSHIP_REQUESTS_FOR_USER, userId)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer, String> usersSendFriendRequest = new HashMap<>();

            while (resultSet.next()){
                int userSenderRequestId = resultSet.getInt(ID_COLUMN_LABEL);
                String firstName = resultSet.getString(FIRST_NAME_COLUMN_LABEL);
                String lastName = resultSet.getString(LAST_NAME_COLUMN_LABEL);
                String fullName = firstName + " " + lastName;
                usersSendFriendRequest.put(userSenderRequestId, fullName);
            }

            return usersSendFriendRequest;
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
    public User buildEntity(ResultSet resultSet) throws DAOExceptions {

       User user = new User();

       try {
           int id = resultSet.getInt(ID_COLUMN_LABEL);
           user.setId(id);
           String firsName = resultSet.getString(FIRST_NAME_COLUMN_LABEL);
           user.setFirstName(firsName);

           String lastName = resultSet.getString(LAST_NAME_COLUMN_LABEL);
           user.setLastName(lastName);

           String userRoleValue = resultSet.getString(USER_ROLE_COLUMN_LABEL);
           UserRole userRole = UserRole.valueOf(userRoleValue);
           user.setUserRole(userRole);

           String email = resultSet.getString(EMAIL_COLUMN_LABEL);
           user.setEmail(email);

           String password = resultSet.getString(PASSWORD_COLUMN_LABEL);
           user.setPassword(password);

           String phoneNumber = resultSet.getString(PHONE_NUMBER_COLUMN_LABEL);
           user.setPhoneNumber(phoneNumber);
       }
       catch (SQLException exception){
           throw new DAOExceptions(exception.getMessage(), exception);
       }

       return user;

    }

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    @Override
    public List<String> getEntityParameter(User entity) {

        System.out.println("hi");
        List<String> parameter = new ArrayList<>();

        String firstName = entity.getFirstName();
        parameter.add(firstName);

        String lastName = entity.getLastName();
        parameter.add(lastName);

        UserRole userRole = entity.getUserRole();
        String userRoleValue = String.valueOf(userRole);
        parameter.add(userRoleValue);

        String email = entity.getEmail();
        parameter.add(email);

        String password = entity.getPassword();
        parameter.add(password);

        String phoneNumber = entity.getPhoneNumber();
        parameter.add(phoneNumber);

        return parameter;

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
