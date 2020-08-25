package com.osama.chatting.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ConnectionCreator {
    private static final Logger LOGGER = Logger.getLogger(ConnectionCreator.class.getName());

    private static final String RESOURCE_BUNDLE_FILE_NAME = "database";
    private static final String POOL_SIZE_PROPERTY_KEY = "db.poolSize";
    private static final String USER_PROPERTY_KEY = "db.user";
    private static final String PASSWORD_PROPERTY_KEY = "db.password";
    private static final String URL_PROPERTY_KEY = "db.url";
    private static final String USER_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";

    private static final ResourceBundle resourceBundle =ResourceBundle.getBundle(RESOURCE_BUNDLE_FILE_NAME);

    public LinkedList<Connection> createPool() {

        LinkedList<Connection> pool = new LinkedList<>();
        String poolSizeValue = resourceBundle.getString(POOL_SIZE_PROPERTY_KEY);
        int currentPoolSize = Integer.parseInt(poolSizeValue);

        for (int listIndex  = 0 ; listIndex  < currentPoolSize; listIndex ++) {
            Connection connection = create();
            pool.addLast(connection);
        }
        LOGGER.info("pool was created successful.");
        return pool;
    }

    public Connection create(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            LOGGER.info("Driver was registered successful.");
        }
        catch (SQLException exception){
            LOGGER.warning("SQL exception was detected during driver registration.");
            throw  new ExceptionInInitializerError("Driver hasn't been registered." + exception.getMessage());
        }
        String urlValue = resourceBundle.getString(URL_PROPERTY_KEY);

        Properties properties = new Properties();
        String userValue = resourceBundle.getString(USER_PROPERTY_KEY);
        String passwordValue = resourceBundle.getString(PASSWORD_PROPERTY_KEY);
        properties.put(USER_PROPERTY,userValue);
        properties.put(PASSWORD_PROPERTY,passwordValue);


        try {
           Connection connection = DriverManager.getConnection(urlValue, properties);
           LOGGER.info("connection was created successful.");
           return connection;
        }
        catch (SQLException exception){
            throw new ExceptionInInitializerError("Connection hasn't been created. " + exception.getMessage());
        }
    }
}
