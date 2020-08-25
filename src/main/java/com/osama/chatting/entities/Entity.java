package com.osama.chatting.entities;

import java.util.Objects;

/**
 * the type Entity.
 *
 * @author Osama Alfaqeeh.
 */

public abstract class Entity {

    private int id ;

    /**
     * Gets entity's id.
     *
     *
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     *
     *
     * @param id the entity's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     *
     *
     * @param object the object.
     * @return true if the objects are equals and false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object == null || this.getClass() != object.getClass()){
            return false;
        }

        Entity entity =(Entity)object;

        return  this.id == entity.id;
    }

    /**
     * this method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        return 31*this.id;
    }

    /**
     * Tis method builds information about objects.
     *
     *
     * @return String information about object.
     */
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
