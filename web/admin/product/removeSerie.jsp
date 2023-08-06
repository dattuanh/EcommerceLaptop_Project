
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                                        <form class="forms-sample mt-3" action="RemoveSerie" method="post">   
                                            <input type="hidden" class="form-control" id="SerieID" name="SerieID" value="${selectedSerie.productSeriId}">
                                            <div class="form-group row">
                                                <label for="SerieName" class="col-sm-3 col-form-label">Serie Name: </label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.productSeriName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Price" class="col-sm-3 col-form-label">Price: </label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">
                                                        <fmt:formatNumber value = "${selectedSerie.price}" type = "number"/>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Description" class="col-sm-3 col-form-label">Description: </label>
                                                <div class="col-sm-9">
                                                    <!--                                                    <input type="text" class="form-control" id="Description" placeholder="Description" name="Description">-->
                                                    <span class="form-control">${selectedSerie.getDescription()}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Image" class="col-sm-3 col-form-label">Image</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.imagePreview}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Category" class="col-sm-3 col-form-label">Category</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.categoryId.categoryName}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Manufacturer" class="col-sm-3 col-form-label">Manufacturer</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.manufacturer}</span>
                                                </div>
                                            </div>    
                                            <div class="form-group row">
                                                <label for="Stock" class="col-sm-3 col-form-label">Stock</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.stock}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="WarrantyTime" class="col-sm-3 col-form-label">WarrantyTime</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.warrantyTime}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Size" class="col-sm-3 col-form-label">Size</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.size}</span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Color" class="col-sm-3 col-form-label">Color</label>
                                                <div class="col-sm-9">
                                                    <span class="form-control">${selectedSerie.color.colorName}</span>
                                                </div>
                                            </div>
                                            <input type="hidden" name="modifiedHistory" value="${selectedSerie.modifiedHistory}" />
                                            <button type="submit" class="btn btn-primary mr-2">Remove</button>
                                            <a href="SeriesManagement">
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
