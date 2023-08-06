<%-- 
    Document   : edit
    Created on : May 12, 2023, 11:10:14 PM
    Author     : Giang Minh
--%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>New NewsGroup</title>
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
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <form class="forms-sample mt-3" action="UpdateNewsGroup?id=${c.newsGroupID}" method="post">
                                            <div class="form-group row">
                                                <label for="newsGroupID" class="col-sm-3 col-form-label">ID</label>
                                                <div class="col-sm-9">
                                                    <input type="hidden" class="" id="newsGroupID" placeholder="newsGroupID" name="id" value="${c.newsGroupID}">
                                                    <span class="">${c.newsGroupID}</span>
                                                </div>
                                            </div>
                                                <p style="border-bottom: 1px solid white;"></p>
                                            <div class="form-group row">
                                                <label for="CreatedDate" class="col-sm-3 col-form-label">CreatedDate</label>
                                                <div class="col-sm-9">
                                                    <input type="hidden" class="" id="CreatedDate" placeholder="CreatedDate" name="createdDate" value="${c.createdDate}">
                                                    <span class="">
                                                        <fmt:formatDate value="${c.createdDate}" pattern="dd-MM-yyyy" />
                                                    </span>
                                                </div>
                                            </div>
                                                    <p style="border-bottom: 1px solid white;"></p>
                                            <div class="form-group row">
                                                <label for="CreatedBy" class="col-sm-3 col-form-label">CreatedBy</label>
                                                <div class="col-sm-9">
                                                    <span class="">${c.createdBy.firstName} ${c.createdBy.lastName}</span>
                                                </div>
                                            </div>
                                                <p style="border-bottom: 1px solid white;"></p>
                                            <div class="form-group row">
                                                <label for="CreatedBy" class="col-sm-3 col-form-label">Old Name</label>
                                                <div class="col-sm-9">
                                                    <span class="">${c.newsGroupName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="newsGroupName" class="col-sm-3 col-form-label">New Name</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="newsGroupName" placeholder="News Group Name" name="newsGroupName">
                                                </div>
                                            </div>
                                            <input type="hidden" name="createdBy" value="${c.createdBy}"/>
                                            <input type="hidden" name="modifiedHistory" value="${c.modifiedHistory}"/>
                                            <button type="submit" class="btn btn-primary mr-2">Confirm Update</button>
                                            <a href="NewsGroupManagement">
                                                <button type="button" class="btn btn-dark">Cancel</button>
                                            </a>
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