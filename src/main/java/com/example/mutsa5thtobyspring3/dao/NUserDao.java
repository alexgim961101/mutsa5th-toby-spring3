package com.example.mutsa5thtobyspring3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao{

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/like_lion?serverTimezone=Asia/Seoul", "root", "Rlawlgus12#");
        return c;
    }
}
