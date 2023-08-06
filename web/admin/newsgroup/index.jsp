

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Customer Management</title>
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

            function search(index) {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                console.log(entry);

                $.ajax({
                    url: "NewsGroupManagement",
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
//                document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());

                $("#search").on("keyup", function () {
                    search(-1);
                });

                $("#entry").on("change", function () {
                    search(-1);
                });

                $('button[name="pageLink"]').click(function () {
                    var pageIndex = $(this).val();
                    search(pageIndex);
                });
            });

            //                $("#danhMuc").on("change", function () {
//                    var danhMuc = $("#danhMuc").val();
//                    console.log(danhMuc);
//                    document.getElementById('danhMucHidden').value = danhMuc;
//                    document.getElementById('formDanhMuc').submit();
//                });
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
                                                <div class="nav-link mt-2 mt-md-0 d-none d-lg-flex search mx-auto" >

                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled" style="margin-right: 10px" >Remove</button>
                                                    <select class="col-md-1 dropdown-toggle btn btn-success" id="entry" style="margin-right: 10px">
                                                        <option value="2" ${entries == 2 ? 'selected' : ''}>2</option>
                                                    <option value="5" ${entries == 5 ? 'selected' : ''}>5</option>
                                                    <option value="10" ${entries == 10 ? 'selected' : ''}>10</option>
                                                    <option value="50" ${entries == 50 ? 'selected' : ''}>50</option>
                                                    <option value="100" ${entries == 100 ? 'selected' : ''}>100</option>
                                                </select>
                                                <div class="col-md-2" style="display: flex;">
                                                    <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>

                                                    <select class="form-control" id="filter" name="filter" required >
                                                        <c:if test="${not empty filter }" >
                                                            <option value="${filter}" selected hidden>${filter}</option>
                                                        </c:if>
                                                        <option value="All"> All </option>
                                                        <option value="Name"> Name </option>
                                                    </select>

                                                </div>
                                                <i class="mdi mdi-magnify"" style="font-size: 22px"></i>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" id="search" name="search" placeholder="Search News Group" value="${search}" autofocus>
                                                </div>
                                                <a type="submit" href="NewsGroupManagement" class="btn btn-light col-md-1" 
                                                   style="line-height: unset; margin-right: 10px">
                                                    <i class="mdi mdi-reload"></i>
                                                </a>
                                                <a href="CreateNewsGroup" class="col-md-3">
                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw">Add News Group</button>
                                                </a>
                                            </div>
                                        </div>
                                        <!-- form cho submit xóa tất cả check box -->
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="DeleteNewsGroup">
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
                                                            <th> News Group ID </th>
                                                            <th> News Group Name </th>
                                                            <th> Actions </th>
                                                        </tr>
                                                    </thead>

                                                    <tbody id="content">
                                                        <c:forEach items="${newsGroupList}" var="d">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${d.newsGroupID}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td> ${d.newsGroupID}  </td>
                                                                <td> ${d.newsGroupName} </td>
                                                                <td>
                                                                    &nbsp;
                                                                    <a href="UpdateNewsGroup?id=${d.newsGroupID}">
                                                                        <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i>
                                                                    </a>
                                                                    &nbsp;
                                                                    <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                       onclick="confirmDelete('DeleteNewsGroup', ${d.newsGroupID})">
                                                                    </i>
                                                                </td>
                                                            </tr>
                                                    </c:forEach>
                                                        <td colspan="10"> 
                                                            <nav aria-label="Page navigation">
                                                                <p> There are  ${newsGroupList.size()} / ${listSize} group </p>
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
