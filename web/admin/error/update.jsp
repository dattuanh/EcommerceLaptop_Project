<%-- 
    Document   : update
    Created on : May 13, 2023, 11:27:49 PM
    Author     : Giang Minh
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Account</title>
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
        <jsp:include page="../assets/shared/jsp/tinymce.jsp" />
        <script>
            tinymcee('../../web/images/errors/');
        </script>
        <style>
            span input {
                color: black
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
                        <div class="content-wrapper">
                            <div class="row">
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <form class="forms-sample mt-3 row" action="UpdateErrors?id=${e.errorId}" method="post">
                                            <div class="col-sm-8">
                                                <div class="form-group row">
                                                    <label for="errorMessage" class="col-form-label">Nội dung lỗi</label>
                                                    <textarea id="editor" name="content" >${e.content}</textarea>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="form-group row">
                                                    <label for="errorsMessage" class="col-form-label col-sm-12">Tên lỗi</label>
                                                    <div class="col-sm-12">
                                                        <input type="text" class="form-control" id="errorsMessage" 
                                                               placeholder="Tên lỗi" name="errorsMessage" value="${e.errorMessage}">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <label for="RepairTime" class="col-form-label col-sm-12">Thời gian sửa chữa dự kiến</label>
                                                    <div class="col-sm-12">
                                                        <input type="date" class="form-control" id="RepairTime" 
                                                               placeholder="Thời gian sửa chữa dự kiến" name="repairTime" 
                                                               value="${e.repairDate}">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <label for="RepairProduct" class="col-form-label col-sm-12">Sản phẩm cần sửa</label>

                                                    <div class="col-sm-12">
                                                        <select class="js-example-basic-single" style="width:100%" name="repairProduct">
                                                            <c:forEach var = "p" items="${products}">
                                                                <option value="${p.productId}">
                                                                    Mã: ${p.productId},Name: ${fn:substring(p.productSerieId.productSeriName, 0, 25)}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <%@ page import="admin.controller.error.RepairStatusEnum" %>

                                                <div class="form-group row">
                                                    <label for="RepairPrice" class="col-form-label col-sm-6">Phí sửa chữa</label>
                                                    <label for="RepairPrice" class="col-form-label col-sm-6">Trạng thái sửa chữa</label>

                                                    <div class="col-sm-6">
                                                        <jsp:include page="../Util/Format.jsp"></jsp:include>
                                                            <script>
                                                            </script>
                                                            <input type="text" class="form-control" id="RepairPrice" placeholder="Phí sửa chữa" 
                                                                   name="repairPrice"  onBlur="formatCurrency(this, '', 'blur');"
                                                                   onkeyup="formatCurrency(this, '');" placeholder="VND #,###"
                                                                   value="<fmt:formatNumber value = "${e.money}" type = "number"/>">
                                                    </div>

                                                    <div class="col-sm-6">
                                                        <select class="js-example-basic-single" style="width:100%" name="repairStatus">
                                                            <%
                                                                int j = 1;
                                                            %>
                                                            <c:forEach var = "i" begin = "1" end = "4">
                                                                <option value="${i}" ${e.repairStatus == i ? "selected" : ""}
                                                                        ><%=(String) RepairStatusEnum.getRepairStatusString(j++)%></option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <button type="submit" class="btn btn-primary mr-2">Confirm Update</button>
                                            <a href="ErrorsManagement">
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
