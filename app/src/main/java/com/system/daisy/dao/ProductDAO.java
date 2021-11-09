package com.system.daisy.dao;

import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DatabaseManager {

    PreparedStatement statement;
    ResultSet rs;

    public List<String> getImageUrls(int productid) {
        List<String> urls = new ArrayList<>();

        try {
            String sql = "select * from product_pictures where productid = ? ";
            connection = connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productid);
            rs = statement.executeQuery();
            while (rs.next()) {
                String url = rs.getString("imageurl");
                urls.add(url);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return urls;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try {
            String sql = "select * from products order by name";
            connection = connect();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSale(rs.getFloat("sale"));
                p.setPrice(rs.getInt("price"));
                p.setAvatar(rs.getString("avatar"));
                productList.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    public List<Product> getTopProducts(String type) {
        List<Product> productList = new ArrayList<>();

        try {
            String sql = "";
            switch (type) {
                case "sale":
                    sql = "select top (10) * from products \n" +
                            "order by salepriority DESC,sale DESC";
                    break;
                case "newest":
                    sql = "select top (10) * from products\n" +
                            "order by createtime DESC";
                    break;
                case "sell":
                    sql = "select top (10) * from products inner join\n" +
                            "(select productid, SUM(quantity) as totalquantity\n" +
                            "from (SELECT * FROM orders INNER JOIN\n" +
                            "                  order_product ON orders.id = order_product.orderid\n" +
                            " where DATEDIFF(day,ordertime,GETDATE()) < 31) as X\n" +
                            " group by productid ) as Y ON products.id = Y.productid\n" +
                            " order by totalquantity DESC";
                    break;
            }

            connection = connect();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setSale(rs.getFloat("sale"));
                p.setPrice(rs.getInt("price"));
                p.setAvatar(rs.getString("avatar"));
                productList.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    public Product getProductDetail(int productId) {
        Product p = new Product();
        try {
            String sql = "select * from products where id = ?";
            connection = connect();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            rs = statement.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getInt("status"));
                p.setSale(rs.getFloat("sale"));
                p.setSalepriority(rs.getInt("salepriority"));
                p.setPrice(rs.getInt("price"));
                p.setClicktimes(rs.getInt("clicktimes"));
                p.setCreatetime(rs.getDate("createtime"));
                p.setProducer(rs.getString("producer"));
                p.setOrigin(rs.getString("origin"));
                p.setGuarantee(rs.getString("guarantee"));
                p.setSpecifictions(rs.getString("specifications"));
                p.setAvatar(rs.getString("avatar"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select * from products where name like ? ";
            connection = connect();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAvatar(rs.getString("avatar"));
                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


    public List<String> getAllProductsName() {
        List<String> nameList = new ArrayList<>();

        try {
            String sql = "select * from products order by name";
            connection = connect();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                nameList.add(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nameList;
    }




}
