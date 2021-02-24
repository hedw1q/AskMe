package ru.hedw1q.AskMe.dao;


import org.springframework.stereotype.Component;
import ru.hedw1q.AskMe.models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author hedw1q
 */
@Deprecated
public class UserDAO {
//    private static final String URL="jdbc:postgresql://localhost:5432/AskMe_DB";
//    private static final String LOGIN="postgres";
//    private static final String PASSWORD="admin" ;
//    private static Connection connection;
//
//    static {
//        try {
//            connection= DriverManager.getConnection(URL,LOGIN,PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public void create(User user){
//        String SQL="INSERT INTO public.\"User\"(name,surname,username,password) VALUES(?,?,?,?)";
//        try {
//            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getSurname());
//            preparedStatement.setString(3,user.getUsername());
//            preparedStatement.setString(4,user.getPassword());
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }





