package controller;

import bean.Product;
import dao.ProductsDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilterCategory extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) {
        String cat = request.getParameter("brand");
        request.getSession().setAttribute("category", cat);
        ArrayList<Product> products;
        try {
            ProductsDAO productsDAO = new ProductsDAO();
            products = productsDAO.getProductsByCategory(cat);
            request.setAttribute("products", products);
            request.getRequestDispatcher("category.jsp").forward(request, response);
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
