<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Category Management</title>
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
            function searchCategory() {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                $.ajax({
                    url: "CategoryManagement",
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
                    url: "CategoryManagement",
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
                    searchCategory();
                });

                $("#filter").on("change", function () {
                    searchCategory();
                });

                $('button[name="pageLink"]').click(function () {
                    var pageIndex = $(this).val();
                    searchPage(pageIndex);
                });

                $("#entry").on("change", function () {
                    searchCategory();
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
                                                <h4 class="card-title col-10">Category List</h4>
                                                <ul class="navbar-nav w-100">
                                                    <li class="nav-item w-100">
                                                        <div class="nav-link mt-2 mt-md-0 d-none d-lg-flex search">
                                                            <div class="col-md-2" style="line-height: unset;">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled" style="line-height: unset;">Remove Category</button>
                                                            </div>
                                                            <div class="col-md-1">
                                                                <select class="btn btn-success btn-rounded form-control" id="entry" name="entry">
                                                                    <option hidden="" value="5" selected></option>
                                                                    <option value="2" >2</option>
                                                                    <option value="5" >5</option>
                                                                    <option value="10" >10</option>
                                                                    <option value="50" >50</option>
                                                                    <option value="100">100</option>
                                                                </select>
                                                            </div>
                                                            <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                                                            <div class="col-md-2">
                                                                <select class="form-control" id="filter" name="filter" required >
                                                                    <option value="All" selected> All </option>
                                                                    <option value="Name"> Name </option>
                                                                    <option value="Quantity"> Quantity of Product</option>
                                                                </select>
                                                            </div>
                                                            <i class="mdi mdi-magnify" style="font-size: 22px"></i>
                                                            <div class="col-md-4">
                                                                <input type="text" class="form-control" id="search" name="search" placeholder="Search Category" value="${search}">

                                                        </div>
                                                        <a type="submit" href="SeriesManagement" class="btn btn-light w-20" style="line-height: unset; margin-right: 25px;">
                                                            <i class="mdi mdi-reload"></i>
                                                        </a>
                                                        <div class="col-md-1">
                                                            <a href="CreateCategory">
                                                                <button type="button" class="btn btn-primary btn-rounded btn-fw" style="line-height: unset;">New Category</button>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- form cho submit xóa tất cả check box -->
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="RemoveCategory">
                                            <div class="table-responsive">
                                                <table id="content" class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>
                                                                <div class="form-check form-check-muted m-0">
                                                                    <label class="form-check-label">
                                                                        <input type="checkbox" class="form-check-input" id="checkbox-all">
                                                                    </label>
                                                                </div>
                                                            </th>
                                                            <th> Category Name </th>
                                                            <th> Numbers of Products </th>
                                                            <th> Actions </th>
                                                            <!--                                                            <th> Payment Mode </th>
                                                                                                                        <th> Start Date </th>
                                                                                                                        <th> Payment Status </th>-->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${categoryList}" var="c">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${c.getCategoryID()}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td> ${c.getCategoryName()} </td>
                                                                <td> ${c.getProductQuantity()} </td>
                                                                <td>
                                                                    <!--<div class="badge badge-outline-success">Approved</div>-->
                                                                    <a href="ViewCategory?CategoryID=${c.categoryID}">
                                                                        <i class="mdi mdi-eye" style="font-size: 22px"></i> &nbsp;
                                                                    </a>
                                                                    <a href="UpdateCategory?CategoryID=${c.categoryID}">
                                                                        <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i> &nbsp;
                                                                    </a>
                                                                    <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                       onclick="confirmDelete('RemoveCategory', ${c.categoryID})">
                                                                    </i>&nbsp;
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    <td colspan="10">
                                                        <nav aria-label="Page navigation">
                                                            <p> There are ${categoryList.size()} / ${listSize} Category</p>
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
        <script src="admin/assets/js/dashboard.js"></script>
        <script src="admin/assets/shared/js/delete-confirm.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
