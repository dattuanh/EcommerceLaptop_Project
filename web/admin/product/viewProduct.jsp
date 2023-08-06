<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Product Management</title>
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
                                                <h4 class="card-title col-10">Series: ${selectedSerie.productSeriName}</h4>
                                        </div>
                                        <form class="forms-sample mt-3" action="ViewProduct" method="post">   
                                            <input type="hidden" class="form-control" id="productID" name="productID" value="${selectedProduct.productId}">
                                            <input type="hidden" class="form-control" id="serie" name="serie" value="${selectedSerie.productSeriId}">
                                            <div class="form-group row">
                                                <label for="ProductID" class="col-sm-3 col-form-label">Product ID: </label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedProduct.productId}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="ProductName" class="col-sm-3 col-form-label">Product Name: </label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.productSeriName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Status" class="col-sm-3 col-form-label">Status</label>
                                                <div class="col-sm-9">
                                                    <!--                                                    <span class="form-control"></span>-->
                                                    <c:if test="${selectedProduct.status == false}"> 
                                                        <input type="radio" name="status" value="false" checked="checked"> Available </td>
                                                    </c:if>
                                                    <c:if test="${selectedProduct.status == true}">
                                                        <input type="radio" name="status" value="true" checked="checked"> Sold
                                                    </c:if>
                                                </div>
                                            </div>
                                                    <%-- <c:if test="${selectedProduct.status == true}">
                                                <div class="form-group row">
                                                    <label for="ProductName" class="col-sm-3 col-form-label">Warranty status</label>
                                                    <div class="col-sm-9">
                                                        <span class="form-control">${selectedProduct.warranty.status}</span>
                                                    </div>
                                                </div>
                                                    </c:if> --%>
                                            <div class="form-group row">
                                                <label for="ProductName" class="col-sm-3 col-form-label">Batch Serial </label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedProduct.batchSerial}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="CreatedDate" class="col-sm-3 col-form-label">Created Date</label>
                                                <div class="col-sm-3">
                                                    <span class="form-control">${selectedProduct.createdDate}</span>
                                                </div>
                                                <label for="CreatedDate" class="col-sm-3 col-form-label">Created By</label>
                                                <div class="col-sm-3">
                                                    <span class="form-control">${selectedProduct.createdBy.userName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="LastModified" class="col-sm-3 col-form-label">Last Modified</label>
                                                <div class="col-sm-3">
                                                    <span class="form-control">${selectedProduct.modifiedDate}</span>
                                                </div>
                                                <label for="LastModified" class="col-sm-3 col-form-label">Last Modified By</label>
                                                <div class="col-sm-3">
                                                    <span class="form-control">${selectedProduct.modifiedBy.userName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="LastModified" class="col-sm-3 col-form-label"> Modified History</label>
                                                <div class="col-sm-9">
                                                    <span style="height: 100px;"  class="form-control">${selectedProduct.modifiedHistory}</span>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Update</button>
                                            <a href="ViewSerie?serie=${selectedSerie.productSeriId}">
                                                <button type="button" class="btn btn-dark">Cancel</button>
                                            </a>
                                        </form>
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
