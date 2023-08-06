<%-- 
    Document   : index
    Created on : May 13, 2023, 9:17:01 PM
    Author     : Giang Minh
--%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Management</title>
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

                $.ajax({
                    url: "WarrantiesManagement",
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
        </script>
    </head>
    <body>
        <jsp:useBean id="warranty" scope="request" class="Base.Dal.DaoWarranties"></jsp:useBean>
        <jsp:useBean id="customer" scope="request" class="Base.Dal.DaoCustomer"></jsp:useBean>
        <jsp:useBean id="util" scope="request" class="Base.Util.Utilities"></jsp:useBean>
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
                                                <!--<button class="btn btn-primary btn-sm check-all-submit-btn col-3 disabled">Xóa</button>-->

                                                <ul class="navbar-nav w-100">
                                                    <li class="nav-item w-100">
                                                        <form class="nav-link mt-2 mt-md-0 d-none d-lg-flex search" action="WarrantiesManagement" method="post">
                                                            <div class="col-md-3">
                                                                <a href="">
                                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled">Remove Category</button>
                                                                </a>
                                                            </div>

                                                            <select class="col-md-1 dropdown-toggle btn btn-success" id="entry" style="margin-right: 10px">
                                                                <option value="2" ${entries == 2 ? 'selected' : ''}>2</option>
                                                            <option value="5" ${entries == 5 ? 'selected' : ''}>5</option>
                                                            <option value="10" ${entries == 10 ? 'selected' : ''}>10</option>
                                                            <option value="50" ${entries == 50 ? 'selected' : ''}>50</option>
                                                            <option value="100" ${entries == 100 ? 'selected' : ''}>100</option>
                                                        </select>

                                                        <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                                                        <div class="col-md-2">
                                                            <select class="form-control" id="filter" name="filter" required >
                                                                <option value="All"> All </option>
                                                                <option value="Product"> Product </option>
                                                                <option value="Customer"> Customer </option>
                                                                <option value="Error"> Error </option>
                                                            </select>
                                                        </div>
                                                        <i class="mdi mdi-magnify"" style="font-size: 22px"></i>
                                                        <div class="col-md-3">
                                                            <input type="text" id="search" class="form-control" name="search" placeholder="Search Warranty" value="${search}" autofocus>
                                                        </div>
                                                        <a href="CreateWarranties" class="col-md-3">
                                                            <button type="button" class="btn btn-primary btn-rounded btn-fw">Add Warranties</button>
                                                        </a>
                                                    </form>
                                                </li>
                                            </ul>
                                        </div>
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="DeleteWarranties">
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
                                                            <th> Mã phiếu </th>
                                                            <th> Tên sản phẩm </th>
                                                            <th> Tên khách </th>
                                                            <th> Hóa đơn mua </th>
                                                            <th> Bảo hành đến </th>
                                                            <th> Ghi chú </th>
                                                            <th> Actions </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="content">
                                                        <c:forEach items="${warrantiesList}" var="a">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${a.warrantyId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td> 
                                                                    ${a.warrantyId}
                                                                </td>
                                                                <td>
                                                                    ${a.productId.productSerieId.productSeriName}
                                                                </td>

                                                                <td>
                                                                    <c:set var="c" scope="request" value="${customer.getCustomerByWarranty(a.warrantyId)}" />
                                                                    <a href="UpdateCustomer?id=${c.customerId}">${c.fullName}</a>
                                                                </td>

                                                                <td>
                                                                    ${warranty.getOrderByWarrantyId(a.warrantyId).orderId}
                                                                </td>

                                                                <td>
                                                                    <fmt:formatDate value="${util.plusMonth(a.warrantyDate, a.productId.productSerieId.warrantyTime)}" pattern="dd-MM-yyyy" />
                                                                </td>
                                                                <!--                                                                <td>
                                                                <c:set var="count" value="${warranty.countFaults(a.productId.productSerieId.productSeriId, a.errorId.errorId)}" />
                                                                <span class="${warranty.getButton(count)}">
                                                                ${warranty.getQualityEvaluationEnum(count)}
                                                            </span>
                                                        </td>-->
                                                                <td>
                                                                    ${a.content}
                                                                </td>
                                                                <td>
                                                                    <a href="UpdateWarranties?id=${a.warrantyId}">
                                                                        <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i>
                                                                    </a>
                                                                    &nbsp;
                                                                    <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                       onclick="confirmDelete('DeleteWarranties', ${a.warrantyId})">
                                                                    </i>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    <td colspan="10">
                                                        <nav aria-label="Page navigation">
                                                            <p> Hiện có  ${pagingWarranties.size()} trên ${listSize} bảo hành</p>
                                                            <ul class="pagination justify-content-end">
                                                                <li class="page-item ${currentPage > 1 ? "" : "disabled"}">
                                                                    <a class="page-link" href="WarrantiesManagement?index=${currentPage - 1}" aria-label="Previous">
                                                                        <span aria-hidden="true">&laquo;</span>
                                                                        <span class="sr-only">Previous</span>
                                                                    </a>
                                                                </li>
                                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                                    <li class="page-item ${currentPage == i ? "active" : ""} "><a class="page-link" href="WarrantiesManagement?index=${i}">${i}</a></li>
                                                                    </c:forEach>
                                                                <li class="page-item ${currentPage < endPage ? "" : "disabled"}">
                                                                    <a class="page-link" href="WarrantiesManagement?index=${currentPage + 1}" aria-label="Next">
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
            <script src="admin/assets/vendors/checkbox.js/checkbox.min.js"></script> <!-- import checkbox js -->
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
            <script src="admin/assets/shared/js/delete-confirm.js"></script>
        <c:if test = "${sessionScope.noti != null}">
            <jsp:include page="../../shared/toastify.jsp" />
        </c:if>
    </body>
</html>
