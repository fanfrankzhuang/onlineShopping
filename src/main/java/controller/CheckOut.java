package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.TreeMap;

public class CheckOut extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        TreeMap<String, Integer> cart = (TreeMap<String, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new TreeMap<>();
        }

        if (request.getParameter("productId")!= null) {
            String prodId = request.getParameter("productId");
            Integer amount = Integer.parseInt(request.getParameter("qty"));
            cart.put(prodId, amount);
        }
        session.setAttribute("cart", cart);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doMethod(request, response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doMethod(request, response);
    }
}
