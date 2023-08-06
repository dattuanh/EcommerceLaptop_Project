<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tạo khách hàng mới</title>
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

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" 
              integrity="sha512-t4GWSVZO1eC8BM339Xd7Uphw5s17a86tIZIj8qRxhnKub6WoyhnrxeCIMeAqBPgdZGlCcG2PrZjMc+Wr78+5Xg==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.min.js" 
                integrity="sha512-3dZ9wIrMMij8rOH7X3kLfXAzwtcHpuYpEgQg1OA4QAob1e81H8ntUQmQm3pBudqIoySO5j0tHN4ENzA6+n2r4w==" 
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>


    </head>
    <body>
        <div class="container-scroller">
            <!-- partial:../../partials/_sidebar.jsp -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper" style="background-color: #99999942;">
                    <!-- partial:../../partials/_navbar.jsp -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <!-- partial -->
                    <div class="main-panel mr-3 ml-3 mt-3">
                        <div class="row">
                            <div class="col-md-12 grid-margin stretch-card mx-auto">
                                <div class="card">
                                    <div class="" style="background: #f0f8ff57;" >
                                        <form class="mt-3" action="CreateCustomer" method="post" enctype="multipart/form-data">

                                            <div class="row mb-3">
                                                <div class="col-sm-10"></div>
                                                <div class="col-sm-2">
                                                    <div class="btn-group float-right" role="group" aria-label="Basic mixed styles example">
                                                        <button type="submit" class="btn btn-primary">Add</button>
                                                        <a href="CustomerManagement" type="button" class="btn btn-secondary">Cancel</a>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="accordion mb-3" id="accordionPanelsStayOpenExample">
                                                <div class="accordion-item" style="border: none;">
                                                    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                                        <a type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" 
                                                           aria-expanded="true" aria-controls="panelsStayOpen-collapseOne"
                                                           style="text-decoration: none; color: #052C65; font-size: 20px;"
                                                           class="pl-3">
                                                            Customer Information
                                                        </a>
                                                    </h2>
                                                    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                                                        <div class="accordion-body">

                                                            <div class="form-group">
                                                                <div class="row">
                                                                    <label for="FirstName" class="col-sm-1 col-form-label">FirstName</label>
                                                                    <div class="col-sm-5">
                                                                        <input type="text" class="form-control" id="FirstName" 
                                                                               placeholder="FirstName" name="firstName" required value="${firstName}">
                                                                </div>

                                                                <label for="LastName" class="col-sm-1 col-form-label">LastName</label>
                                                                <div class="col-sm-5">
                                                                    <input type="text" class="form-control" id="LastName" placeholder="LastName" name="lastName" value="${lastName}">
                                                                </div>


                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <div class="row">

                                                                <label for="UserName" class="col-sm-1 col-form-label">UserName</label>
                                                                <div class="col-sm-5">
                                                                    <input type="text" class="form-control" id="UserName" placeholder="UserName" name="userName" value="${username}" required>
                                                                </div>

                                                                <label for="Gender" class="col-sm-1 col-form-label">Gender</label>
                                                                <div class="col-sm-5">
                                                                    <div class="form-check form-check-inline">
                                                                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="1" checked>
                                                                        <label class="form-check-label" for="inlineRadio1">Male</label>
                                                                    </div>
                                                                    <div class="form-check form-check-inline">
                                                                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="0">
                                                                        <label class="form-check-label" for="inlineRadio2">Female</label>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <div class="row">

                                                                <label for="Password" class="col-sm-1 col-form-label">Password</label>
                                                                <div class="col-sm-5">
                                                                    <input type="password" class="form-control" id="Password" placeholder="Password" name="password" required value="${pass}">
                                                                </div>

                                                                <label for="RePassword" class="col-sm-1 col-form-label">Confirm Password</label>
                                                                <div class="col-sm-5">
                                                                    <input type="password" class="form-control" id="RePassword" placeholder="Re Password" name="rePassword" required value="${repass}">
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="accordion" id="accordionPanelsStayOpenExample">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                                        Contact Information
                                                    </button>
                                                </h2>
                                                <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingTwo">
                                                    <div class="accordion-body">

                                                        <div class="form-group">
                                                            <div class="row mb-3">
                                                                <label for="Phone" class="col-sm-1 col-form-label">Phone</label>
                                                                <div class="col-sm-5">
                                                                    <input type="number" class="form-control" id="Phone" placeholder="Phone" name="phone" required value="${phoneNumber}">
                                                                </div>

                                                                <label for="Image" class="col-sm-1 col-form-label">Image</label>
                                                                <div class="col-sm-2">
                                                                    <input type="file" class="form-control" id="Image" placeholder="Image" name="image" onchange="loadFile(event)" required>
                                                                </div>

                                                                <div class="col-sm-3">
                                                                    <img id="output" style="max-width: 100%; position: absolute"/>
                                                                    <script>
                                                                        var loadFile = function (event) {
                                                                            var output = document.getElementById('output');
                                                                            output.src = URL.createObjectURL(event.target.files[0]);
                                                                            output.onload = function () {
                                                                                URL.revokeObjectURL(output.src); // free memory
                                                                            };
                                                                        };
                                                                    </script>
                                                                </div>
                                                            </div> 

                                                            <div class="row mb-3">
                                                                <label for="Email" class="col-sm-1 col-form-label">Email</label>
                                                                <div class="col-sm-5">
                                                                    <input type="email" class="form-control" id="Email" placeholder="Email" 
                                                                           name="email" required value="${email}">
                                                                </div>
                                                            </div>

                                                            <div class="row">
                                                                <label for="Address" class="col-sm-1 col-form-label">Address</label>
                                                                <div class="col-sm-5">
                                                                    <input type="text" class="form-control" id="Address" placeholder="Address" name="address" required value="${address}">
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:../../partials/_footer.jsp -->
                    <%--<jsp:include page="../partials/_footer.jsp"></jsp:include>--%>
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

        <c:if test = "${sessionScope.noti != null}">
            <jsp:include page="../../shared/toastify.jsp" />
        </c:if>
    </body>
</html>
