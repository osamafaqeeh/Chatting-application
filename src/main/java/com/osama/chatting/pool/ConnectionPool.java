package com.osama.chatting.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ConnectionPool {

    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private static Lock instanceLooker = new ReentrantLock();
    private static Lock poolLocker = new ReentrantLock();
    private static Condition poolCondition = poolLocker.newCondition();
    private static LinkedList<Connection> pool;
    private static AtomicBoolean isInstanceAvailable = new AtomicBoolean(true);
    private static ConnectionPool instance = null;

    private ConnectionPool() {
        ConnectionCreator connectionCreator = new ConnectionCreator();
        pool = connectionCreator.createPool();
    }
    public static ConnectionPool getInstance(){

        if (isInstanceAvailable.get()){
            instanceLooker.lock();
            try {
                boolean isAvailableNow = instance == null;
                if (isAvailableNow){
                instance = new ConnectionPool();
                    isInstanceAvailable.set(false);
                }
            }
            finally {
                instanceLooker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection()  {

        poolLocker.lock();
        Connection connection;
       try {
           if (pool.isEmpty()){
               poolCondition.await();
           }
           connection = pool.poll();
       }
       catch (InterruptedException exception){
           throw new IllegalStateException("can't get connection"+exception.getMessage());
       }
       finally {
           poolLocker.unlock();
       }
       return connection;
    }

    public void returnConnection(Connection connection){
        poolLocker.lock();
        try {
            pool.addLast(connection);
            poolCondition.signal();
        }
        finally {
            poolLocker.unlock();
        }
    }
    public void closePool(){
        for (Connection connection : pool) {
            try {
                connection.close();
            } catch (SQLException exception) {
                LOGGER.warning("Exception was detected during pool closing." + exception.getMessage());
            }
        }
    }

}
