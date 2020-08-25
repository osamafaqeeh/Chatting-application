package com.osama.chatting.entities.FriendShip;

import com.osama.chatting.entities.Entity;

import java.sql.Date;
import java.util.Objects;

/**
 * This class describes friendship for application.
 *
 * @author Osama Alfaqeeh.
 * @see Entity
 */
public class FriendShip extends Entity {

    private int firstUserId;
    private int secondUserID;
    private Date friendShipDate;

    /**
     * Instantiates a new friendship.
     */
    public FriendShip() {
    }

    /**
     * Get's first users Id.
     *
     * @return first users Id.
     */
    public int getFirstUserId() {
        return firstUserId;
    }

    /**
     * Set's the first user Id.
     *
     * @param firstUserId the first user Id.
     */
    public void setFirstUserId(int firstUserId) {
        this.firstUserId = firstUserId;
    }

    /**
     * Get's second Users id.
     *
     * @return second users id.
     */
    public int getSecondUserID() {
        return secondUserID;
    }

    /**
     * Set's the second users id.
     * @param secondUserID the second user id.
     */
    public void setSecondUserID(int secondUserID) {
        this.secondUserID = secondUserID;
    }

    /**
     * Get's the friendships date.
     *
     * @return friendships date.
     */
    public Date getFriendShipDate() {
        return friendShipDate;
    }

    /**
     * Set's the friendships date.
     *
     * @param friendShipDate the friendship date.
     */
    public void setFriendShipDate(Date friendShipDate) {
        this.friendShipDate = friendShipDate;
    }

    /**
     * this method equals two objects.
     *
     * @param object
     * @return true if  object's are equal and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FriendShip that = (FriendShip) object;
        return firstUserId == that.firstUserId &&
                secondUserID == that.secondUserID &&
                Objects.equals(friendShipDate, that.friendShipDate);
    }

     /**
     * this method calculate the object's hashcode.
     *
     * @return object hashcode.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstUserId);
        result = 31 * result + (secondUserID);
        result = 31 * result + (friendShipDate != null ? friendShipDate.hashCode() : 0);

        return result;
    }

    /**
     * This method build the information about object's.
     *
     * @return String information about objects.
     */
    @Override
    public String toString() {
        return "FriendShip{" +
                "firstUserId=" + firstUserId +
                ", secondUserID=" + secondUserID +
                ", friendShipDate=" + friendShipDate +
                '}';
    }
}
