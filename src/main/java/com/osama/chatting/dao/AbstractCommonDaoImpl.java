package com.osama.chatting.dao;

import com.osama.chatting.entities.Entity;
import com.osama.chatting.exceptions.DAOExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract root class of DAO level that provide access to the database and deal with application entities.
 *
 * @param <T> the entity type.
 * @author Eugene Makarenko
 * @see Connection
 * @see Entity
 */
public abstract class AbstractCommonDaoImpl<T extends Entity> implements CommonDAO<T> {

    public static final int EMPTY_RESULT = 0;
    public static final String NULL_PARAMETER = "null";

    public static final String SELECT_BY_ID_QUERY_KEY = "SELECT_BY_ID";
    public static final String DELETE_BY_ID_QUERY_KEY = "DELETE_BY_ID";
    public static final String SELECT_ALL_QUERY_KEY = "SELECT_ALL";
    public static final String UPDATE_ENTITY_QUERY_KEY = "UPDATE_ENTITY";
    public static final String INSERT_ENTITY_QUERY_KEY = "INSERT_ENTITY";

    private final Map<String,String> commonQuery;
    protected Connection connection;

    /**
     * Instantiates a new AbstractDAOImpl.
     *
     * @param connection the connection to database.
     */
    public AbstractCommonDaoImpl(Connection connection) {
        this.connection = connection;
        commonQuery = initializeCommonQuery();
    }

    /**
     * This method find entity from database by id.
     *
     * @param id entity Id.
     * @return Entity
     * @throws DAOExceptions  DAOException object if execution of query is failed.
     */
    @Override
    public T selectEntityById(int id) throws DAOExceptions {
       String sqlQuery = commonQuery.get(SELECT_BY_ID_QUERY_KEY);
       try (PreparedStatement preparedStatement = preparedStatementForQuery(sqlQuery, id)){
           ResultSet resultSet = preparedStatement.executeQuery();

           T entity = null;
           entity = buildEntity(resultSet);

           return entity;

       }
       catch (SQLException exception){
           throw new DAOExceptions(exception.getMessage(),exception);
       }

    }

    /**
     * this find all object's from database.
     *
     * @return List of entity.
     * @throws DAOExceptions DAOException object if execution of query is failed.
     */
    @Override
    public List<T> selectAll() throws DAOExceptions {
        String sqlQuery = commonQuery.get(SELECT_ALL_QUERY_KEY);
       try (Statement statement = connection.createStatement()){

           ResultSet resultSet = statement.executeQuery(sqlQuery);

           List<T>entityList = new ArrayList<>();

           while (resultSet.next()){
               T entity = null;
               entity = buildEntity(resultSet);
               entityList.add(entity);
           }
           return entityList;
       }
       catch (SQLException exception){
           throw  new DAOExceptions(exception.getMessage(), exception);
       }
    }

    /**
     * This method delete entity from database by id.
     *
     * @param id The entity Id.
     * @return true if the entity deleted successfully and false otherwise.
     * @throws DAOExceptions  DAOException object if execution of query is failed.
     */
    @Override
    public boolean deleteById(int id) throws DAOExceptions {
        String sqlQuery = commonQuery.get(DELETE_BY_ID_QUERY_KEY);

        return executeUpdate(sqlQuery, id);

    }

    /**
     * This method update entity from database.
     *
     * @param entity The Entity.
     * @return true if the entity updated successfully from database.
     * @throws DAOExceptions
     */
    @Override
    public boolean update(T entity) throws DAOExceptions {
        String sqlQuery = commonQuery.get(UPDATE_ENTITY_QUERY_KEY);
        List<String> parameterList = getEntityParameter(entity);

        int id = entity.getId();
        String idValue = String.valueOf(id);
        parameterList.add(idValue);

       return executeUpdate(sqlQuery, parameterList);
    }

    /**
     * This method insert entity in database.
     *
     * @param entity The entity.
     * @return true if entity inserted successfully.
     * @throws DAOExceptions  DAOException object if execution of query is failed.
     */
    @Override
    public boolean insertEntities(T entity) throws DAOExceptions {
        String sqlQuery = commonQuery.get(INSERT_ENTITY_QUERY_KEY);

        List<String> parameterList = getEntityParameter(entity);

        return executeUpdate(sqlQuery, parameterList);
    }

    /**
     *This method execute update
     *
     * @param sqlQuery the sql Query.
     * @param parameters the parameters.
     * @return true if resultSet is expected and false otherwise.
     * @throws DAOExceptions  DAOException object if execution of query is failed.
     */
    private boolean executeUpdate(String sqlQuery, Object... parameters) throws DAOExceptions {

        try(PreparedStatement preparedStatement = preparedStatementForQuery(sqlQuery, parameters)){

            int queryResult = preparedStatement.executeUpdate();

            return queryResult != EMPTY_RESULT;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method execute update.
     *
     * @param sqlQuery The sql query.
     * @param parameterList The parameters.
     * @return true if the resultSet is excepted and false otherwise.
     * @throws DAOExceptions DAOException object if execution of query is failed.
     */
    private boolean executeUpdate(String sqlQuery, List<String> parameterList) throws DAOExceptions {
        try(PreparedStatement preparedStatement = preparedStatementForQuery(sqlQuery, parameterList)){

            int queryResult = preparedStatement.executeUpdate();

            return queryResult != EMPTY_RESULT;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method initialise the preparedStatement object's and sets it's parameter.
     *
     * @param sqlQuery the sql query.
     * @param parameters the parameters.
     * @return preparedStatement.
     * @throws SQLException DAOException object if execution of query is failed.
     */
    public PreparedStatement preparedStatementForQuery(String sqlQuery, Object... parameters) throws DAOExceptions {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            if (parameters != null) {
                int parameterIndex = 1;
                for (Object parameter : parameters) {
                    if (parameter == null) {
                        preparedStatement.setNull(parameterIndex, Types.NULL);
                    } else {
                        preparedStatement.setObject(parameterIndex, parameter);
                    }
                    parameterIndex++;
                }
            }
            return preparedStatement;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method initialise the preparedStatement object's and sets it's parameter.
     *
     * @param sqlQuery the sql query.
     * @param parameters the parameters.
     * @return preparedStatement.
     * @throws SQLException DAOException object if execution of query is failed.
     */
    public PreparedStatement preparedStatementForQuery(String sqlQuery, List<String>parameters) throws DAOExceptions {
        try{
        PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);

            if (parameters != null){
               int parameterIndex = 1;
               for (String parameter : parameters){
                   if (NULL_PARAMETER.equals(parameter)){
                       preparedStatement.setNull(parameterIndex, Types.NULL);
                   }
                   else {
                       preparedStatement.setString(parameterIndex, parameter);
                   }
                   parameterIndex++;
               }
            }
           return preparedStatement;
        }
        catch (SQLException exception){
            throw new DAOExceptions(exception.getMessage(), exception);
        }
    }

    /**
     * This method build entity from resultSet object.
     *
     * @param resultSet the resultSet from preparedStatement.
     * @return the entity.
     */
    public abstract T buildEntity(ResultSet resultSet) throws DAOExceptions;

    /**
     * This method get entity parameter from entity object.
     *
     * @param entity The entity.
     * @return List of parameter.
     */
    public abstract List<String> getEntityParameter(T entity);

    /**
     * This method build common Query
     *
     * @return Map of common query key and common query.
     */
    public abstract Map<String,String> initializeCommonQuery();
}
