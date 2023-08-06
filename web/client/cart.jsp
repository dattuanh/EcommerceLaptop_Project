<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet">
        <script src="client/https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <!--zoom image-->
        <link href="client/assets/css/zoom-image-css.jsp" rel="stylesheet"/>
        <script src="client/assets/js/zoom-image-js.jsp"></script>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <jsp:include page="../client/partials/_topbar.jsp" />
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <jsp:include page="../client/partials/_navbar.jsp"></jsp:include>
            <!-- Navbar End -->


            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="">Home</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Shopping Cart</p>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->

            <!-- Cart Start -->
            <div class="container-fluid pt-5">
                <div class="row px-xl-5">
                    <div class="col-lg-8 table-responsive mb-5">
                        <table class="table table-bordered text-center mb-0">
                            <thead class="bg-secondary text-dark">
                                <tr>
                                    <th style="text-align: left; padding-left: 10px">Products</th>
                                    <th style="text-align: right; padding-right: 10px">Price</th>
                                    <th>Quantity</th>
                                    <th style="text-align: right; padding-right: 10px">Total</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach var="o" items="${cart.listItem}">
                                <tr data-product-Id="${o.productSeri.productSeriId}">
                                    <td id="img-zoomer-box" style="padding: 0; text-align: left">
                                        <img id="img-1" src="images/ProductSeri/${o.productSeri.imagePreview}" alt="" style="width: 100px;">${o.productSeri.productSeriName}
                                    </td>
                                    <td style="text-align: right">
                                        <input type="text" name="price" readonly
                                               value="<fmt:formatNumber value="${o.productSeri.price}" type="number"/>" 
                                               style="outline: none; border: none; width: 100px; text-align: right;">
                                    </td>
                                    <td class="align-middle">
                                        <div class="input-group quantity mx-auto" style="width: 100px;">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-primary btn-minus" id="geteletmentbyid">
                                                    <i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                            <input type="text" name="quantity" class="form-control form-control-sm bg-secondary text-center" value="${o.quantity}" max="${o.productSeri.getStock()}" data-product-id="${o.productSeri.productSeriId}" data-price="${o.productSeri.price}">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-primary btn-plus" id="geteletmentbyid">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="text-align: right">                                        
                                        <input type="text" name="totalPrice" id="totalPrice_${o.productSeri.productSeriId}" readonly 
                                               value="<fmt:formatNumber value="${o.quantity * o.productSeri.price}" type="number"/>" 
                                               min="${o.quantity * o.productSeri.price}" style="outline: none; border: none; width: 100px; text-align: right;">
                                    </td>
                                    <td class="align-middle">
                                        <!--<a href="DeleteCart?pSeriId=${o.productSeri.productSeriId}"><button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></a>-->
                                        <button class="btn btn-sm btn-primary delete-from-cart-btn" data-product-seri-id="${o.productSeri.productSeriId}"><i class="fa fa-times"></i></button>
                                    </td>
                                </tr>                      
                            </c:forEach>

                        </tbody>
                    </table>                    
                </div>
                <div class="col-lg-4">
                    <form class="mb-5" action="">
                        <div class="input-group">
                            <input type="text" class="form-control p-4" placeholder="Coupon Code">
                            <div class="input-group-append">
                                <button class="btn btn-primary">Apply Coupon</button>
                            </div>
                        </div>
                    </form>
                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                        </div>
                        <div class="card-body">
                            <div class="d-flex justify-content-between mb-3 pt-1">
                                <h6 class="font-weight-medium">Subtotal</h6>
                                <h6 class="font-weight-medium" id="subtotalCart"><fmt:formatNumber value="${cart.getTotalMoney()}" type="number"/></h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6 class="font-weight-medium">Shipping</h6>
                                <h6 class="font-weight-medium">0</h6>
                            </div>
                        </div>
                        <div class="card-footer border-secondary bg-transparent">
                            <div class="d-flex justify-content-between mt-2">
                                <h5 class="font-weight-bold">Total</h5>
                                <h5 class="font-weight-bold" id="totalCart"><fmt:formatNumber value="${cart.getTotalMoney()}" type="number"/></h5>
                            </div>
                            <a href="ViewCheckout" style="text-decoration: none">
                                <button class="btn btn-block btn-primary my-3 py-3 ">Proceed To Checkout</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Cart End -->


        <!-- Footer Start -->
        <jsp:include page="../client/partials/_footer.jsp"></jsp:include>
            <!-- Footer End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
            <script src="client/lib/easing/easing.min.js"></script>
            <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Contact Javascript File -->
            <script src="client/mail/jqBootstrapValidation.min.js"></script>
            <script src="client/mail/contact.js"></script>
        <c:if test = "${sessionScope.noti != null}">
            <div class="toast-div">
                <input id="toast-content" type="hidden" value="${sessionScope.noti}" />
                <input id="toast-status" type="hidden" value="${sessionScope.status}" />
                <div class="progress"></div>
            </div>
            <%--<jsp:include page="../shared/toastify.jsp" />--%>
            <script>
                var status = document.getElementById('toast-status').value;
                Toastify({
                    text: document.getElementById('toast-content').value,
                    duration: 3000,
                    close: true,
                    gravity: "bottom", // `top` or `bottom`
                    position: "center", // `left`, `center` or `right`
                    stopOnFocus: true, // Prevents dismissing of toast on hover
                    style: {
                        background: status === "1" ? "green" : "red"
                    },
                    onClick: function () {} // Callback after click
                }).showToast();

            </script>
        
            <jsp:include page="../shared/removeToastSession.jsp"></jsp:include>
        </c:if>

        <!-- Template Javascript -->
        <script>
            $(document).ready(function () {
                $(".btn-minus").click(function () {
                    //alert('minus');
                    var input = $(this).closest(".input-group").find("input[name='quantity']");
                    var quantity = parseInt(input.val());
                    if (quantity > 1) {
                        input.val(quantity - 1);
                        updateCart(input);
                    }
                });

                $(".btn-plus").click(function () {
                    //alert('plus');
                    var input = $(this).closest(".input-group").find("input[name='quantity']");
                    var quantity = parseInt(input.val());
                    var max = parseInt(input.attr("max"));
                    if (quantity < max) {
                        input.val(quantity + 1);
                        updateCart(input);
                    }
                });

                function updateCart(input) {
                    var productId = input.attr("data-product-id");
                    var price = input.attr("data-price");
                    var quantity = input.val();
                    var button = document.getElementById("geteletmentbyid");

                    $.ajax({
                        type: "POST",
                        url: "ViewCart",
                        data: {
                            productId: productId,
                            quantity: quantity
                        },
                        success: function (response) {
                            // Cập nhật số lượng sản phẩm trên giao diện
                            var quantityInput = response.quantity;
                            var showTotalPrice = quantityInput * price;
                            var formattedPrice = showTotalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/₫/g, '');
                            document.getElementById("totalPrice_" + productId).value = formattedPrice;
                            button.value = quantityInput;

                            var totalMoney = response.totalMoney;
                            var formattedTotalMoney = totalMoney.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/₫/g, '');
                            $("#totalCart").text(formattedTotalMoney);
                            $("#subtotalCart").text(formattedTotalMoney);
                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi nếu có
                            alert('fail');
                        }
                    });
                }
            });
        </script>

        <script>
            $(document).ready(function () {
                $(".delete-from-cart-btn").click(function (event) {
                    //event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>

                    var productSeriId = $(this).data("product-seri-id"); // Lấy giá trị data-product-seri-id

                    $.ajax({
                        type: "GET",
                        url: "DeleteCart",
                        data: {
                            pSeriId: productSeriId
                        },
                        success: function (response) {
                            window.location.reload();
                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi nếu có
                            alert("fail");
                        }
                    });
                });
            });
        </script>
    </body>

</html>
