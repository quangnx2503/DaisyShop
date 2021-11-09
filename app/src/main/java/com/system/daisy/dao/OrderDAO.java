package com.system.daisy.dao;

import com.system.daisy.common.Common;
import com.system.daisy.databases.DatabaseManager;
import com.system.daisy.entity.CartItem;
import com.system.daisy.entity.Orders;
import com.system.daisy.entity.PaymentMethods;
import com.system.daisy.entity.Productrating;
import com.system.daisy.entity.Products;

import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAO extends DatabaseManager {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Common common = new Common();

    public ArrayList<Orders> getOrdersByEmail(String email) {
        ArrayList<Orders> orders = new ArrayList<>();
        try {
            String query = "select * from orders where email = ? order by ordertime desc";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt(1));
                order.setEmail(rs.getString("email"));
                order.setOrderTime(rs.getTimestamp(3));
                order.setShipTime(rs.getTimestamp(4));
                order.setDestination(rs.getString(5));
                order.setTotalPrice(rs.getInt(6));
                order.setStatus(rs.getInt(7) == 1 ? true : false);
                order.setPaymentmethod(rs.getInt(8));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void insertOrder(String email, Date orderTime, Date shipTime, String address, int totalPrice, int status, int paymentMethod, ArrayList<CartItem> items) {
        try {
            connection = connect();
            connection.setAutoCommit(false);
            String queryInsertOrder = "insert into orders values (?,?,?,?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(queryInsertOrder, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, email);
            ps1.setTimestamp(2, new java.sql.Timestamp(orderTime.getTime()));
            ps1.setTimestamp(3, new java.sql.Timestamp(shipTime.getTime()));
            ps1.setString(4, address);
            ps1.setInt(5, totalPrice);
            ps1.setInt(6, status);
            ps1.setInt(7, paymentMethod);
            ps1.executeUpdate();
            ResultSet key = ps1.getGeneratedKeys();
            int returnKey = 0;
            if (key.next()) {
                returnKey = key.getInt(1);
            }
            for (int i = 0; i < items.size(); i++) {
                String queryInsertOrderProduct = "insert into order_product values (?,?,?)";
                PreparedStatement ps2 = connection.prepareStatement(queryInsertOrderProduct);
                ps2.setInt(2, returnKey);
                ps2.setInt(1, items.get(i).getId());
                ps2.setInt(3, items.get(i).getQuantity());
                ps2.executeUpdate();
                ps2.close();
            }
            connection.commit();
            connection.close();
            ps1.close();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void rateProduct(Productrating productrating) {
        try {
            String query = "insert into productrating values (?,?,?,?)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(4, productrating.getProductid());
            ps.setString(1, productrating.getEmail());
            ps.setString(2, productrating.getRatingContent());
            ps.setFloat(3, productrating.getStars());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Orders getOrderById(int id) {
        ArrayList<Orders> orders = new ArrayList<>();
        try {
            String query = "select * from orders where id = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt(1));
                order.setEmail(rs.getString(2));
                order.setOrderTime(rs.getTimestamp(3));
                order.setShipTime(rs.getTimestamp(4));
                order.setDestination(rs.getString(5));
                order.setTotalPrice(rs.getInt(6));
                order.setStatus(rs.getInt(7) == 1 ? true : false);
                order.setPaymentmethod(rs.getInt(8));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders.get(0);
    }

    public ArrayList<Products> getProductsByOrder(int orderId) {
        ArrayList<Products> products = new ArrayList<>();
        try {
            String query = "select p.* from \n" +
                    "  products p \n" +
                    "  inner join order_product od on p.id = od.productid\n" +
                    "  inner join orders o on od.orderid = o.id\n" +
                    "  where o.id = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setDescription(rs.getString(3));
                product.setStatus(rs.getInt(4) == 1 ? true : false);
                product.setSale(rs.getFloat(5));
                product.setSalepriority(rs.getInt(6));
                product.setPrice(rs.getInt(7));
                product.setClickTimes(rs.getInt(8));
                product.setCreateTime(rs.getTimestamp(9));
                product.setProducer(rs.getString(10));
                product.setOrigin(rs.getString(11));
                product.setGuarantee(rs.getString(12));
                product.setSpecifications(rs.getString(13));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public String getNameOfListProducts(@NotNull ArrayList<Products> products) {
        ArrayList<String> names = new ArrayList<>();
        for (Products product : products) {
            names.add(product.getName());
        }
        String nameOfList = String.join(" + ", names);
        return nameOfList;
    }

    public PaymentMethods getPaymentMethodById(int id) {
        ArrayList<PaymentMethods> paymentMethods = new ArrayList<>();
        try {
            String query = "select * from paymentmethods where id = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                PaymentMethods pm = new PaymentMethods();
                pm.setId(rs.getInt(1));
                pm.setName(rs.getString(2));
                paymentMethods.add(pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentMethods.get(0);
    }

    public ArrayList<CartItem> getCartItemsByOrder(int orderId) {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        try {
            String query = "select top 1 with ties p.id, p.name,pp.imageurl, od.quantity, p.price, p.sale from \n" +
                    "  orders o \n" +
                    "  inner join order_product od on o.id = od.orderid\n" +
                    "  inner join products p on od.productid = p.id\n" +
                    "  inner join product_pictures pp on p.id = pp.productid\n" +
                    "  where o.id = ?\n" +
                    "  order by ROW_NUMBER() over (partition by p.id order by p.id)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setUrl(rs.getString(3));
                item.setQuantity(rs.getInt(4));
                item.setPrice(rs.getInt(5));
                item.setSale(rs.getInt(6));
                cartItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public boolean isRatedPosition(String email, int productId) {
        String checkEmail = null;
        try {
            String query = "select * from productrating where email = ? and productid = ?";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                checkEmail = rs.getString(2);
            }
            if (checkEmail != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertOrderNotification(String email, Date time, String title, String content) {
        try {
            String query = "insert into notifications values (?,?,?,?)";
            connection = connect();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setTimestamp(2, new java.sql.Timestamp(time.getTime()));
            ps.setString(3, title);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
