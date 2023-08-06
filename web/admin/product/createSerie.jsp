<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tạo mới dòng sản phẩm</title>
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
        <jsp:include page="../Util/Format.jsp"></jsp:include>

            <script src="https://cdn.tiny.cloud/1/960h90rahv9jcykqywlacmvxqve30f5e9a1jn5id5msshl6a/tinymce/5/tinymce.min.js" 
            referrerpolicy="origin"></script>
            <script>
                var imageList = [];
            <c:forEach items="${serieImages}" var="image">
                imageList.push("${image.imageName}");
            </c:forEach>
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
                        <form class="content-wrapper forms-sample mt-3" action="CreateSerie" method="post" enctype="multipart/form-data">   
                            <div class="row ">
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <h3 class="card-title col-10">Create Series <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h3>
                                                <h9 style="color: #cccccc">Webpage will save the information of creator and the date time due to company's right</h9>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row ">
                                <div class="col-md-5 grid-margin stretch-card">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Serie's Information <i class="mdi mdi-information card-title" style="color: lightgrey"></i></h4>
                                            <div class="form-group row">
                                                <div class="col-sm-8">
                                                    <label for="SerieName" class="col-form-label"><h6>Serie Name:</h6> </label>
                                                    <input class="col-sm-12 form-control" id="SerieName" name="SerieName" required autofocus>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label for="Price" class="col-form-label"><h6>Price:</h6> </label>
                                                    <!--<input class="col-sm-12 form-control" id="Price" name="Price" required="">-->
                                                    <input type="text" class="form-control" id="Price" onBlur="formatCurrency(this, '', 'blur');" name="Price"
                                                           onkeyup="formatCurrency(this, '');" placeholder="VND #,###.00" name="totalPrice" />
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Category" class="col-sm-12 col-form-label"><h6>Category</h6></label>
                                                <div class="dropdown col-sm-4">
                                                    <select class="btn btn-outline-primary dropdown-toggle" id="list" name="CategoryID" required >
                                                    <c:forEach items="${categoryList}" var="c">
                                                        <option class="dropdown-item" value="${c.categoryID}"> ${c.categoryName} </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>  
                                        <div class="form-group row">
                                            <div class="col-sm-5">
                                                <label for="Manufacturer" class="col-form-label"><h6>Manufacturer:</h6> </label>
                                                <input class="col-sm-12 form-control" id="Manufacturer" placeholder="Manufacturer" 
                                                       value="Lenovo" name="Manufacturer">
                                            </div>
                                            <div class="col-sm-7">
                                                <label for="WarrantyTime" class="col-form-label"><h6>Warranty Time:</h6></label>
                                                <input class="col-sm-12 form-control" id="WarrantyTime" name="WarrantyTime" 
                                                       value="24" required="">
                                            </div>
                                        </div> 
                                        <div class="form-group row">
                                            <div class="col-sm-6">
                                                <label for="Manufacturer" class="col-form-label"><h6>Size</h6> </label>
                                                <input class="col-sm-12 form-control" id="Size" name="Size" value="15 inch" required="">
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="Manufacturer" class="col-form-label"><h6>Color</h6> </label>
                                                <div class="dropdown">
                                                    <select class="btn btn-outline-primary dropdown-toggle" id="Color" name="Color" required >
                                                        <c:forEach items="${allColors}" var="c">
                                                            <option class="dropdown-item" value="${c.colorId}"> ${c.colorName} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-7 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Serie's Image <i class="mdi mdi-information" style="color: lightgrey"></i></h4>
                                        <div class="form-group row">
                                            <label for="Image" class="col-sm-3 col-form-label">Image</label>
                                            <div class="col-sm-9">
                                                <input type="file" class="form-control" id="image" placeholder="image" name="image">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="Image" class="col-sm-3 col-form-label">Multiple Images</label>
                                            <div class="col-sm-9">
                                                <input type="file" class="form-control" id="Image" placeholder="Image"  name="images" multiple>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary mr-2">Add</button>
                                        <a href="SeriesManagement">
                                            <button type="button" class="btn btn-dark">Cancel</button>
                                        </a>
                                    </div>
                                </div>
                            </div> 
                        </div>
                            
<!--                        <div class="row ">
                            <div class="col-12 grid-margin">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="form-group row">
                                            <label for="Description" class="col-sm-12 col-form-label"><h3>Multiple Images: </h3></label>
                                            <div class="col-sm-12">
                                                <textarea id="editor" name="content"></textarea>
                                                <script>
                                                    tinymce.init({
                                                        selector: 'textarea#editor',
                                                        skin: 'oxide-dark',
                                                        content_css: 'dark', // > **Note**: This feature is only available for TinyMCE 5.1 and later.
                                                        //            width: 1500,
                                                        height: 450,
                                                        plugins: 'image code',
                                                        toolbar: 'image code',
                                                        menubar: false,
                                                        setup: function (editor) {
                                                            editor.on('change', function (e) {
                                                                var content = editor.getContent({format: 'text'});
//                                                    
//                                                                for (let i = 0; i < imageList.length; i++) {
//                                                                    var imageUrl = 'images/ProductSeri/' + imageList[i];
//                                                                    content += '<img src="' + imageUrl + '">';
//                                                                    console.log(content);
//                                                                }
                                                                console.log(content);

                                                                // Insert the image tag into the editor
//                                                                editor.setContent(content);
                                                            });
                                                        },
                                                        images_upload_url: '/EcommerceLaptopSale/TinyController',
                                                        images_upload_handler: function (blobInfo, success, failure) {
                                                            var xhr, formData;
                                                            xhr = new XMLHttpRequest();
                                                            xhr.withCredentials = false;
                                                            xhr.open('POST', '/EcommerceLaptopSale/TinyController?directory=' + "/images/ProductSeri/");
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

                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>-->
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
