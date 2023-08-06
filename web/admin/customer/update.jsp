<%-- 
    Document   : edit
    Created on : May 12, 2023, 11:10:14 PM
    Author     : Giang Minh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Customer</title>
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

        <style>
            /* Style the buttons that are used to open and close the accordion panel */
            .accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                text-align: left;
                border: none;
                outline: none;
                transition: 0.4s;
            }

            /* Add a background color to the button if it is clicked on (add the .active class with JS), and when you move the mouse over it (hover) */
            .active, .accordion:hover {
                background-color: #ccc;
            }

            /* Style the accordion panel. Note: hidden by default */
            .panel {
                padding: 0 18px;
                background-color: white;
                max-height: 0;
                overflow: hidden;
                transition: max-height 0.2s ease-out;
            }

            .accordion:after {
                content: '\25bc'; /* Unicode character for "plus" sign (+) */
                font-size: 13px;
                color: #777;
                float: right;
                margin-left: 5px;
            }

            .active:after {
                content: "\25B2"; /* Unicode character for "minus" sign (-) */
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
                        <div class="content-wrapper">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <form class="forms-sample mt-3" action="UpdateCustomer?id=${c.customerId}" method="post" enctype="multipart/form-data">
                                            <div class="row">
                                                <div class="form-group col-sm-5">
                                                    <label for="Image" class="col-sm-2 col-form-label"></label>
                                                    <div class="col-sm-9">
                                                        <img src="images/Customer/${c.image}" id="output" class="rounded"
                                                             onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                                                             style="max-width: 100%; max-height: 100%" />
                                                        <input type="file" class="form-control mt-3" id="Image" placeholder="Image" 
                                                               name="image" value="${c.image}" onchange="loadFile(event)" style="width: 100%">
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
                                                <div class="col-sm-7">

                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="FirstName" class="col-form-label">FirstName</label>
                                                            <input type="text" class="form-control" id="FirstName" placeholder="FirstName" name="firstName" value="${c.firstName}">
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <label for="LastName" class="col-form-label">LastName</label>
                                                            <input type="text" class="form-control" id="LastName" placeholder="LastName" name="lastName" value="${c.lastName}">
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="Email" class="col-form-label">Email</label>
                                                            <input type="email" class="form-control" id="Email" placeholder="Email" 
                                                                   name="email" value="${c.email}" readonly>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <label class="col-form-label">Gender</label>
                                                            <div class="form-check">
                                                                <div class="row">
                                                                    <div class="col-sm-5">
                                                                        <label class="form-check-label" for="Male">
                                                                            <input class="form-check-input" type="radio" name="gender" id="Male" ${c.gender == true ? "checked" : ""}  value="1">
                                                                            Male
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-sm-5">
                                                                        <label class="form-check-label" for="Female">
                                                                            <input class="form-check-input" type="radio" name="gender" id="Female" ${c.gender == false ? "checked" : ""} value="0">
                                                                            Female
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="UserName" class="col-form-label">UserName</label>
                                                            <input type="text" class="form-control" id="UserName" placeholder="UserName" name="userName" value="${c.userName}">
                                                        </div>

                                                        <div class="col-sm-6">
                                                            <label for="Password" class="col-form-label">Password</label>
                                                            <input type="password" class="form-control" id="Password" placeholder="Password" name="password" value="${c.password}">
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="Address" class="col-form-label">Address</label>
                                                            <input type="text" class="form-control" id="Address" placeholder="Address" name="address" value="${c.address}">
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <label for="Phone" class="col-form-label">Phone</label>
                                                            <input type="tel" class="form-control" id="Phone" placeholder="Phone" name="phone" value="${c.phone}">
                                                        </div>
                                                    </div>

                                                    <div class="form-group row">
                                                        <div class="col-sm-6">
                                                            <label for="CreatedDate" class="col-form-label">CreatedDate</label>
                                                            <input type="hidden" class="form-control" id="CreatedDate" placeholder="CreatedDate" name="createdDate" value="${c.createdDate}">
                                                            <span id="CreatedDate" class="form-control">
                                                                <fmt:formatDate value="${c.createdDate}" pattern="dd-MM-yyyy" />
                                                            </span>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <label for="CreatedDate" class="col-form-label">Modified History</label>
                                                            <button type="button" class="accordion form-control">Show</button>
                                                            <div class="panel">
                                                                <p style="color: black">${c.modifiedHistory}</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>



                                            <button type="submit" class="btn btn-primary mr-2">Confirm Update</button>
                                            <a href="CustomerManagement">
                                                <button type="button" class="btn btn-dark">Cancel</button>
                                            </a>
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
        <script>
                                                            var acc = document.getElementsByClassName("accordion");
                                                            var i;

                                                            for (i = 0; i < acc.length; i++) {
                                                                acc[i].addEventListener("click", function () {
                                                                    this.classList.toggle("active");
                                                                    var panel = this.nextElementSibling;
                                                                    if (panel.style.maxHeight) {
                                                                        panel.style.maxHeight = null;
                                                                    } else {
                                                                        panel.style.maxHeight = panel.scrollHeight + "px";
                                                                    }
                                                                });
                                                            }
        </script>
    </body>
</html>