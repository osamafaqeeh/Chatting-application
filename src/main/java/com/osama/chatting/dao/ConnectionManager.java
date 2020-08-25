package com.osama.chatting.dao;

import com.osama.chatting.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection ;
import java.sql.SQLException;


/**
 * class with dao level to work with connection.
 *
 * @author Osama Alfaqeeh.
 * @see Connection
 * @see ConnectionPool
 */
public class ConnectionManager implements AutoCloseable {
    private ConnectionPool connectionPool;
    private final Connection connection;
    private final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

    /**
     * Instantiates a new ConnectionManager.
     */
    public ConnectionManager() {
        this.connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.getConnection();
    }

    /**
     * The method starts transaction.
     */
    public void startTransaction(){
        try {
            connection.setAutoCommit(false);
        }
        catch (SQLException exception){
            LOGGER.error("start transaction is failed ", exception);
        }
    }

    /**
     * The method commit Transaction.
     */
    public void commitTransaction(){
        try {
            connection.commit();
        }
        catch (SQLException exception){
            LOGGER.warn("commit Transaction is failed", exception);
        }
    }

    /**
     * the method rollback transaction.
     */
    public void rollBackTransaction(){
        try {
            connection.rollback();
        }
        catch (SQLException exception){
            LOGGER.warn("Rollback transaction is failed", exception);
        }
    }

    /**
     * The method end Transaction.
     */
    public void endTransaction(){
        try {
            connection.setAutoCommit(true);
        }
        catch (SQLException exception){
            LOGGER.warn("end transaction is failed " , exception);
        }
    }

    /**
     * The method get connection.
     * @return the connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Implementation of AutoCloseable interface to work with try().
     */
    @Override
    public void close(){
        connectionPool.returnConnection(connection);
    }

}
