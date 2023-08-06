<%-- 
    Document   : add
    Created on : 12-May-2023, 21:37:00
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="admin/assets/vendors/select2/select2.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
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
                <!-- partial:../../partials/_sidebar.jsp -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper">
                    <!-- partial:../../partials/_navbar.jsp -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <!-- partial -->
                    <div class="main-panel">

                        <div class="content-wrapper" style="padding-top: 5%">
                            <div class="row">
                                <div class="col-md-6 grid-margin stretch-card mx-auto">
                                    <div class="card">
                                        <div class="card-body">
                                            <form class="forms-sample mt-3" action="AddOrder" method="post">
                                                <div class="form-group row">
                                                    <label for="CustomerID" class="col-sm-3 col-form-label">CustomerID:</label>
                                                    <div class="col-sm-9">
                                                        <div class="dropdown">
                                                                <select class="btn btn-outline-primary dropdown-toggle" id="CustomerID" name="customerId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <c:forEach var="c" items="${allCustomers}">
                                                                <option class="dropdown-item" value="${c.customerId}">${c.userName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
<!--                                            <div class="form-group row">
                                                <label for="OrderDate" class="col-sm-3 col-form-label">Order Date</label>
                                                <div class="col-sm-9">
                                                    <input type="date" class="form-control" id="OrderDate" placeholder="OrderDate" name="orderDate">
                                                </div>
                                            </div>-->
                                            <div class="form-group row">
                                                <label for="TotalPrice" class="col-sm-3 col-form-label">Total Price</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="text" class="form-control" id="TotalPrice" placeholder="TotalPrice" name="totalPrice">-->
                                                    <input type="text" class="form-control" id="TotalPrice" onBlur="formatCurrency(this, '', 'blur');"
                                                           onkeyup="formatCurrency(this, '');" placeholder="VND #,###.00" name="totalPrice" />
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Status" class="col-sm-3 col-form-label">Status</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="text" class="form-control" id="Status" placeholder="Status" name="status">-->
                                                    <div class="dropdown">
                                                            <select class="btn btn-outline-primary dropdown-toggle" id="Status" name="status" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <c:forEach var="os" items="${allOrderStatus}">
                                                                <option class="dropdown-item" value="${os.orderStatusId}">${os.orderStatusName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
<!--                                            <div class="form-group row">
                                                <label for="PaymentDate" class="col-sm-3 col-form-label">Payment Date</label>
                                                <div class="col-sm-9">
                                                    <input type="date" class="form-control" id="PaymentDate" placeholder="PaymentDate" name="paymentDate">
                                                </div>
                                            </div>-->
                                            <div class="form-group row">
                                                <label for="PaymentMethod" class="col-sm-3 col-form-label">Payment Method</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="PaymentMethod" placeholder="PaymentMethod" name="paymentMethod">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="CashReceive" class="col-sm-3 col-form-label">Cash Receive</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="text" class="form-control" id="CashReceive" placeholder="CashReceive" name="cashReceive">-->
                                                    <input type="text" class="form-control" id="CashReceive" onBlur="formatCurrency(this, '', 'blur');"
                                                           onkeyup="formatCurrency(this, '');" placeholder="VND #,###.00" name="cashReceive" />
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="CashBack" class="col-sm-3 col-form-label">CashBack</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="text" class="form-control" id="CashBack" placeholder="CashBack" name="cashBack">-->
                                                    <input type="text" class="form-control" id="CashBack" onBlur="formatCurrency(this, '', 'blur');"
                                                           onkeyup="formatCurrency(this, '');" placeholder="VND #,###.00" name="cashBack" />
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Address" class="col-sm-3 col-form-label">Address</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Address" placeholder="Address" name="address">
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Add</button>
                                            <button type="button" class="btn btn-dark" onclick="window.history.back()">Cancel</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:../../partials/_footer.jsp -->
                    <jsp:include page="../partials/_footer.jsp"></jsp:include>
                    <!-- partial -->
                </div>
                <!-- main-panel ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="admin/assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="admin/assets/vendors/select2/select2.min.js"></script>
        <script src="admin/assets/vendors/typeahead.js/typeahead.bundle.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="admin/assets/js/off-canvas.js"></script>
        <script src="admin/assets/js/hoverable-collapse.js"></script>
        <script src="admin/assets/js/misc.js"></script>
        <script src="admin/assets/js/settings.js"></script>
        <script src="admin/assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="admin/assets/js/file-upload.js"></script>
        <script src="admin/assets/js/typeahead.js"></script>
        <script src="admin/assets/js/select2.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
