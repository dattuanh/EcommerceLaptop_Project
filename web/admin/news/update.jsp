<%-- 
    Document   : edit
    Created on : May 12, 2023, 11:10:14 PM
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
        <title>New News </title>
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

        <jsp:include page="../assets/shared/jsp/tinymce.jsp" />
        <script>
            tinymcee('../../web/images/News/');
        </script>
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
                                    <div class="card" style="background-color: aliceblue;">
                                        <div class="card-body">
                                            <form class="forms-sample" action="UpdateNews?id=${c.newId}" method="post" enctype="multipart/form-data">

                                            <div class="row">
                                                <div class="col-sm-10"></div>
                                                <div class="col-sm-2">
                                                    <button type="submit" class="btn btn-primary mr-2">Update</button>
                                                    <a href="NewsManagement">
                                                        <button type="button" class="btn btn-secondary">Cancel</button>
                                                    </a>
                                                </div>
                                            </div>

                                            <div class="row mt-3">
                                                <div class="col-sm-8">
                                                    <div class="form-group row">
                                                        <div class="col-sm-2">
                                                            <label for="newTitle" class="btn btn-primary col-form-label ">Title</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" id="newTitle" placeholder="title" name="title" value="${c.newTitle}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group row">
                                                        <div class="col-sm-2">
                                                            <label for="newsHeading" class="btn btn-primary col-form-label">Heading</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <input type="text" class="form-control" id="newsHeading" placeholder="heading" name="heading" value="${c.newsHeading}">
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <!--<label for="newsContent" class="col-sm-3 col-form-label">Content</label>-->
                                                        <textarea id="editor" name="content">${c.newsContent}</textarea>
                                                    </div>
                                                </div>

                                                <div class="col-sm-4">
                                                    <div class="row" 
                                                         style="background-color: #d3d3d347; border-radius: 10px">

                                                        <div class="form-group row mt-3" style="align-items: baseline">
                                                            <div class="col-sm-6">
                                                                <label for="newsGroupId" class="btn btn-primary col-form-label">News Group</label>
                                                            </div>
                                                            <div class="dropdown col-sm-6">
                                                                <select class="btn btn-outline-primary dropdown-toggle" id="newsGroupId" name="newsGroupId">
                                                                    <c:forEach var="ng" items="${newsGroupList}">
                                                                        <option class="dropdown-item" value="${ng.newsGroupID}"
                                                                                ${c.newsGroupID.newsGroupID == ng.newsGroupID ? "selected" : ""}>
                                                                            ${ng.newsGroupName}
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>

                                                        <div class="form-group row" style="align-items: baseline">
                                                            <div class="col-sm-6">
                                                                <label for="IsPresent" class="btn btn-primary col-form-label">Present in Client</label>
                                                            </div>

                                                            <div class="dropdown col-sm-6">
                                                                <select class="btn btn-outline-primary dropdown-toggle" id="IsPresent" name="isPresent">
                                                                    <option value="1" ${c.isPresent ? "selected" : ""}>Show</option>
                                                                    <option value="0" ${!c.isPresent ? "selected" : ""}>Not Show</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row" style="align-items: baseline">
                                                            <div class="col-sm-6">
                                                                <label for="IsSlider" class="btn btn-primary col-form-label">Present in Slider</label>
                                                            </div>

                                                            <div class="dropdown col-sm-6">
                                                                <select class="btn btn-outline-primary dropdown-toggle" id="IsSlider" name="isSlider">
                                                                    <option value="1" ${c.isSlider ? "selected" : ""}>Show</option>
                                                                    <option value="0" ${!c.isSlider ? "selected" : ""}>Not Show</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div style="background-color: #d3d3d347; border-radius: 10px" class="row mt-3">
                                                        <div class="form-group mt-3">
                                                            <label for="newsImage" class="btn btn-primary col-form-label mb-3">Image</label>
                                                            <input type="file" class="form-control col-sm-10" id="newsImage" 
                                                                   placeholder="link" name="image" value="${c.newsImage}"  onchange="loadFile(event)">
                                                            <img id="output" src="images/News/${c.newsImage}" 
                                                                 onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                                                                 style="max-width: 100%; max-height: 300px" />
                                                            <script>
                                                                var loadFile = function (event) {
                                                                    var output = document.getElementById('output');
                                                                    output.src = URL.createObjectURL(event.target.files[0]);
                                                                    console.log(output);
                                                                    output.onload = function () {
                                                                        URL.revokeObjectURL(output.src); // free memory
                                                                    };
                                                                };
                                                            </script>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="date-input" class="col-form-label">Create date:</label>
                                                    <input type="hidden" name="created" value="${c.createdDate}">
                                                    <span class="form-control">
                                                        <fmt:formatDate value="${c.createdDate}" pattern="dd-MM-yyyy" />
                                                    </span>
                                                </div>

                                                <div class="form-group">
                                                    <label for="createBy" class="col-form-label">Created By</label>
                                                    <input type="hidden" class="form-control" id="createBy" placeholder="Id" name="createdby" value="${c.createBy.accountId}">
                                                    <span class="form-control">${c.createBy.userName}</span>
                                                </div>

                                                <div class="form-group">
                                                    <label for="modifiedDate" class="col-form-label">Last Modified</label>
                                                    <span class="form-control">
                                                        <fmt:formatDate value="${c.modifiedDate}" pattern="dd-MM-yyyy" />
                                                    </span>
                                                </div>

                                                <div class="form-group">
                                                    <label for="modifiedBy" class="col-form-label">Last Modified By</label>
                                                    <span class="form-control">${c.modifiedBy.userName}</span>
                                                </div>
                                            </div>

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