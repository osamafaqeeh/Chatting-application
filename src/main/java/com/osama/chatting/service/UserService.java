package com.osama.chatting.service;

import com.osama.chatting.dao.AbstractCommonDaoImpl;
import com.osama.chatting.dao.NotificationDao;
import com.osama.chatting.dao.UserDao;
import com.osama.chatting.dao.impl.NotificationDAOImpl;
import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.notifications.NotificationStatus;
import com.osama.chatting.entities.notifications.NotificationType;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.dao.impl.UserDAOImpl;
import com.osama.chatting.dao.ConnectionManager;
import com.osama.chatting.entities.user.UserRole;
import com.osama.chatting.exceptions.DAOExceptions;
import com.osama.chatting.exceptions.ServiceExceptions;
import com.osama.chatting.utils.PasswordEncoder;
import com.osama.chatting.utils.UserDateValidator;


import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for user entity.
 *
 * @author Osama ALfaqeeh.
 * @see User
 * @see UserDAOImpl
 * @see ConnectionManager
 * @see ServiceExceptions
 */
public class UserService {

    private static final String NAME_SPLIT_SYMBOL = " ";
    private static final int FIRST_NAME_INDEX = 0;
    private static final int LAST_NAME_INDEX = 1;
    /**
     * The method return authorized user.
     *
     * @param email The user's email.
     * @param password the user's password.
     * @return The User.
     * @throws ServiceExceptions
     */
    public User login(String email, String password) throws ServiceExceptions {
        try(ConnectionManager connectionManager = new ConnectionManager()) {
            Connection connection = connectionManager.getConnection();
            UserDao UserDaoImpl = new UserDAOImpl(connection);

            String passwordEncoded = PasswordEncoder.encode(password);

            return UserDaoImpl.selectUserByEmailAndPassword(email, passwordEncoded);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during login operation.", exception);
        }
    }

    /**
     * The method registers user into data base.
     *
     * @param firstName the user's first name.
     * @param lastName the user's last name.
     * @param email    the user's email.
     * @param password the user's password.
     * @param phoneNumber the user's phone number.
     * @return true if operation was made successful and false otherwise.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public boolean register (String firstName, String lastName, String email, String password, String phoneNumber) throws ServiceExceptions {
        try(ConnectionManager connectionManager = new ConnectionManager()) {
            Connection connection = connectionManager.getConnection();
            AbstractCommonDaoImpl<User> userDao = new UserDAOImpl(connection);
            System.out.println(firstName);
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserRole(UserRole.USER);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            System.out.println(user.toString());

            return userDao.insertEntities(user);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during register operation.", exception);
        }
    }

    /**
     * The method checks user email for unique value during registration.
     * @param email the user email.
     * @return true if email for unique value otherwise false.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public boolean checkUserEmailForUnique(String email) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());

            return userDao.checkEmailForUnique(email);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during check User Email For Unique operation.", exception);
        }
    }

    /**
     * The method find user from database by first name and last name.
     *
     * @param name the user's  name.
     * @return List of users.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public List<User> findUserByFullName(String name) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());
            UserDateValidator userDateValidator = new UserDateValidator();

            boolean isFullName = userDateValidator.isFullName(name);
            System.out.println(isFullName);
            if (isFullName){
                String[] names = name.split(NAME_SPLIT_SYMBOL);
                String firstName = names[FIRST_NAME_INDEX];
                String lastName = names[LAST_NAME_INDEX];
                System.out.println(firstName);
                System.out.println(lastName);

                return userDao.selectUserByFullName(firstName, lastName);
            }
            else{
                return userDao.selectUserByPartName(name);
            }
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * The method Find friends of users from database by user id.
     *
     * @param userId the user id.
     * @return Map of friends name and user id.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public Map<Integer,String> findUserFriends(int userId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());

            return userDao.selectUserIdAndFullNameForUserFriends(userId);

        }
        catch (DAOExceptions exception){
            System.out.println(exception);
            throw new ServiceExceptions("Exception during find user friends operation.", exception);
        }
    }

    /**
     * the method Find the users who joined the group from database by group Id.
     *
     * @param groupId The group id0
     * @return Map of user's id and user's name.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public Map<Integer,String> findUsersJoinedGroup(int groupId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());

            return userDao.selectUserIdAndFullNameForUsersInGroup(groupId);

        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during find users joined group operation.", exception);
        }
    }

    /**
     * The method Find friends who have sent a message to a user from database by user id.
     *
     * @param userId the user id.
     * @return Map of user id and user name.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public Map<Integer,String> findFriendsSendMessageToUser(int userId) throws ServiceExceptions {
        try (ConnectionManager connectionManager = new ConnectionManager()){
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());

            return userDao.selectUserIdAndFullNameForFriendsSendMessageForUser(userId);

        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during find Friend Send Message To User operation.", exception);
        }
    }
    /**
     * The method Find friends who have send a friendship requests to a user from database by user id.
     *
     * @param userId the user id.
     * @return Map of user id and user name.
     * @throws ServiceExceptions ServiceException object if execution of method is failed.
     */
    public Map<Integer,String> findUsersSendFriendshipRequestsToUser(int userId) throws ServiceExceptions {
        try(ConnectionManager connectionManager = new ConnectionManager()) {
            UserDao userDao = new UserDAOImpl(connectionManager.getConnection());
             return userDao.selectUserIdAndFullNameForUsersSendFriendshipRequestsForUser(userId);
        }
        catch (DAOExceptions exception){
            throw new ServiceExceptions("Exception during find Friend Send Message To User operation.", exception);
        }

    }
}
