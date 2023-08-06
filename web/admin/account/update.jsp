<%-- 
    Document   : update
    Created on : May 13, 2023, 11:27:49 PM
    Author     : Giang Minh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
        <link rel="shortcut icon" href="admin/assets/images/favicon.png"/>

        <!--customer-->
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
        <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" 
        crossorigin="anonymous"></script>
        <style>
            input, input:hover {
                color: black
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
                            <form class="forms-sample mt-3" action="UpdateAccount?id=${a.accountId}" method="post" enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-12 grid-margin stretch-card mx-auto">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="row">

                                                    <div class="col-sm-8">
                                                        <div class="row">
                                                            <div class="col-sm-6">
                                                                <div class="form-group">
                                                                    <label for="Username" >Username</label>
                                                                    <input type="text" class="form-control" id="Username" placeholder="Username" name="userName" value="${a.userName}">
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-6">

                                                            <div class="form-group row">
                                                                <label class="col-form-label">Gender</label>
                                                                <div class="col-sm-4 mt-3">
                                                                    <div class="form-check">
                                                                        <label class="form-check-label">
                                                                            <input type="radio" class="form-check-input" name="gender" id="Male" value="1"  ${a.gender == true ? "checked" : ""} > 
                                                                            Male 
                                                                        </label>
                                                                    </div>
                                                                </div>

                                                                <div class="col-sm-5 mt-3">
                                                                    <div class="form-check">
                                                                        <label class="form-check-label">
                                                                            <input type="radio" class="form-check-input" name="gender" id="Female" value="0" ${a.gender == false ? "checked" : ""} > 
                                                                            Female 
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div class="row">
                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="FirstName">FirstName</label>
                                                                <input type="text" class="form-control" id="FirstName" placeholder="FirstName" name="firstName" value="${a.firstName}">

                                                            </div>
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="LastName">LastName</label>
                                                                <input type="text" class="form-control" id="LastName" placeholder="LastName" name="lastName" value="${a.lastName}" >
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">

                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="Password">Password</label>
                                                                <input type="password" class="form-control" id="Password" placeholder="Password" name="password" value="${a.password}">
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="ConfirmPassword">Confirm Password</label>
                                                                <input type="password" class="form-control" id="ConfirmPassword" placeholder="Confirm Password" name="confirmPassword" value="${a.password}">
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="row">
                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="DateOfBirth">Date Of Birth</label>
                                                                <input type="date" class="form-control" id="DateOfBirth" placeholder="DateOfBirth" name="dateOfBirth" value="${a.dateOfBirth}">
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <div class="form-group">
                                                                <label for="PhoneNumber">Phone Number</label>
                                                                <input type="tel" class="form-control" id="PhoneNumber" placeholder="Phone Number" name="phoneNumber" value="${a.phoneNumber}">
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="RoleId" class="">Role</label>
                                                        <select class="js-example-basic-multiple" multiple="multiple" id="roles" name="roles"
                                                                style="width:100%" name="roleIds">
                                                            <c:forEach var="r" items="${roleList}">
                                                                <option value="${r.roleId}">${r.roleName}</option>
                                                            </c:forEach>
                                                        </select>

                                                    </div>

                                                    <div class="text-center">
                                                        <button type="submit" class="btn btn-primary mr-2">Add</button>
                                                        <a href="AccountManagement" class="btn btn-dark">
                                                            Cancel
                                                        </a>
                                                    </div>
                                                </div>

                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label for="Image">Image</label>
                                                        <input type="file" class="form-control" id="Image" placeholder="Image" name="image" onchange="loadFile(event)">
                                                    </div>
                                                    <img id="output" style="width: 100%;"
                                                         class="rounded" src="images/Account/${a.image}"/>
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




                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
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
            <script>
//                                                        var roles = Object.values(${roles});
                                                        $("#roles").val(${roles}).trigger("change");
        </script>
    </body>
</html>
