package com.osama.chatting.entities.user;

import com.osama.chatting.entities.Entity;

import java.util.Objects;

/**
 * This class describes user of application.
 *
 * @author Osama ALfaqeeh.
 * @see UserRole
 * @see Entity
 */

public class User extends Entity {

    private String firstName;
    private String lastName;
    private UserRole userRole;
    private String email;
    private String password;
    private String phoneNumber;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets user's first name.
     *
     *
     * @return the user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets user's first name.
     *
     * @param firstName the user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get's user's last name.
     *
     * @return the user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set's users last name.
     *
     * @param lastName the user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *Get's the user's role.
     *
     * @return the user's Role.
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * Set's the user's role
     *
     * @param userRole the user's role.
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Get's the user's Email.
     *
     * @return the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set's the user's email.
     *
     * @param email the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get's the user's password.
     *
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     *Set's the use's password.
     *
     * @param password the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get's the user's phone number.
     *
     * @return the user's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set's the user's phone number.
     *
     * @param phoneNumber the user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * this method equals two objects.
     *
     * @param object the object.
     * @return true if objects are equals and false otherwise.
     */
    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }

        if (object == null || this.getClass() != object.getClass()){
            return false;
        }

        if (!super.equals(object)){
            return false;
        }

        User user = (User)object;

        if (!Objects.equals(firstName, user.firstName)){
            return false;
        }
        if (!Objects.equals(lastName, user.lastName)){
            return false;
        }
        if (!Objects.equals(email, user.email)){
            return false;
        }
        if (!Objects.equals(password, user.password)){
            return false;
        }
        if (!Objects.equals(userRole, user.userRole)){
            return false;
        }
        if (!Objects.equals(phoneNumber, user.password)){
            return false;
        }

        return true;

    }

    /**
     * this method calculate object's hashcode.
     *
     * @return object's hashcode.
     */
    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    /**
     * this method build information about object's.
     *
     * @return String information about object.
     */
    @Override
    public String toString() {
        return  "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
