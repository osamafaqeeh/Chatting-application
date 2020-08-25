package com.osama.chatting.dao;

import com.osama.chatting.entities.Entity;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.SQLDataException;
import java.util.List;

/**
 * DAO interface has all common method to work with Entities.
 *
 * @param <T> The Entity
 */
public interface CommonDAO <T extends Entity> {

    /**
     * This method find entity from database by id.
     *
     * @param id entity Id.
     * @return The Entity.
     */
    T selectEntityById(int id) throws DAOExceptions;

    /**
     * This method select all entities from database.
     *
     * @return List of entities.
     */
    List<T> selectAll() throws DAOExceptions;

    /**
     * This method delete entity from database by id.
     *
     * @param id The entity Id.
     * @return true if the entity is deleted successfully and false otherwise.
     */
    boolean deleteById(int id) throws DAOExceptions;

    /**
     * This method update Entity in database.
     *
     * @param entity The Entity.
     * @return true if the Entity is updated successfully and false otherwise.
     */
    boolean update(T entity) throws DAOExceptions;

    /**
     * This method insert the entity in database.
     *
     * @param entity The entity.
     * @return true if The entity is inserted successfully and false otherwise.
     */
    boolean insertEntities(T entity) throws DAOExceptions;


}
