package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.Advertisement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdDAO extends DatabaseManager {

    PreparedStatement statement;
    ResultSet rs;

    public List<Advertisement> getAdList() {
        List<Advertisement> adList = new ArrayList<>();
        try {
            String sql = "select top (5) * from advertisements " +
                         "order by priority DESC";
            connection = connect();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Advertisement ad = new Advertisement();
                ad.setId(rs.getInt("id"));
                ad.setProductId(rs.getInt("productid"));
                ad.setMediaUrl(rs.getString("mediaurl"));
                ad.setStatus(true);
                adList.add(ad);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return adList;
    }
}
