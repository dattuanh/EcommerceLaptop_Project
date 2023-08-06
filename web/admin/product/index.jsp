<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function searchSeri() {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                $.ajax({
                    url: "SeriesManagement",
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
                    url: "SeriesManagement",
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
                    searchSeri();
                });

                $("#filter").on("change", function () {
                    searchSeri();
                });

                $('button[name="pageLink"]').click(function () {
                    var pageIndex = $(this).val();
                    searchPage(pageIndex);
                });

                $("#entry").on("change", function () {
                    searchSeri();
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
                                                <h4 class="card-title col-10">Series List</h4>
                                                <ul class="navbar-nav w-100">
                                                    <li class="nav-item w-100">
                                                        <div class="nav-link mt-2 mt-md-0 d-none d-lg-flex search">
                                                            <div class="col-md-2" style="line-height: unset;">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled" style="line-height: unset;">Remove Series</button>
                                                            </div>
                                                            <div class="col-md-1">
                                                                <select class="btn btn-success btn-rounded form-control" id="entry" name="entry">
                                                                    <option hidden="" value="9" selected></option>
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
                                                                <option value="Name"> Name </option>
                                                                <option value="Price"> Price</option>
                                                                <option value="Manufacturer"> Manufacturer</option>
                                                                <option value="WarrantyTime"> WarrantyTime</option>
                                                                <option value="Size"> Size</option>
                                                                <option value="Color"> Color</option>
                                                                <option value="Quantity"> Quantity</option>
                                                                <option value="Removed"> Removed Series</option>
                                                            </select>
                                                        </div>
                                                        <i class="mdi mdi-magnify" style="font-size: 22px"></i>
                                                        <div class="col-md-4">
                                                            <input type="text" class="form-control" id="search" name="search" placeholder="Search Series" value="${search}">

                                                        </div>
                                                        <a type="submit" href="SeriesManagement" class="btn btn-light w-20" style="line-height: unset; margin-right: 25px;">
                                                            <i class="mdi mdi-reload"></i>
                                                        </a>
                                                        <div class="col-md-1">
                                                            <a href="CreateSerie">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw" style="line-height: unset;" >
                                                                    New Serie
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- form cho submit xóa tất cả check box -->
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="RemoveSerie">
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
                                                            <!--<th> Image </th>-->
                                                            <th> Series Name </th>
                                                            <th> Price </th>
                                                            <th> Category </th>
                                                            <th> Warranty Time </th>
                                                            <th> Size </th>
                                                            <th> Color </th>
                                                            <th> Stock </th>
                                                            <th> Last Modified </th>
                                                            <th> Actions </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${serieList}" var="s">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${s.productSeriId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <!--<td> ${s.imagePreview} </td>-->
                                                                <td>
                                                                    ${fn:substring(s.productSeriName, 0, 25)}
                                                                </td>
                                                                <td> 
                                                                    <fmt:formatNumber value = "${s.price}" type = "number"/>
                                                                </td>
                                                                <td> ${s.categoryId.categoryName} </td>
                                                                <td> ${s.warrantyTime} </td>
                                                                <td> ${s.size} </td>
                                                                <td> ${s.color.colorName} </td>
                                                                <td> ${s.stock} </td>
                                                                <td>
                                                                    <fmt:formatDate value="${s.modifiedDate}" pattern="dd-MM-yyyy" />
                                                                </td>
                                                                <td>
<!--                                                                    <a href="ViewSerie?serie=${s.productSeriId}">
                                                                        <i class="mdi mdi-eye" style="font-size: 22px"></i> &nbsp;
                                                                    </a>-->
                                                                    <c:if test="${s.isDelete == false}" >
                                                                        <a href="UpdateSerie?serie=${s.productSeriId}">
                                                                            <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i> &nbsp;
                                                                        </a>

                                                                        &nbsp;
                                                                        <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                           onclick="confirmDelete('RemoveSerie', ${s.productSeriId})">
                                                                        </i>
                                                                    </c:if>
                                                                    <c:if test="${s.isDelete == true}" >
                                                                        <a href="RestoreSerie?serie=${s.productSeriId}">
                                                                            <i class="mdi mdi-restore" style="font-size: 22px"></i> &nbsp;
                                                                        </a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    <td colspan="10">
                                                        <nav aria-label="Page navigation">
                                                            <p> There are ${serieList.size()} / ${listSize} Products</p>
                                                            <ul class="pagination justify-content-end">
                                                                <li class="page-item ${currentPage > 1 ? "active" : "disabled"}">
                                                                    <button type="button" class="page-link" name="pageLink" id="pageLink" value="${currentPage-1}" aria-label="Previous">
                                                                        <span aria-hidden="true">&laquo;</span>
                                                                        <span class="sr-only">Previous</span>
                                                                    </button>
                                                                </li>
                                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                                    <li class="page-item ${currentPage == i ? "active" : ""}">
                                                                        <button type="button" class="page-link" name="pageLink" id="pageLink" value="${i}">${i}</button>
                                                                    </li>
                                                                </c:forEach>
                                                                <li class="page-item ${currentPage < endPage ? "active" : "disabled"}">
                                                                    <button type="button" class="page-link" name="pageLink" id="pageLink" value="${currentPage+1}" aria-label="Next">
                                                                        <span aria-hidden="true">&raquo;</span>
                                                                        <span class="sr-only">Next</span>
                                                                    </button>
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
        <script src="admin/assets/vendors/checkbox.js/checkbox.min.js"></script> <!-- import checkbox js -->
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
        <script src="admin/assets/shared/js/delete-confirm.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
