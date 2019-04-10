package dao;

import bean.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductsDAO {
    public ArrayList<Product> getProductsByCategory (String category) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement = null;
        try {
            String sql = "select prod_id, product_name, price, img_url, category,color from products where category='"
                    + category + "';";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                product.setColor(resultSet.getString("color"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;

    }

    public ArrayList<Product> getProductsByName (String productName) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category, color from products " +
                    "where product_name like '%" + productName + "%'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                product.setColor(resultSet.getString("color"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;

    }

    public Product getProductsById (String productId) throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category,description,color,availability from products " +
                    "where prod_id = '" + productId + "'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                product.setProductId(resultSet.getString("prod_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                product.setDescription(resultSet.getString("description"));
                product.setColor(resultSet.getString("color"));
                product.setAvailability(resultSet.getInt("availability"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return product;

    }

    public ArrayList<Product> getTrendingProducts() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category,color,availability,description" +
                    " from products;";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                product.setColor(resultSet.getString("color"));
                product.setAvailability(resultSet.getInt("availability"));
                product.setDescription(resultSet.getString("description"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;

    }

    public int updateProductById(Product product) throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.getDbConnection("mysql");
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("update products set product_name = ?, category = ?, color = ?, " +
                    "availability = ?, img_url = ?, price = ?, description = ? where prod_id = ?");
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getColor());
            stmt.setInt(4, product.getAvailability());
            stmt.setString(5, product.getImgUrl());
            stmt.setDouble(6, product.getPrice());
            stmt.setString(7, product.getDescription());
            stmt.setString(8, product.getProductId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.getStackTrace();
            return -1;

        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
