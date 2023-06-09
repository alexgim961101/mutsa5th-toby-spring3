package com.example.mutsa5thtobyspring3.dao;

import com.example.mutsa5thtobyspring3.domain.User;

import java.sql.*;

public abstract class UserDao {

    // public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDao() {
        simpleConnectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into User(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("select * from User where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new NUserDao();

        User user = new User();
        user.setId("2");
        user.setName("alex");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
