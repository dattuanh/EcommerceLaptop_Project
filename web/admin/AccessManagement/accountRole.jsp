<%-- 
    Document   : role
    Created on : May 19, 2023, 4:25:03 PM
    Author     : Giang Minh
--%>

<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Roles Management</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <link rel="stylesheet" href="assets/vendors/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/vendors/owl-carousel-2/owl.theme.default.min.css">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="assets/images/favicon.png" />
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
                                                <h4 class="card-title col-10">Role List</h4>
                                                <a href="CreateAccount">
                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn">Confirm</button>
                                                </a>
                                            </div>
                                            <!-- form cho submit xóa tất cả check box -->
                                            <form id="check" class="" name="checkAll-form" method="post" action="AccountRolesManagement?id=${param.id}">
                                                <div class="table-responsive">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label" for="checkbox-all">
                                                                            <input type="checkbox" class="form-check-input" id="checkbox-all">
                                                                        </label>
                                                                    </div>
                                                                </th>
                                                                <th> Id </th>
                                                                <th> Name </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${adminRoles}" var="ar">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" ${ar.status ? "checked" : ""}  name="roleIds[]" value="${ar.roleId.roleId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td> ${ar.roleId.roleId} </td>
                                                                <td> ${ar.roleId.roleName} </td>
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
        <script src="assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <script src="assets/vendors/chart.js/Chart.min.js"></script>
        <script src="assets/vendors/progressbar.js/progressbar.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
        <script src="assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <script src="assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
        
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var checkAllForm = document.forms['checkAll-form'];
                var checkboxAll = document.querySelector('#checkbox-all');
                var orderIdCheckboxes = document.querySelectorAll('input[name="roleIds[]"]');
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
                        var isCheckedAll = document.querySelectorAll('input[name="roleIds[]"]:checked').length === orderIdCheckboxes.length;
                        checkboxAll.checked = isCheckedAll;
                        renderCheckAllSubmitBtn();
                    });
                }
                // Check all submit button clicked
                checkAllSubmitBtn.addEventListener('click', function (e) {
                    e.preventDefault();
                    var isSubmittable = !this.classList.contains('disabled');
                    if (isSubmittable) {
                        if(confirm("Chắc chưa!!!!")){
                            checkAllForm.submit();
                        } else{
                            console.log("cancel Submit");
                        }
                    }
                });
                // Re-render check all submit button
                function renderCheckAllSubmitBtn() {
                    var checkedCount = document.querySelectorAll('input[name="roleIds[]"]:checked').length;
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
        <script src="assets/js/off-canvas.js"></script>
        <script src="assets/js/hoverable-collapse.js"></script>
        <script src="assets/js/misc.js"></script>
        <script src="assets/js/settings.js"></script>
        <script src="assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="assets/js/dashboard.js"></script>
    </body>
</html>
