package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DatabaseManager {


    public User checkLogin(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT [email]\n" +
                        "      ,[pass_word]\n" +
                        "      ,[name]\n" +
                        "      ,[phone]\n" +
                        "      ,[dob]\n" +
                        "  FROM   [dbo].[users] where email = ? and pass_word = ?";
                connection = connect();
                ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setEmail(rs.getString(1));
                    user.setPass_word(rs.getString(2));
                    user.setName(rs.getString(3));
                    user.setPhone(rs.getString(4));
                    user.setDob(rs.getString(5));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User checkUserForget(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT[email]\n" +
                    "      ,[pass_word]\n" +
                    "      ,[name]\n" +
                    "      ,[phone]\n" +
                    "      ,[dob]\n" +
                    "  FROM   [dbo].[users] where email = ? OR phone = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString(1));
                user.setPass_word(rs.getString(2));
                user.setName(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setDob(rs.getString(5));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updatePassword(String username, String password) {
        PreparedStatement ps = null;

        try {
            String query = "UPDATE   [dbo].[users] SET pass_word = ? WHERE email = ? ";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User getInfo(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT name,email from [users] where email=?";
            connection = connect();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExist(String email) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            String query = "SELECT email\n"
                    + "  FROM users WHERE email = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return flag;
    }

    public  boolean insert(User user) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            String query = "INSERT INTO users (email, pass_word, name, gender, phone, dob) VALUES (?, ?, ?, ?, ?, ?)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPass_word());
            ps.setString(3, user.getName());
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getDob());
            int retVal = ps.executeUpdate();
            if (retVal > 0) {
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
