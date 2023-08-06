<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Trang chủ</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="client/assets/css/priceRange.css">
        <link rel="stylesheet" href="client/assets/css/Autocomplete.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <jsp:include page="../client/partials/_topbar.jsp" />
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <jsp:include page="../client/partials/_navbar.jsp" />
        <!-- Navbar End -->

        <!-- Featured Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Quality Product</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Free Shipping</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">7-Day Return</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
                    </div>
                </div>
            </div>
        </div>
        <!-- Featured End -->


        <!-- Offer Start -->
        <!--        <div class="container-fluid offer pt-5">
                    <div class="row px-xl-5">
                        <div class="col-md-6 pb-4">
                            <div class="position-relative bg-secondary text-center text-md-right text-white mb-2 py-5 px-5">
                                <img src="img/offer-1.png" alt="">
                                <div class="position-relative" style="z-index: 1;">
                                    <h5 class="text-uppercase text-primary mb-3">20% off the all order</h5>
                                    <h1 class="mb-4 font-weight-semi-bold">Spring Collection</h1>
                                    <a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop Now</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 pb-4">
                            <div class="position-relative bg-secondary text-center text-md-left text-white mb-2 py-5 px-5">
                                <img src="img/offer-2.png" alt="">
                                <div class="position-relative" style="z-index: 1;">
                                    <h5 class="text-uppercase text-primary mb-3">20% off the all order</h5>
                                    <h1 class="mb-4 font-weight-semi-bold">Winter Collection</h1>
                                    <a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
        <!-- Offer End -->


        <!-- Trendy Products Start -->
        <c:if test="${trendySerie.size() > 0}">
            <div class="container-fluid pt-5">
                <div class="text-center mb-4">
                    <h2 class="section-title px-5"><span class="px-2">Trendy Products</span></h2>
                </div>
                <div class="row px-xl-5 pb-3">
                    <c:forEach items="${trendySerie}" var="serie">
                        <div class="col-lg-2 col-md-6 col-sm-12 pb-1">
                            <div class="card product-item border-0 mb-4">
                                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                    <a href="SerieDetail?serieId=${serie.productSeriId}">
                                        <img class="img-fluid w-100" src="images/ProductSeri/${serie.imagePreview}" alt="">
                                    </a>
                                </div>
                                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                    <h6 class="text-truncate mb-3">${serie.productSeriName}</h6>
                                    <div class="d-flex justify-content-center">
                                        <h6><fmt:formatNumber value="${serie.price}" type="number" /></h6>
                                        <h6 class="text-muted ml-2"><del><fmt:formatNumber value="${serie.price}" type="number" /></del></h6>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between bg-light border">
                                    <a href="#"  
                                       class="btn btn-sm text-dark p-0 add-to-cart-btn"
                                       data-product-seri-id="${serie.productSeriId}">
                                        <i class="fas fa-shopping-cart text-primary mr-1"></i> Add To Cart
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <!-- Products End -->


        <!-- Subscribe Start -->
        <!--        <div class="container-fluid bg-secondary my-5">
                    <div class="row justify-content-md-center py-5 px-xl-5">
                        <div class="col-md-6 col-12 py-5">
                            <div class="text-center mb-2 pb-2">
                                <h2 class="section-title px-5 mb-3"><span class="bg-secondary px-2">Stay Updated</span></h2>
                                <p>Amet lorem at rebum amet dolores. Elitr lorem dolor sed amet diam labore at justo ipsum eirmod duo labore labore.</p>
                            </div>
                            <form action="">
                                <div class="input-group">
                                    <input type="text" class="form-control border-white p-4" placeholder="Email Goes Here">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary px-4">Subscribe</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>-->
        <!-- Subscribe End -->


        <!-- Products Start -->
        <div class="container-fluid pt-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">Just Arrived</span></h2>
            </div>
            <div class="row px-xl-5 pb-3">
                <c:forEach items="${newSerie}" var="serie">
                    <div class="col-lg-2 col-md-6 col-sm-12 pb-1">
                        <div class="card product-item border-0 mb-4">
                            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                <a href="SerieDetail?serieId=${serie.productSeriId}">
                                    <img class="img-fluid w-100" src="images/ProductSeri/${serie.imagePreview}" alt="">
                                </a>
                            </div>
                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                <h6 class="text-truncate mb-3">${serie.productSeriName}</h6>
                                <div class="d-flex justify-content-center">
                                    <h6><fmt:formatNumber value="${serie.price}" type="number" /></h6>
                                    <h6 class="text-muted ml-2"><del><fmt:formatNumber value="${serie.price}" type="number" /></del></h6>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between bg-light border">
                                <a href="#"  
                                   class="btn btn-sm text-dark p-0 add-to-cart-btn"
                                   data-product-seri-id="${serie.productSeriId}">
                                    <i class="fas fa-shopping-cart text-primary mr-1"></i> Add To Cart
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Products End -->


        Vendor Start 
        <div class="container-fluid py-5">
            <div class="row px-xl-5">
                <div class="col">
                    <div class="owl-carousel vendor-carousel">
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-1.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-2.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-3.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-4.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-5.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-6.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-7.jpg" alt="">
                        </div>
                        <div class="vendor-item border p-4">
                            <img src="img/vendor-8.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        Vendor End 


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

            <script>
                $(document).ready(function () {
                    $(".add-to-cart-btn").click(function (event) {
                        event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>

                        var productSeriId = $(this).data("product-seri-id"); // Lấy giá trị data-product-seri-id

                        $.ajax({
                            type: "GET",
                            url: "addToCart",
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

            <!-- Contact Javascript File -->
            <script src="client/mail/jqBootstrapValidation.min.js"></script>
            <script src="client/mail/contact.js"></script>

            <!-- Template Javascript -->
            <script src="client/js/main.js"></script>
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
                    duration: 6000,
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
    </body>

</html>