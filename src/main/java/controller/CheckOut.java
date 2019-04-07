package controller;

import bean.Order;
import bean.OrderDetail;
import bean.Product;
import bean.UserAccount;
import dao.AccountsDAO;
import dao.OrderDAO;
import dao.ProductsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

public class CheckOut extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
//        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
        TreeMap<String, Integer> cart = (TreeMap<String, Integer>) session.getAttribute("cart");
        String uid = "";
        Cookie[] cookies = request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("uid")){
                uid = cookies[i].getValue();
                break;
            }
        }
        if (uid.equals("")) {
            response.sendRedirect("/OnlineShopping/login.jsp");
        }

        if (cart == null) {
            response.sendRedirect("/OnlineShopping/index.jsp");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        AccountsDAO accountsDAO = new AccountsDAO();
        UserAccount user = accountsDAO.getUser(uid);
        Order order = new Order();
        order.setUid(user.getUid());
        order.setOrderDate(df.format(new Date()));
        order.setCity("Toronto");
        order.setOrderStatus("Checked");
        order.setPaymentMethod("Online");
        order.setPostCode("M2J 1S5");
        order.setStreet(user.getAddress());

        OrderDAO orderDAO = new OrderDAO();
        int orderid = orderDAO.addOrder(order);
        double totalAmount = 0;

        for (String prodid: cart.keySet()){
            ProductsDAO productsDAO = new ProductsDAO();
            Product product = productsDAO.getProductsById(prodid);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderid);
            orderDetail.setProdId(Integer.parseInt(prodid));
            orderDetail.setQty(cart.get(prodid));
            orderDetail.setPrice(product.getPrice());
            orderDAO.addOrderDetail(orderDetail);
            totalAmount += cart.get(prodid) * product.getPrice();
        }
        orderDAO.updateTotalAmount(orderid,totalAmount);
        session.removeAttribute("cart");
        request.setAttribute("orderid",orderid);

        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doMethod(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doMethod(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
