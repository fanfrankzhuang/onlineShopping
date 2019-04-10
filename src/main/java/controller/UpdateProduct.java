package controller;

import bean.Product;
import dao.ProductsDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProduct extends HttpServlet {
    public void doMethod (HttpServletRequest request, HttpServletResponse response) {
        String prod_id = request.getParameter("prod_id");
        String prod_name = request.getParameter("prod_name");
        String category = request.getParameter("category");
        String color = request.getParameter("color");
        String availability = request.getParameter("availability");
        String imgUrl = request.getParameter("imgUrl");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        //request.getSession().setAttribute("searchName", name);
        Product product = new Product();
        product.setProductId(prod_id);
        product.setProductName(prod_name);
        product.setCategory(category);
        product.setColor(color);
        product.setAvailability(Integer.parseInt(availability));
        product.setImgUrl(imgUrl);
        product.setPrice(Double.parseDouble(price));
        product.setDescription(description);

        try {
            ProductsDAO productsDAO = new ProductsDAO();
            int result = productsDAO.updateProductById(product);
            request.setAttribute("updateResult",String.valueOf(result));
            request.getRequestDispatcher("productupdate.jsp").forward(request,response);
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
