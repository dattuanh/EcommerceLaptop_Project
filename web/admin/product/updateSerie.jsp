<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>${selectedSerie.productSeriName}</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <script src="https://cdn.tiny.cloud/1/960h90rahv9jcykqywlacmvxqve30f5e9a1jn5id5msshl6a/tinymce/5/tinymce.min.js" 
        referrerpolicy="origin"></script>
        <script>
            var imageList = [];
            <c:forEach items="${serieImages}" var="image">
            imageList.push("${image.imageName}");
            </c:forEach>
        </script>
    </head>
    <jsp:include page="../Util/Format.jsp"></jsp:include>
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
                                                <h3 class="card-title col-10">Series Information: ${selectedSerie.productSeriName}</h3>
                                            <h9 style="color: #cccccc"> This page allow user with roles to view series information in real client view and add new batch of Product</h9>
                                            <input type="hidden" id="serieId" name="serieId" value="${selectedSerie.productSeriId}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-success shadow-none active" id="infor-tab" data-bs-toggle="tab" data-bs-target="#infor" type="button" role="tab" aria-controls="infor" aria-selected="true"><h6>Series Information</h6></button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-secondary shadow-none" id="history-tab" data-bs-toggle="tab" data-bs-target="#history" type="button" role="tab" aria-controls="infor" aria-selected="true"><h6>Modified History</h6></button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-warning shadow-none" id="product-tab" data-bs-toggle="tab" data-bs-target="#product" type="button" role="tab" aria-controls="product" aria-selected="false"><h6>Product List</h6></button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-info shadow-none" id="client-tab" data-bs-toggle="tab" data-bs-target="#client" type="button" role="tab" aria-controls="infor" aria-selected="true"><h6>Client's View</h6></button>
                            </li>
                        </ul>
                        <div class="tab-content card" id="myTabContent">
                            <div class="tab-pane fade show active" id="infor" role="tabpanel" aria-labelledby="home-tab">
                                <div class="card-body">



                                    <div class="form-group row">
                                        <h3 class="card-title col-10">Display Information: ${selectedSerie.productSeriName}</h3>
                                    </div> 
                                    <div class="form-group row">
                                        <div class="col col-md-6">
                                            <div class="form-group row">
                                                <!--<label for="Image" class=" col-sm-12 col-form-label">Preview Image </label>-->
                                                <div class="col-sm-8">
                                                    <!--<img src="images/ProductSeri/${selectedSerie.imagePreview}" onerror="this.onerror=null;this.src='admin/assets/shared/images/others/img-14.jpg';" style="max-width: 100%; max-height: 300px" />-->
                                                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                                        <div class="carousel-inner">
                                                            <c:forEach var="si" items="${serieImages}">
                                                                <div class="carousel-item">
                                                                    <img class="d-block w-100" src="images/ProductSeri/${si.imageName}" 
                                                                         alt="First slide" style="width: 100%">
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Previous</span>
                                                        </a>
                                                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Next</span>

                                                        </a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col col-md-6">
                                            <div class="form-group row">
                                                <label for="Serie Name" class="col-sm-3 col-form-label">Series Name: </label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="SerieName" placeholder="Serie Name" name="SerieName" 
                                                           value="${selectedSerie.productSeriName}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Price" class="col-sm-3 col-form-label">Price: </label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Price" placeholder="Price" name="price" value="<fmt:formatNumber value = "${selectedSerie.price}" type = "number"/>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label for="Category" class="col-sm-3 col-form-label">Category</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Category" placeholder="Category" name="Category" 
                                                           value="${selectedSerie.categoryId.categoryName}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Manufacturer" class="col-sm-3 col-form-label">Manufacturer</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Manufacturer" placeholder="Manufacturer" name="Manufacturer" 
                                                           value="${selectedSerie.manufacturer}">
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
                                                    <input type="text" class="form-control" id="WarrantyTime" placeholder="WarrantyTime" name="WarrantyTime" 
                                                           value="${selectedSerie.warrantyTime}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Size" class="col-sm-3 col-form-label">Size</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Size" placeholder="Size" name="Size" 
                                                           value="${selectedSerie.size}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Color" class="col-sm-3 col-form-label">Color</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="Color" placeholder="Color" name="Color" 
                                                           value="${selectedSerie.color.colorName}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label for="Description" class="col-sm-3 col-form-label">Description: </label>
                                                <div class="col-sm-9">
                                                    <a href="#">Go to client view</a>
                                                </div>
                                            </div>   
                                        </div>
                                    </div>

                                    <!--                                    <textarea id="editor" name="content"></textarea>
                                                                        <script>
                                                                            tinymce.init({
                                                                                selector: 'textarea#editor',
                                                                                skin: 'oxide-dark',
                                                                                content_css: 'dark', // > **Note**: This feature is only available for TinyMCE 5.1 and later.
                                                                                //            width: 1500,
                                                                                height: 450,
                                                                                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                                                                                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                                                                                setup: function (editor) {
                                                                                    editor.on('init', function (e) {
                                                                                        var content = editor.getContent({format: 'text'});
                                    //                                                    
                                                                                        for (let i = 0; i < imageList.length; i++) {
                                                                                            var imageUrl = 'images/ProductSeri/' + imageList[i];
                                                                                            content += '<img src="' + imageUrl + '">';
                                                                                            console.log(content);
                                                                                        }
                                    
                                                                                        // Insert the image tag into the editor
                                                                                        editor.setContent(content);
                                                                                    });
                                                                                },
                                                                                images_upload_url: '/EcommerceLaptopSale/TinyController',
                                                                                images_upload_handler: function (blobInfo, success, failure) {
                                                                                    var xhr, formData;
                                                                                    xhr = new XMLHttpRequest();
                                                                                    xhr.withCredentials = false;
                                                                                    xhr.open('POST', '/EcommerceLaptopSale/TinyController?id=${selectedSerie.productSeriId}&directory=../../web/images/ProductSeri/');
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
                                    
                                                                        </script>-->

                                    <a href="UpdateSerie?serie=${selectedSerie.productSeriId}">
                                        <button type="button" class="btn btn-primary mr-2">Update</button>
                                    </a>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="history-tab">
                                <div class="card-body">
                                    <div class="form-group row">
                                        <div class="col col-md-6">
                                            <div class="bg-gray-dark d-flex d-md-block d-xl-flex py-3 px-4 px-md-3 px-xl-4 rounded col-12">
                                                <div class="text-md-center text-xl-left">
                                                    <h6 class="mb-1">Last Modified By </h6>
                                                    <h6 class="mb-1">Last Modified Date </h6>
                                                </div>
                                                <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                                                    <h6 class="font-weight-bold mb-0">${selectedSerie.modifiedBy.userName}</h6>
                                                    <h6 class="text-muted mb-0"><fmt:formatDate value="${selectedSerie.modifiedDate}" pattern="dd-MM-yyyy" /></h6>

                                                </div>
                                            </div>
                                            <hr>
                                            <div class="bg-gray-dark d-flex d-md-block d-xl-flex  py-3 px-4 px-md-3 px-xl-4 rounded col-12">
                                                <div class="text-md-center text-xl-left">
                                                    <h6 class="mb-1">Created By </h6>
                                                    <h6 class="mb-1">Created Date </h6>

                                                </div>
                                                <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                                                    <h6 class="font-weight-bold mb-0">${selectedSerie.createdBy.userName}</h6>
                                                    <h6 class="text-muted mb-0"><fmt:formatDate value="${selectedSerie.createdDate}" pattern="dd-MM-yyyy" /></h6>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-md-6">
                                            <div class="bg-gray-dark d-flex d-md-block d-xl-flex  py-3 px-4 px-md-3 px-xl-4 rounded ">
                                                <div class="text-md-center text-xl-left">
                                                    <h6 class="mb-1">Modified History </h6>
                                                </div>
                                                <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                                                    <p class="text-muted mb-0">${selectedSerie.modifiedHistory}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="product" role="tabpanel" aria-labelledby="product-tab">
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
                                                    <a type="submit" href="SeriesManagement" class="btn btn-light w-20" style="line-height: unset; margin-right: 25px;">
                                                        <i class="mdi mdi-reload"></i>
                                                    </a>
                                                    <div class="col-md-1">
                                                        <a href="AddProduct?serie=${selectedSerie.productSeriId}">
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
                                        <input hidden="" name="id" id="id" value="${selectedSerie.productSeriId}">
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
                                                        <th> ID </th>
                                                        <th> Series Name </th>
                                                        <th> Sold </th>
                                                        <th> Batch Serial </th>
                                                        <th> Warranty </th>
                                                        <th> Action </th>
                                                        <!--                                                            <th> Price </th>
                                                                                                                    <th> Category </th>
                                                                                                                    <th> Size </th>
                                                                                                                    <th> Color </th>-->
                                                        <!--                                                            <th> Payment Mode </th>
                                                                                                                    <th> Start Date </th>
                                                                                                                    <th> Payment Status </th>-->
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
                                                            <!--                                                            <td>
                                                                                                                            <img src="admin/assets/images/faces/face1.jpg" alt="image" />
                                                                                                                            <span class="pl-2">Henry Klein</span>
                                                                                                                        </td>-->
                                                            <td> ${p.productId} </td>
                                                            <td> ${selectedSerie.productSeriName}</td>
                                                            <td> ${p.status} </td>
                                                            <td> ${p.batchSerial} </td>
                                                            <c:if test="${empty p.warranty.status}">
                                                                <td> Not activated </td>
                                                            </c:if>
                                                            <c:if test="${not empty p.warranty.status}">
                                                                <c:if test="${p.warranty.status == true}">
                                                                    <td> On Warranty </td>
                                                                </c:if>
                                                                <c:if test="${p.warranty.status == false}">
                                                                    <td> Expired Warranty </td>
                                                                </c:if>
                                                            </c:if>
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
                                                        <p> There are ${productList.size()} / ${listSize} Products</p>
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
                            <div class="tab-pane fade" id="client" role="tabpanel" aria-labelledby="client-tab">
                                <iframe src="http://localhost:9999/EcommerceLaptopSale/SerieDetail?serieId=${selectedSerie.productSeriId}" 
                                        width="100%" height="750"></iframe>
                            </div>
                        </div>
                    </div>


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
            
            <script>
                                                                       var items = document.getElementsByClassName('carousel-item');

                                                                       for (let i = 0; i < items.length; i++) {
                                                                           if (i == items.length - 1) {

                                                                               change(items[i], items[0]);
//                        setTimeout(change(items[i], items[0]), 5000);
                                                                               i = 0;
                                                                           } else {
                                                                               change(items[i], items[i + 1])
//                        setTimeout(change(items[i], items[i + 1]), 5000);
                                                                           }
                                                                           sleep(2000);
                                                                       }

                                                                       function change(x, y) {
                                                                           x.classList.remove("active");
                                                                           y.classList.add("active");
                                                                       }
            </script>
    </body>
</html>
