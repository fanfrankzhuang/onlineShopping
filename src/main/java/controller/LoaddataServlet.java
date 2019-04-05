package controller;

import bean.Product;
import dao.ProductsDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LoaddataServlet extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Product> products;
        String requestURI = request.getRequestURI();
        try {
            if (request.getAttribute("trendingProducts") == null) {
                ProductsDAO productsDAO = new ProductsDAO();
                products = productsDAO.getTrendingProducts();

                request.setAttribute("trendingProducts", products);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
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
