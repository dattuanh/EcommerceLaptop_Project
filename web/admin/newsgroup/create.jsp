<%-- 
    Document   : Create
    Created on : May 12, 2023, 2:35:21 PM
    Author     : Giang Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>New News Group</title>
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
        <style>
            body {
                background-image: url("https://st.depositphotos.com/1032463/1373/i/950/depositphotos_13732950-stock-photo-background-of-old-vintage-newspapers.jpg");
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
        <style>
            input[type="text"], input[type="email"] {
                background-color: white;
                color: black;
            }
        </style>
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
                                <div class="col-lg-6 grid-margin">
                                    <div class="card card-primary">
                                        <div class="card-body">
                                            <h4 class="card-title">New News Group</h4>
                                            <form class="forms-sample mt-3" action="CreateNewsGroup" method="post">
                                                <div class="form-group">
                                                    <label for="newSGroupName">News Group</label>
                                                    <input type="text" class="form-control" id="newSGroupName" placeholder="Tiêu đề mới" name="name">
                                                </div>
                                                <!-- Thay đổi lớp CSS của nút "Submit" thành "btn-submit" -->
                                                <button type="submit" class="btn btn-success mr-2" style="background-color: #0d6efd;">Submit</button>
                                                <a href="NewsGroupManagement">
                                                    <button type="button" class="btn btn-danger" style="background-color: #808080;">Cancel</button>
                                                </a>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 grid-margin">
                                    <div class="card card-success">
                                        <div class="card-body">
                                            <h4 class="card-title">Existing News Groups</h4>
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>News Group ID</th>
                                                            <th>News Group Name</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${newsGroupList}" var="d">
                                                        <tr>
                                                            <td>${d.newsGroupID}</td>
                                                            <td>${d.newsGroupName}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
