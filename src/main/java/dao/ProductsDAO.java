package dao;

import bean.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductsDAO {
    public ArrayList<Product> getProductsByCategory (String category) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement = null;
        try {
            String sql = "select prod_id, product_name, price, img_url, category from products where category='"
                    + category + "';";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setPurductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;
        //test code
/*        ArrayList<Product> testProducts = new ArrayList<>();
        Product testProduct1 = new Product();
        testProduct1.setCategory("Men");
        testProduct1.setImgUrl("img/product/product1.png");
        testProduct1.setPrice(150.00);
        testProduct1.setProductId("1");
        testProduct1.setPurductName("Quartz Belt Watch");
        testProducts.add(testProduct1);
        return testProducts;*/
    }

    public ArrayList<Product> getProductsByName (String productName) throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category from products " +
                    "where product_name like '%" + productName + "%'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setPurductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;
        //test code
/*        ArrayList<Product> testProducts = new ArrayList<>();
        Product testProduct1 = new Product();
        testProduct1.setCategory("Men");
        testProduct1.setImgUrl("img/product/product1.png");
        testProduct1.setPrice(150.00);
        testProduct1.setProductId("1");
        testProduct1.setPurductName("Quartz Belt Watch");
        testProducts.add(testProduct1);
        return testProducts;*/
    }

    public Product getProductsById (String productId) throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category,description from products " +
                    "where prod_id = '" + productId + "'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                product.setProductId(resultSet.getString("prod_id"));
                product.setPurductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                product.setDescription(resultSet.getString("description"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return product;
        //test code
/*        Product testProduct1 = new Product();
        testProduct1.setCategory("Men");
        testProduct1.setImgUrl("img/product/product1.png");
        testProduct1.setPrice(150.00);
        testProduct1.setProductId("1");
        testProduct1.setPurductName("Quartz Belt Watch");
        return testProduct1;*/
    }

    public ArrayList<Product> getTrendingProducts() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = DbConnection.getDbConnection("mysql");
        Statement statement;
        try {
            String sql = "select prod_id, product_name, price, img_url, category from products limit 10;";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("prod_id"));
                product.setPurductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImgUrl(resultSet.getString("img_url"));
                product.setCategory(resultSet.getString("category"));
                products.add(product);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;
        //test code
/*        ArrayList<Product> testProducts = new ArrayList<>();
        Product testProduct1 = new Product();
        testProduct1.setCategory("Men");
        testProduct1.setImgUrl("img/product/product1.png");
        testProduct1.setPrice(150.00);
        testProduct1.setProductId("1");
        testProduct1.setPurductName("Quartz Belt Watch");
        testProducts.add(testProduct1);
        Product testProduct2 = new Product();
        testProduct2.setCategory("Beauty");
        testProduct2.setImgUrl("img/product/product2.png");
        testProduct2.setPrice(160.00);
        testProduct2.setProductId("2");
        testProduct2.setPurductName("Women Freshwash");
        testProducts.add(testProduct2);
        return testProducts;*/
    }
}
