<%--
  Created by IntelliJ IDEA.
  User: zhuangfan
  Date: 2019/3/25
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Product" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="http://www.daimajiayuan.com/download/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.js"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css">

    <!-- 3.0 -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

    <!-- 2.3.2
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.js"></script>
    -->
    <script type="text/javascript">
        function a(){
            $.ajax({
                url:"LoaddataServlet",
                type:"GET",
                /*success:function(e){
                    alert("servlet调用成功！");
                }*/
            });

        }
    </script>

</head>
<body onload="a()">
    <%
        if (request.getAttribute("products") != null) {
            ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
    %>
    <div class="col-md-6 col-lg-4 col-xl-3" >
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="<%=product.getImgUrl()%>" alt="">
            </div>
            <div class="card-body">
                <p><%=product.getCategory()%></p>
                <h4 class="card-product__title"><a href="/OnlineShopping/productdetail?productId=<%=product.getProductId()%>"><%=product.getProductName()%></a></h4>
                <p class="card-product__price">$<%=product.getPrice()%></p>
            </div>
        </div>
    </div>
    <%
        }
    }
    %>
    <%--<div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product1.png" alt="">
            </div>
            <div class="card-body">
                <p>Accessories</p>
                <h4 class="card-product__title"><a href="#">Quartz Belt Watch</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product2.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Beauty</p>
                <h4 class="card-product__title"><a href="#">Women Freshwash</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product3.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Decor</p>
                <h4 class="card-product__title"><a href="#">Room Flash Light</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product4.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Decor</p>
                <h4 class="card-product__title"><a href="#">Room Flash Light</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product5.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Accessories</p>
                <h4 class="card-product__title"><a href="#">Man Office Bag</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product6.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Kids Toy</p>
                <h4 class="card-product__title"><a href="#">Charging Car</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product7.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Accessories</p>
                <h4 class="card-product__title"><a href="#">Blutooth Speaker</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product8.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Kids Toy</p>
                <h4 class="card-product__title"><a href="#">Charging Car</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-lg-4">
        <div class="card text-center card-product">
            <div class="card-product__img">
                <img class="card-img" src="img/product/product1.png" alt="">
                <ul class="card-product__imgOverlay">
                    <li><button><i class="ti-search"></i></button></li>
                    <li><button><i class="ti-shopping-cart"></i></button></li>
                    <li><button><i class="ti-heart"></i></button></li>
                </ul>
            </div>
            <div class="card-body">
                <p>Accessories</p>
                <h4 class="card-product__title"><a href="#">Quartz Belt Watch</a></h4>
                <p class="card-product__price">$150.00</p>
            </div>
        </div>
    </div>--%>
</body>
</html>
