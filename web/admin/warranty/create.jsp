<%-- 
    Document   : create
    Created on : May 13, 2023, 9:50:21 PM
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
        <title>New Warranties</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="admin/assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <link rel="stylesheet" href="admin/assets/vendors/select2/select2.min.css">
        <link rel="stylesheet" href="admin/assets/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="admin/assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="admin/assets/images/favicon.png" />
        <!--        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <%--<jsp:include page="../assets/shared/jsp/tinymce.jsp" />--%>
        <script>
            tinymcee('../../web/images/warranties');
        </script>-->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            input, input:hover {
                color: black
            }

            button:focus {
                box-shadow: none !important;
            }
            
            .showWarrantyInfo {
                visibility: hidden;
            }
        </style>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#search").on("keyup", function () {
                    search();
                });
            });

            function search() {
                var search = $("#search").val();
//                console.log(search);
                $.ajax({
                    url: "Ajax",
                    data: {search: search},
                    type: "GET",
                    success: function (data) {
                        $("#result").css('display', 'block');
//                        console.log('success');
                        var result = document.getElementById('result');
                        result.innerHTML = data;
                    }
                });
            }

            function getData(productId) {
                $.ajax({
                    url: "Ajax",
                    data: {productId: productId},
                    type: "POST",
                    success: function (data) {

                        $("#result").css('display', 'none');
                        var parsedData = JSON.parse(data);

                        if (parsedData[0] === "false") {
                            $("#falseNoti").html(parsedData[1]);
                            $('.showWarrantyInfo').css({'visibility': 'hidden'});
                            return;
                        } else {
                            $("#falseNoti").html("");
                            $('.showWarrantyInfo').css({'visibility': 'visible'});
                        }

                        var c = parsedData[0];
                        var p = parsedData[1];
                        var serie = p.productSerieId;
                        $("#maSp").html(p.productId);
                        $("#tenSp").html(serie.productSeriName);
                        $("#image").attr("src", "images/ProductSeri/" + serie.imagePreview);
                        $("#warrantyTime").html(serie.warrantyTime);
                        $("#expiredDate").html(parsedData[2]);
                        $("#customerName").val(c.lastName + ' ' + c.firstName);
                        $("#phone").val(c.phone);
                        $("#productId").val(p.productId);
                        $("#warrantyPrice").html(parsedData[3]);

                        if (parsedData[4] != null) {
                            $("#falseNoti").html(parsedData[4]);
                        }
                    }
                });
            }
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js" 
                integrity="sha512-3dZ9wIrMMij8rOH7X3kLfXAzwtcHpuYpEgQg1OA4QAob1e81H8ntUQmQm3pBudqIoySO5j0tHN4ENzA6+n2r4w==" 
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </head>
    <body>
        <div class="container-scroller">
            <!-- partial:../../partials/_sidebar.jsp -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper">
                    <!-- partial:../../partials/_navbar.jsp -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <!-- partial -->
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="row">
                                <div class="col-12 grid-margin">
                                    <div class="card">
                                        <div class="card-body">
                                            <form class="forms-sample mt-3" action="CreateWarranties" method="post">

                                                <!--<input type="hidden" id="customerId" name="customerId" >-->
                                                <input type="hidden" id="productId" name="productId" >

                                                <div class="row">
                                                    <div class="col-sm-9"></div>
                                                    <button type="submit" class="col-sm-1 btn btn-primary mr-2 showWarrantyInfo">Tạo mới</button>
                                                    <a href="WarrantiesManagement" class="col-sm-1 btn btn-dark">
                                                        Hủy
                                                    </a>
                                                </div>

                                                <h5 style="text-align: center">PHIẾU BẢO HÀNH</h5>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-md-9"></div>
                                                    <div class="col-md-3">
                                                        <label style="display: inline-block">Ngày:</label>
                                                        <input value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${date}" />" 
                                                           style="border: none" readonly name="currentDate">
                                                </div>

                                            </div>

                                            <div class="accordion" id="accordionPanelsStayOpenExample">
                                                <div class="accordion-item" style="border: none;">
                                                    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                                        <a type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" 
                                                           aria-expanded="true" aria-controls="panelsStayOpen-collapseOne"
                                                           style="text-decoration: none; color: #052C65; font-size: 20px"
                                                           class="pl-3">
                                                            Tìm kiếm sản phẩm để tạo bảo hành
                                                        </a>
                                                    </h2>
                                                    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                                                        <div class="accordion-body">
                                                            <div class="row">
                                                                <div class="col-sm-5">
                                                                    <input type="text" class="form-control" id="search" name="search" 
                                                                           placeholder="Search Product" value="${search}" autofocus>
                                                                </div>
                                                                <div class="col-sm-7" id="falseNoti" style="color: red">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div id="result"
                                                 style="position: absolute;
                                                 cursor: pointer"></div>

                                            <c:set var = "admin" scope = "session" value = "${sessionScope.account}"/>

                                            <div class="row mt-4 mb-3 showWarrantyInfo">

                                                <div class="row">
                                                    <div class="col-sm-1"></div>
                                                    <h6 class="col-sm-4">THÔNG TIN KHÁCH HÀNG</h6>
                                                    <div class="col-sm-2"></div>
                                                    <h6 class="col-sm-4">THÔNG TIN NGƯỜI TIẾP NHẬN</h6>
                                                    <div class="col-sm-1"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-1"></div>

                                                    <div class="col-sm-4">
                                                        <div class="form-floating mb-3">
                                                            <input type="text" class="form-control" id="customerName" placeholder="Tên KH" readonly>
                                                            <label for="customerName">Tên KH</label>
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-2"></div>

                                                    <div class="col-sm-4">
                                                        <div class="form-floating mb-3">
                                                            <input type="text" class="form-control" placeholder="Tên nhân viên" 
                                                                   id="tenNhanVien" value="${admin.fullName}" readonly>
                                                            <label for="tenNhanVien">Tên nhân viên</label>
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-1"></div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-sm-1"></div>

                                                    <div class="col-sm-4">
                                                        <div class="form-floating mb-3">
                                                            <input type="text" class="form-control" placeholder="Điện thoại" id="phone" readonly>
                                                            <label for="customerName">Điện thoại</label>
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-2"></div>

                                                    <div class="col-sm-4">
                                                        <div class="form-floating mb-3">
                                                            <input type="text" class="form-control" placeholder="Mã số nv" 
                                                                   id="maNhanVien" value="${admin.accountId}" readonly>
                                                            <label for="maNhanVien">Mã số nv</label>
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-1"></div>

                                                </div>
                                            </div>

                                            <c:set value="${w.productId.productSerieId}" var="productSeri" />

                                            <table class="table table-bordered showWarrantyInfo">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">TT</th>
                                                        <th scope="col">Mã SP</th>
                                                        <th scope="col">Tên SP</th>
                                                        <th scope="col">Ảnh sp</th>
                                                        <th scope="col">Phí bảo hành</th>
                                                        <th scope="col">Ngày bắt đầu</th>
                                                        <th scope="col">Hạn bảo hành</th>
                                                        <th scope="col">Ngày kết thúc</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">1</th>
                                                        <td id="maSp"></td>
                                                        <td id="tenSp"></td>
                                                        <td>
                                                            <img id="image"
                                                                 onerror="this.onerror=null;this.src='images/others/img-14.jpg';" />
                                                        </td>
                                                        <td id="warrantyPrice"></td>
                                                        <td>
                                                            <fmt:formatDate pattern = "dd-MM-yyyy" value = "<%=new java.util.Date()%>" />
                                                        </td>
                                                        <td id="warrantyTime"></td>
                                                        <td id="expiredDate"></td>
                                                    </tr>
                                                </tbody>
                                            </table>


                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:../../partials/_footer.jsp -->
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
        <script src="admin/assets/vendors/select2/select2.min.js"></script>
        <script src="admin/assets/vendors/typeahead.js/typeahead.bundle.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="admin/assets/js/off-canvas.js"></script>
        <script src="admin/assets/js/hoverable-collapse.js"></script>
        <script src="admin/assets/js/misc.js"></script>
        <script src="admin/assets/js/settings.js"></script>
        <script src="admin/assets/js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page -->
        <script src="admin/assets/js/file-upload.js"></script>
        <script src="admin/assets/js/typeahead.js"></script>
        <script src="admin/assets/js/select2.js"></script>
        <!-- End custom js for this page -->
    </body>
</html>
