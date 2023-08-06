<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index.jsp
    Created on : 12-May-2023, 21:35:59
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Order Management</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="admin/assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
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
    </head>
    <body>
        <div class="container-scroller">
            <!-- partial:partials/_sidebar.jsp -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper">
                    <!-- partial:partials/_navbar.jsp -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <!-- partial -->
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="row ">
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <h4 class="card-title col-3">Order List</h4>
                                                <ul class="navbar-nav w-100">
                                                    <li class="nav-item w-100">
                                                        <form class="nav-link mt-2 mt-md-0 d-none d-lg-flex search" action="SearchOrder" method="post">
                                                            <div class="col-md-4">
                                                                <a href="">
                                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled">Restore Order</button>
                                                                </a>
                                                            </div>
                                                            <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                                                            <div class="col-md-3">
                                                                <select class="form-control" id="list" name="filter" required >
                                                                <c:if test="${not empty filter }" >
                                                                    <option value="${filter}" selected hidden>${filter}</option>
                                                                </c:if>
                                                                <option value="All"> All </option>
                                                                <option value="ID"> ID </option>
                                                                <option value="Status"> Status</option>
                                                            </select>
                                                        </div>
                                                        <i class="mdi mdi-magnify" style="font-size: 22px"></i>
                                                        <div class="col-md-3">
                                                            <input type="text" class="form-control" name="search" placeholder="Search Order" value="${search}" autofocus>
                                                        </div>
                                                        <div class="col-md-1">
                                                            <a href="javascript:history.back()">
                                                                <div class="btn btn-primary"><i class="mdi mdi-arrow-left"></i></div>
                                                            </a>
                                                        </div>
                                                    </form>
                                                </li>
                                            </ul>

                                        </div>
                                        <!-- form cho submit xóa tất cả check box -->
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="CloseOrder">
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>
                                                                <div class="form-check form-check-muted m-0">
                                                                    <label class="form-check-label" for="checkbox-all">
                                                                        <input type="checkbox" class="form-check-input" value="" id="checkbox-all">
                                                                    </label>
                                                                </div>
                                                            </th>
                                                            <!--<th> OrderID </th>-->
                                                            <th> Customer Name </th>
                                                            <th> Order Date </th>
                                                            <th> Total Price </th>
                                                            <th> Status </th>
                                                            <th> Payment Date </th>
                                                            <th> Payment Method </th>
                                                            <th> Actions </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="o" items="${olist}">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="orderIds[]" value="${o.orderId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <!--<td> ${o.orderId} </td>-->
                                                                <td> ${o.customer.userName} </td>
                                                                <td> 
                                                                    <fmt:formatDate value="${o.orderDate}" pattern="dd-MM-yyyy" />
                                                                </td>
                                                                <td>
                                                                    <fmt:formatNumber value="${o.totalPrice}" type="number" />
                                                                </td>
                                                                <td>
                                                                    <c:if test="${o.status.orderStatusName eq 'Approved'}">
                                                                        <div class="badge badge-outline-success">
                                                                            ${o.status.orderStatusName}
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${o.status.orderStatusName eq 'Pending'}">
                                                                        <div class="badge badge-outline-warning">
                                                                            ${o.status.orderStatusName}
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${o.status.orderStatusName eq 'Rejected'}">
                                                                        <div class="badge badge-outline-danger">
                                                                            ${o.status.orderStatusName}
                                                                        </div>
                                                                    </c:if>  
                                                                    <c:if test="${o.status.orderStatusName eq 'Closed'}">
                                                                        <div class="badge badge-outline-info">
                                                                            ${o.status.orderStatusName}
                                                                        </div>
                                                                    </c:if>  
                                                                </td>
                                                                <td> 
                                                                    <fmt:formatDate value="${o.paymentDate}" pattern="dd-MM-yyyy" />
                                                                </td>
                                                                <td> ${o.paymentMethod} </td>
                                                                <td>
                                                                    <a href="CloseOrder?orderId=${o.orderId}&restore=1">
                                                                        <i class="mdi mdi-restore" style="font-size: 22px"></i> 
                                                                    </a>
                                                                    &nbsp;
                                                                </td>
                                                            </tr>
                                                        </c:forEach>  

                                                    </tbody>
                                                </table>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:partials/_footer.jsp -->
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
        <script src="admin/assets/vendors/chart.js/Chart.min.js"></script>
        <script src="admin/assets/vendors/progressbar.js/progressbar.min.js"></script>
        <script src="admin/assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
        <script src="admin/assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script src="admin/assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var deleteAllForm = document.forms['deleteAll-form'];
                var checkboxAll = document.querySelector('#checkbox-all');
                var orderIdCheckboxes = document.querySelectorAll('input[name="orderIds[]"]');
                var checkAllSubmitBtn = document.querySelector('.check-all-submit-btn');
                // Checkbox all clicked
                checkboxAll.addEventListener('change', function () {
                    var isCheckedAll = this.checked;
                    for (var i = 0; i < orderIdCheckboxes.length; i++) {
                        orderIdCheckboxes[i].checked = isCheckedAll;
                    }
                    renderCheckAllSubmitBtn();
                });
                // Order item checkbox changed
                for (var i = 0; i < orderIdCheckboxes.length; i++) {
                    orderIdCheckboxes[i].addEventListener('change', function () {
                        var isCheckedAll = document.querySelectorAll('input[name="orderIds[]"]:checked').length === orderIdCheckboxes.length;
                        checkboxAll.checked = isCheckedAll;
                        renderCheckAllSubmitBtn();
                    });
                }
                // Check all submit button clicked
                checkAllSubmitBtn.addEventListener('click', function (e) {
                    e.preventDefault();
                    var isSubmittable = !this.classList.contains('disabled');
                    if (isSubmittable) {
                        if (confirm("Chắc chưa!!!!")) {
                            deleteAllForm.submit();
                        } else {
                            console.log("cancel Submit");
                        }
                    }
                });
                // Re-render check all submit button
                function renderCheckAllSubmitBtn() {
                    var checkedCount = document.querySelectorAll('input[name="orderIds[]"]:checked').length;
                    if (checkedCount > 0) {
                        checkAllSubmitBtn.classList.remove('disabled');
                    } else {
                        checkAllSubmitBtn.classList.add('disabled');
                    }
                }
            });

        </script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="admin/assets/js/off-canvas.js"></script>
        <script src="admin/assets/js/hoverable-collapse.js"></script>
        <script src="admin/assets/js/misc.js"></script>
        <script src="admin/assets/js/settings.js"></script>
        <script src="admin/assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="admin/assets/js/dashboard.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>

