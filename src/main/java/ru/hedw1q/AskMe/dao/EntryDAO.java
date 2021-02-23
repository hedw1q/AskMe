package ru.hedw1q.AskMe.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hedw1q
 */
@Component
public class EntryDAO {
    private static final String URL="jdbc:postgresql://localhost:5432/AskMe_DB";
    private static final String LOGIN="postgres";
    private static final String PASSWORD="admin" ;
    private static Connection connection;


//    static {
//        try {
//            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


}
