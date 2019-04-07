package dao;

import bean.Order;
import bean.OrderDetail;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {
    public int addOrder (Order order) {
        int result = 0;
        PreparedStatement stmt = null;
        try {
            Connection conn = DbConnection.getDbConnection("mysql");

            stmt = conn.prepareStatement("insert into orders (uid, order_date, order_status, payment_method, " +
                    "street, city, postalcode) values (?,?,?,?,?,?,?)");
            stmt.setString(1, order.getUid());
            stmt.setString(2, order.getOrderDate());
            stmt.setString(3, order.getOrderStatus());
            stmt.setString(4, order.getPaymentMethod());
            stmt.setString(5, order.getStreet());
            stmt.setString(6, order.getCity());
            stmt.setString(7, order.getPostCode());

            stmt.executeUpdate();

            String sql = "select max(order_id) from orders;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (Exception e) {

            e.getStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void addOrderDetail (OrderDetail od) throws SQLException, ClassNotFoundException {

        Connection conn = DbConnection.getDbConnection("mysql");
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("insert into order_detail (order_id, prod_id, qty, price) " +
                    " values (?,?,?,?)");
            stmt.setInt(1, od.getOrderId());
            stmt.setInt(2, od.getProdId());
            stmt.setInt(3, od.getQty());
            stmt.setDouble(4, od.getPrice());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.getStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTotalAmount(int orderid, double amount) throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement = null;
        try {
            String sql = "update orders set totalamount = " + amount + " where order_id = " + orderid + ";";
            statement = conn.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.getStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Order getOrderById(int orderid) throws SQLException, ClassNotFoundException {
        Order order = new Order();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "Select uid, order_date, order_status, payment_method,street, city, postalcode,totalamount from orders " +
                    "where order_id = " + orderid +";";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                order.setOrderId(orderid);
                order.setUid(resultSet.getString("uid"));
                order.setOrderDate(resultSet.getString("order_date"));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setPaymentMethod(resultSet.getString("payment_method"));
                order.setStreet(resultSet.getString("street"));
                order.setCity(resultSet.getString("city"));
                order.setPostCode(resultSet.getString("postalcode"));
                order.setTotalAmount(resultSet.getDouble("totalamount"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return order;
    }

    public ArrayList<OrderDetail> getOrderDetailById(int orderid) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "Select prod_id, qty, price from order_detail " +
                    "where order_id = " + orderid +";";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                orderDetail.setOrderId(orderid);
                orderDetail.setProdId(resultSet.getInt("prod_id"));
                orderDetail.setQty(resultSet.getInt("qty"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetails.add(orderDetail);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return orderDetails;
    }
}
