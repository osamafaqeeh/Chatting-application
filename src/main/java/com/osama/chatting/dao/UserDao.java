package com.osama.chatting.dao;

import com.osama.chatting.entities.notifications.Notification;
import com.osama.chatting.entities.user.User;
import com.osama.chatting.exceptions.DAOExceptions;

import java.util.List;
import java.util.Map;


/**
 *  User DAO interface has method to work with User.
 */
public interface UserDao {

    /**
     * This method select user from database by email and password.
     *
     * @param email The user email.
     * @param password The user password.
     * @return User The user.
     * @throws DAOExceptions object if execution of query is failed.
     */
    User selectUserByEmailAndPassword(String email, String password) throws DAOExceptions;

    /**
     *This method check the email is unique.
     *
     * @return true if email is unique and false otherwise.
     * @throws DAOExceptions object if execution of query is failed.
     */
    boolean checkEmailForUnique(String email) throws DAOExceptions;

    /**
     * This method select user from database by first name and last name;
     *
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @throws DAOExceptions object if execution of query is failed.
     * @return list of users.
     */
    List<User> selectUserByFullName(String firstName, String lastName)throws DAOExceptions;

    /**
     * This method select user from database by part of name.
     *
     * @param name The user's part name.
     * @return List of User .
     * @throws DAOExceptions object if execution of query is failed.
     */
    List<User> selectUserByPartName(String name)throws DAOExceptions;

    /**
     * This method select all user id and full name for user friends.
     *
     * @param userId the user id.
     * @return Map of user id and user full name for user friends.
     */
    Map<Integer, String> selectUserIdAndFullNameForUserFriends(int userId) throws DAOExceptions;

    /**
     * This method select user id and full name for all users in group.
     *
     * @param groupId The group id.
     * @return Map of user id and full name for member group.
     */
    Map<Integer, String> selectUserIdAndFullNameForUsersInGroup(int groupId) throws DAOExceptions;

    /**
     *This method select user id and full name for all friends Who send messages to a specific user.
     *
     * @param userId The user id.
     * @return Map of user id and full name Who send messages to a specific user
     */
    Map<Integer, String> selectUserIdAndFullNameForFriendsSendMessageForUser(int userId) throws DAOExceptions;

    /**
     *This method select user id and full name for all users Who send friendship request to a specific user.
     *
     * @param userId The user id.
     * @return Map of user id and full name Who Who send friendship request to a specific user
     */
    Map<Integer, String> selectUserIdAndFullNameForUsersSendFriendshipRequestsForUser(int userId) throws DAOExceptions;

}
