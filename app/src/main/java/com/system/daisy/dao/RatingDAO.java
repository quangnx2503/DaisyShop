package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.Productrating;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RatingDAO extends DatabaseManager {

    public float rateProduct(int productId){
        float ratingStars = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT AVG(stars) AS \"Stars\"\n" +
                    "FROM [dbo].[productrating] where productid = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()){
                ratingStars = rs.getFloat("Stars");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ratingStars;
    }

    public void favouriteProduct(int productId, String email){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "insert into favorites values (?,GETDATE(),?,?)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(3, productId);
            ps.setString(1, email);

            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
