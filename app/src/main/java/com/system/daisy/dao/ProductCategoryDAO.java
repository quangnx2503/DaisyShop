package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.FavoriteProduct;
import com.system.daisy.entity.ProductList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductCategoryDAO extends DatabaseManager {
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<FavoriteProduct> getListProductFavorite(String email) {
        ArrayList<FavoriteProduct> list = new ArrayList<>();
        try {
            String query = "SELECT p.id,pf.email,p.name,pf.[time],p.price,p.Avatar,p.sale\n" +
                    "                    FROM products p INNER JOIN\n" +
                    "                    favorites pf ON p.id = pf.productid \n" +
                    "                    where email=?\n" +
                    "                    order by ROW_NUMBER() over (partition by p.id order by p.id)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1,email);
            rs = ps.executeQuery();
            while (rs.next()) {
                FavoriteProduct fp = new FavoriteProduct();
                fp.setId(rs.getInt(1));
                fp.setEmail(rs.getString(2));
                fp.setName(rs.getString(3));
                fp.setTime(rs.getDate(4));
                fp.setPrice(rs.getInt(5));
                fp.setImageURL(rs.getString(6));
                fp.setSale(rs.getInt(7));
                list.add(fp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<ProductList> getListProductBySubCategory(int subcategoryId) {
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            String query = "select top 1 with ties p.id, p.name, p.price, pp.imageurl, p.sale\n" +
                    "  from products p \n" +
                    "  inner join product_pictures pp on p.id = pp.productid\n" +
                    "  inner join product_subcategory ps on p.id = ps.productid\n" +
                    "  where ps.subcategoryid = ?\n" +
                    "  order by ROW_NUMBER() over (partition by p.id order by p.id)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1,subcategoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductList p = new ProductList();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setImageUrl(rs.getString(4));
                p.setSale(rs.getInt(5));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
