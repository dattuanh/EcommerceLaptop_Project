
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quản lý sản phẩm</title>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function searchProduct() {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                $.ajax({
                    url: "ProductManagement",
                    data: {search: search, filter: filter, entry: entry},
                    type: "POST",
                    success: function (data) {
                        var searchList = document.getElementById('content');
                        searchList.innerHTML = data;
                        document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());
                        document.querySelector('.check-all-submit-btn').classList.add('disabled');
                    }
                });
            }

            function searchPage(index) {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                $.ajax({
                    url: "ProductManagement",
                    data: {search: search, filter: filter, index: index, entry: entry},
                    type: "POST",
                    success: function (data) {
                        var searchList = document.getElementById('content');
                        searchList.innerHTML = data;
                        document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());
                        document.querySelector('.check-all-submit-btn').classList.add('disabled');
                    }
                });
            }

            $(document).ready(function () {
                $("#search").on("keyup", function () {
                    searchProduct();
                });

                $("#filter").on("change", function () {
                    searchProduct();
                });

                $('button[name="pageLink"]').click(function () {
                    var pageIndex = $(this).val();
                    searchPage(pageIndex);
                });

                $("#entry").on("change", function () {
                    searchProduct();
                });
            });
        </script>
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
                                                <h4 class="card-title col-sm-10">Product List</h4>
                                                <ul class="navbar-nav w-100">
                                                    <li class="nav-item w-100">
                                                        <div class="nav-link mt-2 mt-md-0 d-none d-lg-flex search">
                                                            <div class="col-md-2" style="line-height: unset;">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled" style="line-height: unset;">Remove Product</button>
                                                            </div>
                                                            <div class="col-md-1">
                                                                <select class="btn btn-success btn-rounded form-control" id="entry" name="entry">
                                                                    <option hidden="" value="10" selected></option>
                                                                    <option value="2" ${entries == 2 ? 'selected' : ''}>2</option>
                                                                <option value="5" ${entries == 5 ? 'selected' : ''}>5</option>
                                                                <option value="10" ${entries == 10 ? 'selected' : ''}>10</option>
                                                                <option value="50" ${entries == 50 ? 'selected' : ''}>50</option>
                                                                <option value="100" ${entries == 100 ? 'selected' : ''}>100</option>
                                                            </select>
                                                        </div>
                                                        <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                                                        <div class="col-md-2">
                                                            <select class="form-control" id="filter" name="filter" required >
                                                                <c:if test="${not empty filter }" >
                                                                    <option value="${filter}" selected hidden>${filter}</option>
                                                                </c:if>
                                                                <option value="All"> All </option>
                                                                <option value="ID"> ID </option>
                                                                <option value="Batch"> Batch </option>
                                                                <option value="Sold"> Sold</option>
                                                                <option value="Available"> Available</option>
                                                                <option value="ActivatedWarranty"> Activated Warranty</option>
                                                                <option value="OnWarranty"> On Warranty</option>
                                                                <option value="NotActivatedWarranty">Not Activated Warranty</option>
                                                                <option value="ExpiredWarranty"> Expired Warranty</option>
                                                            </select>
                                                        </div>
                                                        <i class="mdi mdi-magnify" style="font-size: 22px"></i>
                                                        <div class="col-md-4">
                                                            <input type="text" class="form-control" id="search" name="search" placeholder="Search Products" value="${search}">
                                                        </div>
                                                        <a type="submit" href="ProductManagement" class="btn btn-light w-20" style="line-height: unset; margin-right: 25px;">
                                                            <i class="mdi mdi-reload"></i>
                                                        </a>
                                                        <div class="col-md-1">
                                                            <a href="AddProduct">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw" style="line-height: unset;" >
                                                                    Add Product
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>     
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="RemoveProduct">
                                            <div class="table-responsive">
                                                <table id="content" class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>
                                                                <div class="form-check form-check-muted m-0">
                                                                    <label class="form-check-label" for="checkbox-all">
                                                                        <input type="checkbox" class="form-check-input" id="checkbox-all">
                                                                    </label>
                                                                </div>
                                                            </th>
                                                            </th>
                                                            <th> Series Name </th>
                                                            <th> Sold </th>
                                                            <th> Batch Serial </th>
                                                            <th> Warranty </th>
                                                            <th> Action </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${productList}" var="p">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${p.productId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    ${fn:substring(p.productSerieId.productSeriName, 0, 25)}
                                                                </td>
                                                                <td> ${p.status} </td>
                                                                <td> ${p.batchSerial} </td>

                                                                <td> 
                                                                    <c:if test="${empty p.warranty.status}">
                                                                        <span class="badge badge-dark">Not Activated</span>
                                                                    </c:if>
                                                                        
                                                                    <c:if test="${not empty p.warranty.status}">
                                                                        <c:if test="${p.warranty.status == true}">
                                                                            <span class="badge badge-danger">On Warranty</span>
                                                                        </c:if>
                                                                        <c:if test="${p.warranty.status == false}">
                                                                            <span class="badge badge-success">Expired Warranty</span>
                                                                        </c:if>
                                                                    </c:if>
                                                                </td>

                                                                <td>
                                                                    <a href="ViewProduct?serie=${selectedSerie.productSeriId}&&productID=${p.productId}">
                                                                        <i class="mdi mdi-eye" style="font-size: 22px"></i> &nbsp;
                                                                    </a>
                                                                    <a href="UpdateProduct?serie=${selectedSerie.productSeriId}&&productID=${p.productId}">
                                                                        <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i> &nbsp;
                                                                    </a>  &nbsp;
                                                                    <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                       onclick="confirmDelete('RemoveProduct', '${selectedSerie.productSeriId}&productID=${p.productId}')">
                                                                    </i> &nbsp;
                                                                </td>

                                                            </tr>
                                                        </c:forEach>
                                                    <td colspan="10">
                                                        <nav aria-label="Page navigation">
                                                            <p> There are ${productList.size()} / ${listSize} Series</p>
                                                            <ul class="pagination justify-content-end">
                                                                <li class="page-item ${currentPage > 1 ? "" : "disabled"}">
                                                                    <a class="page-link" href="${function}?index=${currentPage - 1}&entries=${entries}" aria-label="Previous">
                                                                        <span aria-hidden="true">&laquo;</span>
                                                                        <span class="sr-only">Previous</span>
                                                                    </a>
                                                                </li>
                                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                                    <li class="page-item ${currentPage == i ? "active" : ""} ">
                                                                        <a class="page-link" href="${function}?index=${i}&entries=${entries}">${i}
                                                                        </a>
                                                                    </li>
                                                                </c:forEach>
                                                                <li class="page-item ${currentPage < endPage ? "" : "disabled"}">
                                                                    <a class="page-link" href="${function}?index=${currentPage + 1}&entries=${entries}" aria-label="Next">
                                                                        <span aria-hidden="true">&raquo;</span>
                                                                        <span class="sr-only">Next</span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </nav>
                                                    </td>
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
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="admin/assets/js/off-canvas.js"></script>
        <script src="admin/assets/js/hoverable-collapse.js"></script>
        <script src="admin/assets/js/misc.js"></script>
        <script src="admin/assets/js/settings.js"></script>
        <script src="admin/assets/js/todolist.js"></script>
        <script src="admin/assets/vendors/checkbox.js/checkbox.min.js"></script> <!-- import checkbox js -->
        <script src="admin/assets/shared/js/delete-confirm.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="admin/assets/js/dashboard.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
