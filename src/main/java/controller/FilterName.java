package controller;

import bean.Product;
import dao.ProductsDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FilterName extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("searchname");
        request.getSession().setAttribute("searchName", name);
        ArrayList<Product> products;
        try {
            ProductsDAO productsDAO = new ProductsDAO();
            products = productsDAO.getProductsByName(name);
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
