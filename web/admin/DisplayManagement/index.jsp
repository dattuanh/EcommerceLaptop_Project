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
        <link rel="stylesheet" href="admin/assets/shared/css/imageInput.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="admin/assets/images/favicon.png" />
        <jsp:include page="../assets/shared/jsp/tinymce.jsp" />
        <script>
//            function tinymcee(directory) {
            tinymce.init({
                selector: 'textarea#editorHeading',
                skin: 'oxide-dark',
                content_css: 'dark', // > **Note**: This feature is only available for TinyMCE 5.1 and later.
//            width: 1500,
                height: 200,
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                images_upload_url: '/EcommerceLaptopSale/TinyController',
                images_upload_handler: function (blobInfo, success, failure) {
                    var xhr, formData;
                    xhr = new XMLHttpRequest();
                    xhr.withCredentials = false;
                    xhr.open('POST', '/EcommerceLaptopSale/TinyController?directory=' + '../../web/images/News/');
                    xhr.onload = function () {
                        var json;

                        if (xhr.status != 200) {
                            failure('HTTP Error: ' + xhr.status);
                            return;
                        }

                        json = JSON.parse(xhr.responseText);

                        if (!json || typeof json.location != 'string') {
                            failure('Invalid JSON: ' + xhr.responseText);
                            return;
                        }

                        success(json.location);
                    };
                    formData = new FormData();
                    formData.append('file', blobInfo.blob(), blobInfo.filename());
                    xhr.send(formData);
                }
            });
//            }

            tinymcee('../../web/images/News/');

            var loadFile = function (event) {
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0]);
                console.log(output);
                output.onload = function () {
                    URL.revokeObjectURL(output.src); // free memory
                };
            };
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
                        <form class="content-wrapper forms-sample mt-3" action="CompanyManagement" method="post" enctype="multipart/form-data">
                            <div class="row ">
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <h3 class="card-title col-10">Edit Company's Information <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h3>
                                                <h9 style="color: #cccccc">Webpage will save the information of creator and the date time due to company's right</h9>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row ">
                                <div class="col-md-8 grid-margin stretch-card">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="form-group row">
                                                <div class="col-sm-12">
                                                    <label>
                                                        <h4 class="card-title">Company's Name <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h4>
                                                    </label>
                                                    <textarea id="editorHeading" name="heading">${company.newsHeading}</textarea>

                                            </div>
                                        </div>
                                        <h4 class="card-title">Company's Information <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h4>
                                        <textarea id="editor" name="content">${company.newsContent}</textarea>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-4 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Company's Logo <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h4>
                                        <input type="file" class="form-control" id="newsImage" 
                                               placeholder="link" name="image" value="${company.newsImage}" onchange="loadFile(event)">
                                        <div class="text-center">
                                            <img id="output" src="images/News/${company.newsImage}" 
                                                 onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                                                 style="max-width: 70%; margin-top: 20px" />
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-primary mr-2">Update</button>
                                        <a href="CompanyManagement">
                                            <button type="button" class="btn btn-secondary">Cancel</button>
                                        </a>
                                    </div> 
                                </div>

                            </div> 
                        </div>
                    </form>
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
        <script src="admin/assets/js/imageUpload.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
