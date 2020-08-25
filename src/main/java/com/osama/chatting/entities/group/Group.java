package com.osama.chatting.entities.group;
import com.osama.chatting.entities.Entity;

import java.sql.Date;
import java.util.Objects;


/**
 * This class describes group for application.
 *
 * @author Osama Alfaqeeh
 * @see Entity
 */
public class Group extends Entity {

    private int adminId;
    private String groupName;
    private Date DateCreated;
    private String groupDescription;

    /**
     * Instantiates a new group.
     */
    public Group() {
    }

    /**
     * Get's Group's Admin Id ;
     *
     * @return The group Admin Id.
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Set's Group Admin's Id.
     *
     * @param adminId The group Admin's Id.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Get's the group name.
     *
     * @return the group name.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set's group name.
     *
     * @param groupName the group name.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Get's The date the group was created.
     *
     * @return date the group was created.
     */
    public Date getDateCreated() {
        return DateCreated;
    }

    /**
     * Set's The date the group was created.
     *
     * @param dateCreated The date the group was created.
     */
    public void setDateCreated(Date dateCreated) {
        DateCreated = dateCreated;
    }

    /**
     * Get's the description for the group.
     *
     * @return the description for the group.
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set's the description for the group.
     *
     * @param groupDescription the description for the group.
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * This method equals two objects.
     *
     * @param object the object.
     * @return true if objects are equals and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)){
            return false;
        }
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Group group = (Group) object;
        return adminId == group.adminId &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(DateCreated, group.DateCreated) &&
                Objects.equals(groupDescription, group.groupDescription);
    }

    /**
     * This method calculate the object's hashcode.
     *
     * @return the hashcode.
     */
    @Override
    public int hashCode() {
       int result = super.hashCode();
       result = 31 * result + adminId;
       result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (DateCreated != null ? DateCreated.hashCode() : 0);
        result = 31 * result + (groupDescription != null ? groupDescription.hashCode() : 0);

        return result;

    }

    /**
     * This method build information about object.
     *
     * @return String information about object's.
     */
    @Override
    public String toString() {
        return "Group{" +
                "adminId=" + adminId +
                ", groupName='" + groupName + '\'' +
                ", DateCreated=" + DateCreated +
                ", groupDescription='" + groupDescription + '\'' +
                '}';
    }
}
