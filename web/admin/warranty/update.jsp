<%-- 
    Document   : update
    Created on : May 13, 2023, 11:27:49 PM
    Author     : Giang Minh
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Account</title>
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

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            input, input:hover {
                color: black
            }
            
            button:focus {
                box-shadow: none !important;
            }
        </style>
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
                        <div class="content-wrapper" style="padding-top: 5%">
                            <div class="grid-margin stretch-card mx-auto">
                                <div class="card">
                                    <div class="card-body">
                                        <form id="frm" class="forms-sample mt-3" action="UpdateWarranties?id=${w.warrantyId}" method="post">
                                        <input type="hidden" name="warrantyId" value="${w.warrantyId}" />
                                        <h5 style="text-align: center">PHIẾU BẢO HÀNH</h5>
                                        <br/>
                                        <div class="row">
                                            <div class="col-md-9"></div>
                                            <div class="col-md-3">
                                                <label style="display: inline-block">Ngày:</label>
                                                <span><fmt:formatDate pattern = "dd-MM-yyyy" value = "${w.createdDate}" /></span>
                                            </div>

                                        </div>

                                        <div style="margin: 20px 0;">
                                            <h6>THÔNG TIN KHÁCH HÀNG</h6>
                                            <label>Tên KH:</label>
                                            <span>${c.fullName}</span>
                                            <br/>
                                            <label>ĐT:</label>
                                            <span>${c.phone}</span>
                                        </div>

                                        <div style="margin: 20px 0;">
                                            <h6>THÔNG TIN NGƯỜI TIẾP NHẬN</h6>
                                            <label>Tên nv: </label>
                                            <span>${w.createdBy.fullName}</span>

                                            <br/>
                                            <label>Mã số nv: </label>
                                            <span>${w.createdBy.accountId}</span>
                                        </div>

                                        <c:set value="${w.productId.productSerieId}" var="productSeri" />

                                        <table class="table table-bordered">
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
                                                    <td>${w.productId.productId}</td>
                                                    <td>${productSeri.productSeriName}</td>
                                                    <td>
                                                        <img src="images/ProductSeri/${productSeri.imagePreview}" 
                                                             onerror="this.onerror=null;this.src='images/others/img-14.jpg';" />
                                                    </td>
                                                    <td>
                                                        ${w.warrantyPrice}
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${w.createdDate}" pattern="dd/MM/yyyy" /> 
                                                    </td>
                                                    <td>
                                                        ${w.warrantyTime}
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate var="year" value="${w.warrantyDate}" pattern="yyyy" /> 
                                                        <fmt:formatDate var="month" value="${w.warrantyDate}" pattern="MM" /> 
                                                        <fmt:formatDate var="day" value="${w.warrantyDate}" pattern="dd" /> 
                                                        <c:set var="calMonth" value="${month + (productSeri.warrantyTime%12)}" /> 
                                                        <c:set value="${year + (productSeri.warrantyTime -  productSeri.warrantyTime%12)/12 + (calMonth - calMonth%12)/12 }" var="y" />
                                                        ${day}/${calMonth%12}/<fmt:formatNumber value="${y}" pattern="#" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>


                                        <div class="mt-4 text-center" id="frmDecision">
                                            <button type="submit" class="btn btn-primary mr-2">Confirm Update</button>
                                            <a href="WarrantiesManagement">
                                                <button type="button" class="btn btn-light">Cancel</button>
                                            </a>
                                            <input type="button" id="create_pdf" value="Generate PDF">  
                                        </div>
                                    </form>
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

        <script src="../assets/shared/js/html2pdf.bundle.min.js"></script>
        <<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.8.0/html2pdf.bundle.min.js"></script>
        <script type="text/javascript">
            document.getElementById('create_pdf').onclick = function () {
                document.getElementById('frmDecision').style.display = 'none';
                var element = document.getElementById('frm');
                var opt = {
                    margin: 1,
                    filename: 'mywarranty.pdf',
                    image: {type: 'jpeg', quality: 0.98},
                    html2canvas: {scale: 2},
                    jsPDF: {unit: 'in', format: 'letter', orientation: 'portrait'}
                };

                html2pdf(element, opt);
                document.getElementById('frmDecision').style.display = 'block';
            };
        </script>
    </body>

</html>
