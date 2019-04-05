package controller;

import bean.Product;
import dao.ProductsDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductDetail extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("productId");
        //request.getSession().setAttribute("searchName", name);
        Product product;
        try {
            ProductsDAO productsDAO = new ProductsDAO();
            product = productsDAO.getProductsById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("single-product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        doMethod(request, response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        doMethod(request, response);
    }
}
