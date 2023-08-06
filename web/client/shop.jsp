<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="client/assets/css/priceRange.css">
        <link rel="stylesheet" href="client/assets/css/Autocomplete.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function searchPage(i) {
//                alert(i);
                var search = $("#search").val();
                var pageIndex = i;
                var categoryFilter = document.querySelectorAll('input[name="categoryFilter[]"]:checked');
                var category = [];
                for (var i = 0; i < categoryFilter.length; i++) {
                    category.push(categoryFilter[i].value);
                }
//                    alert("cate"+category.length);
                var colorFilter = document.querySelectorAll('input[name="colorFilter[]"]:checked');
                var color = [];
                for (var i = 0; i < colorFilter.length; i++) {
                    color.push(colorFilter[i].value);
                }
//                    alert("color"+color.length);
                var sizeFilter = document.querySelectorAll('input[name="sizeFilter[]"]:checked');
                var size = [];
                for (var i = 0; i < sizeFilter.length; i++) {
                    size.push(sizeFilter[i].value);
                }
//                    alert("size"+size.length);
                var priceEnd = document.querySelector('input[name="end"]').value;
                var priceStart = document.querySelector('input[name="start"]').value;

                var orderBy = $("#orderBy").val();
                window.scrollTo(0, 500);
//                    var color = document.querySelector('input[name="colorFilter"]:checked').value;
//                    var size = document.querySelector('input[name="sizeFilter"]:checked').value;
                $.ajax({
                    url: "SearchShop",
                    data: {"search": search, "color[]": color, "size[]": size, "category[]": category, end: priceEnd, start: priceStart, orderBy: orderBy, index: pageIndex},
                    type: "POST",
                    success: function (data) {
                        var searchList = document.getElementById('content');
                        searchList.innerHTML = data;
                        document.getElementById('clear').innerHTML = "";
                    }
                });
            }
        </script>
        <script>
            $(document).ready(function () {
                $("#search").on("keyup", function () {
                    searchAjax();
                });
                $('input[name="categoryFilter[]"]').click(function () {
                    var categoryFilter = document.querySelectorAll('input[name="categoryFilter[]"]');
                    if (this.value !== "") {
                        document.getElementById("category-all").checked = false;
                    }
                    if ($('#category-all').is(":checked")) {
                        for (var i = 1; i < categoryFilter.length; i++) {
                            document.getElementById("category-" + categoryFilter[i].value).checked = false;
                        }
                    }
                    searchAjax();
                });
                $('input[name="colorFilter[]"]').click(function () {
                    var colorFilter = document.querySelectorAll('input[name="colorFilter[]"]');
                    if (this.value !== "") {
                        document.getElementById("color-all").checked = false;
                    }
                    if ($('#color-all').is(":checked")) {
                        for (var i = 1; i < colorFilter.length; i++) {
                            document.getElementById("color-" + colorFilter[i].value).checked = false;
                        }
                    }
                    searchAjax();
                });
                $('input[name="sizeFilter[]"]').click(function () {
                    var sizeFilter = document.querySelectorAll('input[name="sizeFilter[]"]');
                    if (this.value !== "") {
                        document.getElementById("size-all").checked = false;
                    }
                    if ($('#size-all').is(":checked")) {
                        for (var i = 1; i < sizeFilter.length; i++) {
                            document.getElementById("size-" + sizeFilter[i].value).checked = false;
                        }
                    }
                    searchAjax();
                });
                $('input[name="start"]').on("change", function () {
                    searchAjax();
                });
                $('input[name="end"]').on("change", function () {
                    searchAjax();
                });
                $('select[name="orderBy"]').on("change", function () {
                    searchAjax();
                });
                $('button[name="pageLink"]').click(function () {
                    var search = $("#search").val();
                    var pageIndex = $(this).val();
//                    alert(pageIndex);
                    var categoryFilter = document.querySelectorAll('input[name="categoryFilter[]"]:checked');
                    var category = [];
                    for (var i = 0; i < categoryFilter.length; i++) {
                        category.push(categoryFilter[i].value);
                    }
//                    alert("cate"+category.length);
                    var colorFilter = document.querySelectorAll('input[name="colorFilter[]"]:checked');
                    var color = [];
                    for (var i = 0; i < colorFilter.length; i++) {
                        color.push(colorFilter[i].value);
                    }
//                    alert("color"+color.length);
                    var sizeFilter = document.querySelectorAll('input[name="sizeFilter[]"]:checked');
                    var size = [];
                    for (var i = 0; i < sizeFilter.length; i++) {
                        size.push(sizeFilter[i].value);
                    }
//                    alert("size"+size.length);
                    var priceEnd = document.querySelector('input[name="end"]').value;
                    var priceStart = document.querySelector('input[name="start"]').value;

                    var orderBy = $("#orderBy").val();
                    window.scrollTo(0, 500);
//                    var color = document.querySelector('input[name="colorFilter"]:checked').value;
//                    var size = document.querySelector('input[name="sizeFilter"]:checked').value;
                    $.ajax({
                        url: "SearchShop",
                        data: {"search": search, "color[]": color, "size[]": size, "category[]": category, end: priceEnd, start: priceStart, orderBy: orderBy, index: pageIndex},
                        type: "POST",
                        success: function (data) {
                            var searchList = document.getElementById('content');
                            searchList.innerHTML = data;
                            document.getElementById('clear').innerHTML = "";
                        }
                    });
                });
                function searchAjax() {
                    var search = $("#search").val();
                    console.log(search);
                    var categoryFilter = document.querySelectorAll('input[name="categoryFilter[]"]:checked');
                    var category = [];
                    for (var i = 0; i < categoryFilter.length; i++) {
                        category.push(categoryFilter[i].value);
                    }
//                    alert("cate"+category.length);
                    var colorFilter = document.querySelectorAll('input[name="colorFilter[]"]:checked');
                    var color = [];
                    for (var i = 0; i < colorFilter.length; i++) {
                        color.push(colorFilter[i].value);
                    }
//                    alert("color"+color.length);
                    var sizeFilter = document.querySelectorAll('input[name="sizeFilter[]"]:checked');
                    var size = [];
                    for (var i = 0; i < sizeFilter.length; i++) {
                        size.push(sizeFilter[i].value);
                    }
//                    alert("size"+size.length);
                    var priceEnd = document.querySelector('input[name="end"]').value;
                    var priceStart = document.querySelector('input[name="start"]').value;

                    var orderBy = $("#orderBy").val();
                    window.scrollTo(0, 500);
//                    var color = document.querySelector('input[name="colorFilter"]:checked').value;
//                    var size = document.querySelector('input[name="sizeFilter"]:checked').value;
                    $.ajax({
                        url: "SearchShop",
                        data: {"search": search, "color[]": color, "size[]": size, "category[]": category, end: priceEnd, start: priceStart, orderBy: orderBy, index: "1"},
                        type: "POST",
                        success: function (data) {
                            var searchList = document.getElementById('content');
                            searchList.innerHTML = data;
                            document.getElementById('clear').innerHTML = "";

                        }
                    });
                }
            });
        </script>
    </head>
    <body>
        <!-- Topbar Start -->
        <jsp:include page="../client/partials/_topbar.jsp" />
        <!-- Topbar End -->
        <!-- Navbar Start -->  
        <jsp:include page="../client/partials/_navbar.jsp" />
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Our Shop</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Shop</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Shop Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->
                <div class="col-lg-2 col-md-12">
                    <!--Price-->
                    <div class="border-bottom mb-4 pb-4">
                        <h5 class="font-weight-semi-bold mb-4">Filter by Price</h5>
                        <!--<form>-->
                        <div class="values">
                            <span id="range1">
                                ${start}
                            </span>
                            <span> &dash; </span>
                            <span id="range2">
                                ${end}
                            </span>
                        </div>
                        <div class="RangeContainer">
                            <div class="slider-track"></div>
                            <input name="start" type="range" min="${start}" max="${end}" value="${start}" id="slider-1" oninput="slideOne()">
                            <input name="end" type="range" min="${start}" max="${end}" value="${end}" id="slider-2" oninput="slideTwo()">
                        </div>

                        <!--</form>-->
                    </div>
                    <!-- Category Start -->
                    <div class="border-bottom mb-4 pb-4">
                        <h5 class="font-weight-semi-bold mb-4">Filter by Category</h5>
                        <!--<form>-->
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" class="custom-control-input" id="category-all" ${category == "" ? "checked" : "" } name="categoryFilter[]" value="">
                            <label class="custom-control-label" for="category-all">All Category</label>
                            <span class="badge border font-weight-normal">1000</span>
                        </div>
                        <c:forEach items="${categoryList}" var="c">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" ${category == c.categoryID ? "checked" : "" } id="category-${c.categoryID}" name="categoryFilter[]" value="${c.categoryID}">
                                <label class="custom-control-label" for="category-${c.categoryID}">${c.categoryName}</label>
                                <span class="badge border font-weight-normal">${c.productQuantity}</span>
                            </div>
                        </c:forEach>
                        <!--</form>-->
                    </div>
                    <!-- Category End -->
                    <!-- Color Start -->
                    <div class="border-bottom mb-4 pb-4">
                        <h5 class="font-weight-semi-bold mb-4">Filter by color</h5>
                        <!--<form>-->
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" class="custom-control-input" id="color-all" checked name="colorFilter[]" value="">
                            <label class="custom-control-label" for="color-all">All Color</label>
                            <span class="badge border font-weight-normal">1000</span>
                        </div>
                        <c:forEach items="${colorList}" var="c">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id="color-${c.colorId}" name="colorFilter[]" value="${c.colorId}">
                                <label class="custom-control-label" for="color-${c.colorId}">${c.colorName}</label>
                                <span class="badge border font-weight-normal">${c.quantity}</span>
                            </div>
                        </c:forEach>
                        <!--</form>-->
                    </div>
                    <!-- Color End -->

                    <!-- Size Start -->
                    <div class="mb-5">
                        <h5 class="font-weight-semi-bold mb-4">Filter by size</h5>
                        <!--<form>-->
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" class="custom-control-input" checked id="size-all" name="sizeFilter[]" value="">
                            <label class="custom-control-label" for="size-all">All Size</label>
                            <span class="badge border font-weight-normal">1000</span>
                        </div>
                        <c:forEach items="${sizeList}" var="s">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id="size-${s.name}" name="sizeFilter[]" value="${s.name}">
                                <label class="custom-control-label" for="size-${s.name}">${s.name}</label>
                                <span class="badge border font-weight-normal">${s.quantity}</span>
                            </div>
                        </c:forEach>
                        <!--</form>-->
                    </div>
                    <!-- Size End -->
                </div>
                <!-- Shop Sidebar End -->


                <!-- Shop Product Start -->
                <div class="col-lg-10 col-md-12">
                    <div>
                        <div class="col-12 pb-1">
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <!--<form action="">-->
                                <div class="input-group">
                                    <input type="text" class="form-control" id="search" name="search" value="${search}" placeholder="Search by name" autofocus>
                                    <div class="input-group-append">
                                        <span class="input-group-text bg-transparent text-primary">
                                            <i class="fa fa-search"></i>
                                        </span>
                                    </div>
                                </div>
                                <!--                                                                </form>
                                -->                                
                                <div class="dropdown ml-4">
                                    <select class="btn border dropdown-toggle" name="orderBy" id="orderBy">
                                        <option selected class="selected btn border dropdown-toggle" aria-haspopup="true"
                                                aria-expanded="false" hidden="" value="ProductSeriID asc">Sort By</option>
                                        <option class="dropdown-item" value="CreatedDate desc">Latest</option>
                                        <option class="dropdown-item" value="Price asc">Price asc</option>
                                        <option class="dropdown-item" value="Price desc">Price desc</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div id="content" class="row pb-3">
                            <div class="col-12 pb-1">
                                <p style="color: white">.</p>
                            </div>
                            <c:forEach items="${productSerie}" var="serie">
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
                                                <!--<h6 class="text-muted ml-2"><del>${serie.price}</del></h6>-->
                                            </div>
                                        </div>
                                        <div class="card-footer d-flex justify-content-between bg-light border">
                                            <!--<a href="SerieDetail?serieId="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>-->
                                            <a href="#"  
                                               class="btn btn-sm text-dark p-0 add-to-cart-btn"
                                               data-product-seri-id="${serie.productSeriId}">
                                                <i class="fas fa-shopping-cart text-primary mr-1"></i> Add To Cart
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="col-12 pb-1">
                                <p> There is ${productSerie.size()} on ${listSize} Products</p>
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-center mb-3">
                                        <li class="page-item ${currentPage > 1 ? "" : "disabled"}">
                                            <button class="page-link" name="pageLink" id="pageLink" value="${currentPage-1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </button>
                                        </li>
                                        <c:forEach begin="1" end="${endPage}" var="i">
                                            <li class="page-item">
                                                <button class="page-link ${currentPage == i ? "disabled" : ""}" name="pageLink" id="pageLink" value="${i}">${i}</button>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage < endPage ? "" : "disabled"}">
                                            <button class="page-link" name="pageLink" id="pageLink" value="${currentPage+1}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </button>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- Shop Product End -->
            </div>
        </div>
        <!-- Shop End -->


        <!-- Footer Start -->
        <jsp:include page="../client/partials/_footer.jsp"></jsp:include>
            <!-- Footer End -->
            <!-- Back to Top -->
            <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

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

            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
            <script src="client/lib/easing/easing.min.js"></script>
            <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>
            <!-- Contact Javascript File -->
            <script src="client/mail/jqBootstrapValidation.min.js"></script>
            <script src="client/mail/contact.js"></script>

            <!-- Template Javascript -->
            <script src="client/js/main.js"></script>
            <script src="client/assets/js/range.js"></script>

            
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
    </body>
</html>