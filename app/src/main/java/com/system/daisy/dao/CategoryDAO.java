package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.Categories;
import com.system.daisy.entity.Subcategories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO extends DatabaseManager {
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Categories> getListCategories() {
        ArrayList<Categories> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM [categories]";
            connection = connect();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categories category = new Categories();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                category.setDescription(rs.getString(3));
                category.setStatus(rs.getBoolean(4));
                category.setCreateDate(rs.getDate(5));
                category.setImageURL(rs.getString(6));
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Subcategories> getListSubCategoryByCategoryID(int categoryId) {
        ArrayList<Subcategories> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM subcategories where categoriesid=?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Subcategories category = new Subcategories();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                category.setDescription(rs.getString(3));
                category.setStatus(rs.getBoolean(4));
                category.setCreateDate(rs.getDate(5));
                category.setCategoryId(rs.getInt(6));
                category.setImageURL(rs.getString(7));
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
