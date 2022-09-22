package com.niit.jdp.repository;

import com.niit.jdp.exception.InsertErrorException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    /**
     * This function returns a list of all the objects in the database.
     *
     * @param connection The connection to the database.
     * @return A list of objects of type T.
     */
    List<T> displayAll(Connection connection);

    /**
     * This function adds a new object to the database
     *
     * @param connection The connection to the database.
     * @param t          The object to be added to the database.
     * @return A boolean value.
     */
    boolean add(Connection connection, T t) throws InsertErrorException, SQLException;


    /**
     * Delete the row with the given id from the table.
     *
     * @param connection The connection to the database.
     * @param id         The id of the record to delete.
     * @return A boolean value.
     */
    boolean delete(Connection connection, int id) throws SQLException;

    /**
     * Sort a list of objects by alphabetical order.
     *
     * @param tList The list of objects to be sorted.
     * @return A list of objects that are sorted by alphabetical order.
     */
    List<T> sortByAlphabet(List<T> tList);
}
