<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : detail
    Created on : 12-May-2023, 21:36:33
    Author     : ADMIN
--%>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>New Customer</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="admin/assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <!--        <link rel="stylesheet" href="admin/assets/vendors/select2/select2.min.css">
                <link rel="stylesheet" href="admin/assets/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">-->
        <link rel="stylesheet" href="admin/assets/vendors/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="admin/assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/owl-carousel-2/owl.carousel.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/owl-carousel-2/owl.theme.default.min.css">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="admin/assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="admin/assets/images/favicon.png" />
        <jsp:include page="../Util/Format.jsp"></jsp:include>
        </head>
        <body>
            <div class="container-scroller">
                <!-- partial:../../partials/_sidebar.html -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper">
                    <!-- partial:../../partials/_navbar.html -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <!-- partial -->
                    <div class="main-panel">
                        <!--<div class="content-wrapper" style="padding-top: 5%">-->
                        <form class="content-wrapper forms-sample mt-3">
                            <!--<div class="row ">-->
                            <div class="col-12 grid-margin">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">  
                                            <div class="col-sm-8">
                                                <h2 style="font-weight: bold" class="card-title col-10">Computer Online Shop</h2>
                                            </div>
                                            <div class="col-sm-4" style="padding-left: 50px">
                                                <label>Đc: Số 1 Vạn Phúc, HĐ</label><br>
                                                <label>ĐT: 0123456789</label>
                                            </div>
<!--                                            <a href="OrderIO?OrderId=${order.orderId}">
                                            <button>Export</button>
                                            </a>-->
                                    </div>
                                    <div class="row" style="padding-top: 30px">
                                        <div class="col-sm-8">
                                            <h3 style="font-weight: bold; padding-left: 300px" class="card-title col-10">ORDER: #${order.orderId}</h3>
                                        </div>
                                        <div class="col-sm-4" style="padding-left: 50px">
                                            <label>Ngày: <fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy"/></label><br>
                                        </div>
                                    </div>
                                    <h4 class="card-title">Customer Information: </h4>
                                    <div class="form-group" style="margin-bottom: 0px; padding-left: 5px; padding-bottom: 30px">
                                        <label>${cus.firstName} ${cus.lastName}</label>
                                        <br>
                                        <label>Điện thoại: ${cus.phone}</label>
                                        <br>
                                        <label>Email: ${cus.email}</label>
                                        <br>
                                        <label>Address: ${cus.address}</label>
                                    </div>
                                    <div class="form-group row">
                                        <label for="Description" class="col-sm-12 col-form-label"><h3>OrderItem List: </h3></label>
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th> OrderID </th>                                                            
                                                        <th> Product Name </th>
                                                        <th> Price </th>
                                                        <th> Quantity </th>
                                                        <th> Total </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="itemList" items="${orderItemList}">
                                                        <tr>
                                                            <td> ${itemList.orderItemId} </td>
                                                            <td> ${itemList.productId.productSerieId.productSeriName} </td>
                                                            <td> <fmt:formatNumber value="${itemList.productId.productSerieId.price}" type="number"/> </td>
                                                            <td> ${itemList.quantity} </td>
                                                            <td> <fmt:formatNumber value="${itemList.quantity * itemList.productId.productSerieId.price}" type="number"/> </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                            <label class="col-sm-4" style="float: right; padding-top: 5px">Total Money: <fmt:formatNumber value="${order.totalPrice}" type="number"/>VND</label>

                                        </div>
                                    </div>
                                    <h4 class="card-title">Order Modify History: <i class="mdi mdi-information" style="color: lightgrey"></i></h4>
                                    <div class="form-group row">
                                        <div class="col-sm-6">
                                            <label for="LastModified" class="col-form-label"><h6>Last Modified</h6></label>
                                            <span class="col-sm-12 form-control">
                                                <fmt:formatDate value="${order.modifiedDate}" pattern="dd-MM-yyyy"/>
                                            </span>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="LastModified" class="col-form-label"><h6>Last Modified By</h6></label>
                                            <span class="col-sm-12 form-control">${order.modifiedAccount.userName}</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <label for="ModifiedHistory" class="col-form-label"><h6>Modified History </h6></label>
                                        </div>
                                        <div class="col-sm-12">
                                            <span style="height: 150px;"  class="form-control">${order.modifiedHistory}</span>
                                        </div>
                                    </div>
                                    <div style="margin: 10px 0px" class="right-align">
<!--                                        <a href="EditOrder?orderId=${order.orderId}">
                                            <button type="button" class="btn btn-primary mr-2">Update</button>
                                        </a>  -->
                                        <button type="button" class="btn btn-primary mr-2" onclick="window.history.back()">Back</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- content-wrapper ends -->
                    <!-- partial:../../partials/_footer.html -->
                    <jsp:include page="../partials/_footer.jsp"></jsp:include>
                    <!-- partial -->
                </div>
                <!-- main-panel ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="assets/vendors/chart.js/Chart.min.js"></script>
        <script src="assets/vendors/progressbar.js/progressbar.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script src="assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <script src="assets/js/settings.js"></script>
        <script src="assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="assets/js/file-upload.js"></script>
        <script src="assets/js/typeahead.js"></script>
        <script src="assets/js/select2.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
