package com.osama.chatting.entities.groupMumbers;

import com.osama.chatting.entities.Entity;

import java.sql.Date;

/**
 * This class describes group members for application.
 *
 * @author Osama Alfaqeeh.
 * @see Entity
 */
public class GroupMembers extends Entity {

    private int groupId;
    private Date joinDate;
    private int userId;

    /**
     * Instantiates a new message.
     */
    public GroupMembers() {
    }

    /**
     * This method get's group id.
     *
     * @return The group id.
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * This method Set's group id.
     * @param groupId
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * This method get's join date.
     *
     * @return The join Date.
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * This method set's join date.
     *
     * @param joinDate The join Date.
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * This method get's user id.
     *
     * @return The user's id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This method set's user id.
     * @param userId The user's id.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
