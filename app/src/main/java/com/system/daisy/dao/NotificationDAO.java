package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.Notifications;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAO extends DatabaseManager {
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Notifications> getListNotifications() {
        ArrayList<Notifications> list = new ArrayList<>();
        try {
            String query = "select * from notifications order by time desc";
            connection = connect();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notifications notification = new Notifications();
                notification.setId(rs.getInt(1));
                notification.setEmail(rs.getString(2));
                notification.setTime(rs.getTimestamp(3));
                notification.setTitle(rs.getString(4));
                notification.setContent(rs.getString(5));
                list.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
