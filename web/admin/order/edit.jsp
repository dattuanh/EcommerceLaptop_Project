<%-- 
    Document   : update
    Created on : 12-May-2023, 21:37:18
    Author     : ADMIN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                            <form class="forms-sample mt-3" action="EditOrder" method="post">
                                                <input type="hidden" name="orderId" value="${order.orderId}">
                                            <div class="form-group row">
                                                <label for="OrderDate" class="col-sm-3 col-form-label">Order Date</label>
                                                <div class="col-sm-9">
                                                    <input type="date" class="form-control" id="OrderDate" placeholder="OrderDate" name="orderDate" value="${order.orderDate}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Status" class="col-sm-3 col-form-label">Status</label>
                                                <div class="col-sm-9">
                                                    <!--<input type="text" class="form-control" id="Status" placeholder="Status" name="status" value="${order.status}">-->
                                                    <div class="dropdown">
                                                        <select class="btn btn-outline-primary dropdown-toggle" id="Status" name="status" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <c:forEach var="st" items="${slist}">                                                                                                                          
                                                                <option class="dropdown-item" value="${st.orderStatusId}" ${order.status.orderStatusId == st.orderStatusId ? "selected":""}>${st.orderStatusName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="PaymentMethod" class="col-sm-3 col-form-label">Payment Method</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="PaymentMethod" placeholder="PaymentMethod" name="paymentMethod" value="${order.paymentMethod}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="CashReceive" class="col-sm-3 col-form-label">Cash Receive</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="CashReceive" placeholder="CashReceive" name="cashReceive" value="${order.cashReceive}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="CashBack" class="col-sm-3 col-form-label">Cash Back</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="CashBack" placeholder="CashBack" name="cashBack" value="${order.cashBack}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Address" class="col-sm-3 col-form-label">Address</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Address" placeholder="Address" name="address" value="${order.address}">
                                                </div>
                                            </div>

                                            <button type="submit" class="btn btn-primary mr-2">Edit</button>
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
