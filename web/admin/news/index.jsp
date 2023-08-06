
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Base.Util.SlugConverter" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>News Management</title>
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

        <!-- Fancybox CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css">

        <!-- Fancybox JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
        <script>
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

                preview();
            });

            function search(index) {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                console.log(entry);

                $.ajax({
                    url: "NewsManagement",
                    data: {search: search, filter: filter, index: index, entry: entry},
                    type: "POST",
                    success: function (data) {
                        var searchList = document.getElementById('content');
                        searchList.innerHTML = data;
                        preview();
                        document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());
                        document.querySelector('.check-all-submit-btn').classList.add('disabled');
                    }
                });
            }

            function showToClient(span, newsId) {
                if (span.classList.contains('badge-warning')) {
                    span.classList.remove('badge-warning');
                    span.classList.add('badge-success');
                    span.innerHTML = 'Show';
                } else if (span.classList.contains('badge-success')) {
                    span.classList.remove('badge-success');
                    span.classList.add('badge-warning');
                    span.innerHTML = 'Not Show';
                }

                $.ajax({
                    url: "NewsManagement",
                    data: {newsId: newsId, action: "client"},
                    type: "POST",
                    success: function (data) {
                        console.log(data);
                    }
                });
            }

            function showToSlider(span, newsId) {
                if (span.classList.contains('badge-warning')) {
                    span.classList.remove('badge-warning');
                    span.classList.add('badge-success');
                    span.innerHTML = 'Show';
                } else if (span.classList.contains('badge-success')) {
                    span.classList.remove('badge-success');
                    span.classList.add('badge-warning');
                    span.innerHTML = 'Not Show';
                }

                $.ajax({
                    url: "NewsManagement",
                    data: {newsId: newsId, action: "slider"},
                    type: "POST",
                    success: function (data) {
                        console.log(data);
                    }
                });
            }


            function preview() {
                $('td').find('img').each(function () {
                    $(this).wrap('<a data-fancybox="gallery" href="' + $(this).attr('src') + '"></a>');
                });

                // Kích hoạt Fancybox
                $('[data-fancybox="gallery"]').fancybox({
                    buttons: [
                        'zoom',
                        'slideShow',
                        'fullScreen',
                        'close'
                    ],
                    loop: true
                });
            }
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
                                            <div class ="row">
                                                <div class="nav-link mt-2 mt-md-0 d-none d-lg-flex search mx-auto" >
                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw check-all-submit-btn disabled " style="margin-right: 10px">Remove</button>                                                            
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
                                                        <option value="Title"> Title </option>
                                                        <option value="Heading"> Heading</option>
                                                        <!--                                                                <option value="NewsGroup"> News Group</option>-->
                                                    </select>
                                                </div>
                                                <i class="mdi mdi-magnify"" style="font-size: 22px"></i>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" id="search" name="search" placeholder="Search News" value="${search}" autofocus>
                                                </div>
                                                <a type="submit" href="NewsManagement" class="btn btn-light col-md-1" 
                                                   style="line-height: unset; margin-right: 10px">
                                                    <i class="mdi mdi-reload"></i>
                                                </a>
                                                <a href="CreateNews" class="col-md-3">
                                                    <button type="button" class="btn btn-primary btn-rounded btn-fw">Add News</button>
                                                </a>
                                            </div>
                                        </div>
                                        <!-- form cho submit xóa tất cả check box -->
                                        <form id="delete" class="" name="deleteAll-form" method="post" action="DeleteNews">
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
                                                            <th> Preview </th>
                                                            <th> News Title </th>
                                                            <th> Created By </th>
                                                            <th> Modified By </th>
                                                            <th> Show to Client </th>
                                                            <th> Show to Slider </th>
                                                            <th> Link in Client </th>
                                                            <th> Actions </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="content">
                                                        <c:forEach items="${newsList}" var="c">
                                                            <tr>
                                                                <td>
                                                                    <div class="form-check form-check-muted m-0">
                                                                        <label class="form-check-label">
                                                                            <input type="checkbox" class="form-check-input" name="Ids[]" value="${c.newId}">
                                                                        </label>
                                                                    </div>
                                                                </td>
                                                                <td class="py-1">
                                                                    <img id="output" src="images/News/${c.newsImage}" 
                                                                         onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                                                                         />
                                                                </td>
                                                                <td>
                                                                    ${fn:substring(c.newTitle, 0, 20)}
                                                                </td>
                                                                <!--                                                                <td> 
                                                                ${fn:substring(c.newsHeading, 0, 20)}
                                                            </td>-->
                                                                <td> ${c.createBy.userName} </td>
                                                                <td> ${c.modifiedBy.userName} </td>
                                                                <td> 
                                                                    <span class="badge ${c.isPresent == true ? "badge-success" : "badge-warning"}" 
                                                                          onclick="showToClient(this, ${c.newId})">
                                                                        ${c.isPresent == true ? "Show" : "Not Show"}
                                                                    </span>
                                                                </td>
                                                                <td> 
                                                                    <span class="badge ${c.isSlider == true ? "badge-success" : "badge-warning"}" 
                                                                          onclick="showToSlider(this, ${c.newId})">
                                                                        ${c.isSlider == true ? "Show" : "Not Show"}
                                                                    </span>
                                                                </td>
                                                                <td> 
                                                                    <input type="text" 
                                                                           value="http://localhost:9999/EcommerceLaptopSale/TechSingleControl/${SlugConverter.convertToSlug(c.newTitle.replaceAll(" ", "-"))}-${c.newId}-${c.newsGroupID.newsGroupID}-${c.createBy.accountId}.html" 
                                                                           id="myInput_${c.newId}" style="display: none">
                                                                    <button type="button" onclick="copy('${c.newId}')" class="badge badge-success">Get link</button>

                                                                    <script>
                                                                        function copy(newsId) {
                                                                            // Get the text field
                                                                            var copyText = document.getElementById("myInput_" + newsId);

                                                                            // Select the text field
                                                                            copyText.select();
                                                                            copyText.setSelectionRange(0, 99999); // For mobile devices

                                                                            // Copy the text inside the text field
                                                                            navigator.clipboard.writeText(copyText.value);

                                                                            // Alert the copied text
//                                                                            alert("Copied the text: " + copyText.value);
                                                                        }
                                                                    </script>
                                                                </td>
                                                                <td>
                                                                    &nbsp;
                                                                    <a href="UpdateNews?id=${c.newId}">
                                                                        <i class="mdi mdi-lead-pencil" style="font-size: 22px"></i>
                                                                    </a>
                                                                    &nbsp;
                                                                    <i class="mdi mdi-delete" style="font-size: 22px; color: #1079eb" 
                                                                       onclick="confirmDelete('DeleteNews', ${c.newId})">
                                                                    </i>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    <td colspan="10">                                        
                                                        <nav aria-label="Page navigation">
                                                            <p> There are  ${newsList.size()} / ${listSize} news </p>
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
