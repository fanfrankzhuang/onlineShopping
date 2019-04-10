<%@ page import="java.util.TreeMap" %><%--
  Created by IntelliJ IDEA.
  User: zhuangfan
  Date: 2019/3/21
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <a class="navbar-brand logo_h" href="index.jsp"><img src="img/logo.png" alt=""></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%
                    Cookie[] cookies = request.getCookies();
                    Cookie cookie = null;
                    boolean hasCookie = false;
                    for (int i=0; i< cookies.length;i++) {
                        if (cookies[i].getName().equals("uid")) {
                            hasCookie = true;
                            cookie = cookies[i];
                            break;
                        }

                    }
                %>
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                        <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="category.jsp">Shop Category</a></li>
                                <li class="nav-item"><a class="nav-link" href="productmanagement.jsp">Product Management</a></li>
                                <li class="nav-item"><a class="nav-link" href="checkout.html">Product Checkout</a></li>
                                <li class="nav-item"><a class="nav-link" href="confirmation.jsp">Confirmation</a></li>
                                <li class="nav-item"><a class="nav-link" href="cart.jsp">Shopping Cart</a></li>
                            </ul>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Blog</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
                                <li class="nav-item"><a class="nav-link" href="single-blog.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Pages</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                                <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                                <li class="nav-item"><a class="nav-link" href="tracking-order.html">Tracking</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
                    </ul>

                    <ul class="nav-shop">
                        <li class="nav-item"><button><i class="ti-search"></i></button></li>
                        <%
                            TreeMap<String, Integer> cartsNo =
                                    (TreeMap<String, Integer>) request.getSession().getAttribute("cart");
                            if (cartsNo != null && cartsNo.size() != 0) {
                        %>
                        <li class="nav-item"><button href="/OnlineShopping/cart.jsp"><i class="ti-shopping-cart"></i><span class="nav-shop__circle"><%=cartsNo.size()%></span></button> </li>
                        <%
                            } else {
                                %>
                        <li class="nav-item"><button href="/OnlineShopping/cart.jsp"><i class="ti-shopping-cart"></i></button> </li>
                        <%
                            }
                        %>
                         <%
                            if (hasCookie) {
                                %>
                        <li class="nav-item"><%=cookie.getValue()%> | <a href="/OnlineShopping/logout"> Log out</a></li>
                        <%
                            }else {
                                %>
                        <li class="nav-item"><a href="login.jsp">Log in | </a><a href="register.jsp"> Sign up</a></li>
                        <%
                            }
                        %>

                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
</body>
</html>
