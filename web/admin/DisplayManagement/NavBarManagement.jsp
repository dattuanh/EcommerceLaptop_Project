<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="dao" class="Base.Dal.DaoDisplay" scope="request"></jsp:useBean>
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
            <!-- End layout styles -->
            <link rel="shortcut icon" href="admin/assets/images/favicon.png" />
        <jsp:include page="../Util/Format.jsp"></jsp:include>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script>
                var draggedElement = null;
                var items;
                var items1;
                var navId;

                function handleDragStart(e) {
                    this.style.opacity = "0.4";
                    draggedElement = this;
//                    alert("HI");

                    e.dataTransfer.effectAllowed = "move";
                    e.dataTransfer.setData("item", this.innerHTML);
                }

                function handleDragStart1(e) {
                    this.style.opacity = "0.4";
                    draggedElement = this;
//alert("HE");
                    e.dataTransfer.effectAllowed = "move";
                    e.dataTransfer.setData("item1", this.innerHTML);
                }

                function handleDragOver(e) {
                    if (e.preventDefault)
                        e.preventDefault();

                    e.dataTransfer.dropEffect = "move";
                    return false;
                }

                function handleDragEnter(e) {
                    this.classList.add("dragover");
                }

                function handleDragLeave(e) {
                    this.classList.remove("dragover");
                }

                function handleDrop(e) {
                    if (e.stopPropagation)
                        e.stopPropagation();

                    if (draggedElement !== this) {
                        draggedElement.innerHTML = this.innerHTML;
                        var replacedItem = e.dataTransfer.getData("item");
                        this.innerHTML = replacedItem;
                    }
                    document.getElementById("btnSubmit").disabled = false;
                    return false;
                }

                function handleDrop1(e) {
                    if (e.stopPropagation)
                        e.stopPropagation();
                    if (draggedElement !== this) {
                        draggedElement.innerHTML = this.innerHTML;
                        var replacedItem = e.dataTransfer.getData("item1");
                        this.innerHTML = replacedItem;
                    }

                    return false;
                }

                function handleDragEnd(e) {
                    this.style.opacity = "1";

                    items.forEach(function (item) {
                        item.classList.remove("dragover");
                    });
                }

                window.onload = function () {
                    let items = document.querySelectorAll(".editNav-item .box");

                    items.forEach(function (item) {
                        item.addEventListener("dragstart", handleDragStart);
                        item.addEventListener("dragenter", handleDragEnter);
                        item.addEventListener("dragover", handleDragOver);
                        item.addEventListener("dragleave", handleDragLeave);
                        item.addEventListener("drop", handleDrop);
                        item.addEventListener("dragend", handleDragEnd);
                    });

                    let items1 = document.querySelectorAll(".editNav-item1 .box1");

                    items1.forEach(function (item1) {
                        item1.addEventListener("dragstart", handleDragStart1);
                        item1.addEventListener("dragenter", handleDragEnter);
                        item1.addEventListener("dragover", handleDragOver);
                        item1.addEventListener("dragleave", handleDragLeave);
                        item1.addEventListener("drop", handleDrop1);
                        item1.addEventListener("dragend", handleDragEnd);
                    });
                };

                function removeNavLevel1(id) {
                    document.getElementById("removeNavId1").value = id;
                    document.getElementById("removeNavLevel1").submit();

                }
                
                function removeNavLevel2(id) {
                    document.getElementById("removeNavId2").value = id;
                    document.getElementById("removeNavLevel2").submit();

                }

                function saveName() {
                    var parents = document.getElementById("level2").querySelectorAll(".swim-lane");
                    for (let i = 0; i < parents.length; i++) {
                        var child = parents[i].querySelectorAll("input");
                        if (child.length > 0) {
                            for (let j = 0; j < child.length; j++) {
                                child[j].setAttribute("name", parents[i].id + "[]");
                            }
                        }
                    }
                }
            </script>
            <style>
                /* ---- RESET/BASIC STYLING ---- */

                /* ---- FORM ---- */

                /* ---- BOARD ---- */
                .heading {
                    color: black;
                }
                .lanes {
                    display: flex;
                    align-items: flex-start;
                    justify-content: start;
                    gap: 16px;

                    padding: 24px 32px;
                }

                .swim-lane {
                    display: flex;
                    flex-direction: column;
                    gap: 12px;

                    background: #f4f4f4;
                    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.25);

                    padding: 12px;
                    border-radius: 4px;
                    min-height: 120px;

                    flex-shrink: 0;
                }

                .task {
                    background: white;
                    color: black;
                    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.15);

                    padding: 12px;
                    border-radius: 4px;

                    font-size: 16px;
                    cursor: move;
                }

                .is-dragging {
                    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.25);
                    background: rgb(50, 50, 50);
                    color: white;
                }
            </style>
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
                                            <div class="row">
                                                <h3 class="card-title col-10">Navigation Bar Edit</h3>
                                            <h9 style="color: #cccccc"> This page allow user with roles to edit the display of the navigation bar</h9>
                                            <input type="hidden" id="serieId" name="serieId" value="${selectedSerie.productSeriId}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-success shadow-none active" id="infor-tab" data-bs-toggle="tab" data-bs-target="#infor" type="button" role="tab" aria-controls="infor" aria-selected="true"><h6>Navigation Edit Level 1</h6></button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link btn btn-secondary shadow-none" id="history-tab" data-bs-toggle="tab" data-bs-target="#history" type="button" role="tab" aria-controls="infor" aria-selected="true"><h6>Navigation Edit Level 2</h6></button>
                            </li>
                        </ul>
                        <div class="tab-content card" id="myTabContent">
                            <div class="tab-pane fade show active" id="infor" role="tabpanel" aria-labelledby="home-tab">
                                <form class="form-group row" action="CreateNavLevel1" method="POST" style="padding: auto auto">
                                    <label class="col-sm-12 col-form-label"><h3>New Navigation Button</h3> </label>
                                    <div class="col-sm-1">
                                        <label for="Link" class="col-form-label"><h5>Name: </h5> </label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="navName" placeholder="Name" name="navName">
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="Link" class="col-form-label"><h5>Link: </h5> </label>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="link" placeholder="http://localhost:9999/EcommerceLaptopSale/..." name="link" value="http://localhost:9999/EcommerceLaptopSale/">
                                    </div>
                                    <input type="hidden" name="pos" value="${dao.getAllNavBars().size()}">
                                    <div class="col-sm-2">
                                        <button type="submit" class="btn btn-primary">Add navigation</button>
                                    </div>
                                </form>
                                <form action="UpdateNavLevel1" method="POST">
                                    <div id="simple-list" class="row">
                                        <label class="col-sm-12 col-form-label">
                                            <h3>Navigation Edit Level 1</h3>
                                            <h9 style="color: #cccccc"> Drag and Drop Button to rearrange order</h9>
                                        </label>
                                        <div class="nav nav-tabs editNav-item" style="padding-left: 50px; border: none; margin-top: 50px;">
                                            <c:forEach items="${dao.getAllNavBars()}" var="nav" varStatus="loop">
                                                <div id="${nav.newId}" draggable="true" class="box" style="border:">
                                                    <div class="nav-item nav-link btn btn-light shadow-none active" >
                                                        <input type="hidden" name="Id[]" value="${nav.newId}">
                                                        <h6>${dao.getNavBarLevel_1(nav.newTitle).newsGroupName}
                                                            <span onclick="removeNavLevel1(${nav.newId})" style=" margin-left: 20px; position: relative" class="close">&times;</span>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div style="margin-top: 50px">
                                        <a href="DisplayManagement"><button type="button" class="btn btn-primary">Cancel</button></a>
                                        <button id="btnSubmit" disabled="true" type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </form>
                                <form id="removeNavLevel1" hidden action="DeleteNavLevel1" method="POST">
                                    <input type="hidden" id="removeNavId1" name="removeNavId" value="">
                                </form>
                            </div>
                            <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="history-tab">
                                <form class="form-group row" id="todo-form">
                                    <label class="col-sm-12 col-form-label"><h3>New Sub Navigation Button</h3> </label>
                                    <div class="col-sm-1">
                                        <label for="Link" class="col-form-label"><h5>Name: </h5> </label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" placeholder="New TODO..." id="todo-input" name="todo-input"/>
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="Link" class="col-form-label"><h5>Link: </h5> </label>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="todo-link" placeholder="http://localhost:9999/EcommerceLaptopSale/..." name="link" value="http://localhost:9999/EcommerceLaptopSale/">
                                    </div>
                                    <input type="hidden" name="pos" value="${dao.getAllNavBarLevel_1().size()}">
                                    <div class="col-sm-2">
                                        <button type="submit" class="btn btn-primary">Add navigation</button>
                                    </div>
                                </form>
                                <div id="simple-list" >
                                    <label class="row col-form-label">
                                        <h3 class="col-sm-12">Navigation Edit Level 2</h3>
                                        <h9 class="col-sm-12" style="color: #cccccc"> Drag and Drop Button to rearrange order</h9>
                                    </label>
                                    <form action="UpdateNavLevel2" method="POST">

                                        <div id="level2" class="lanes row">
                                            <div id="todo-lane" hidden="true" class="col-2">

                                            </div>
                                            <c:forEach items="${dao.getAllNavBars()}" var="nav" varStatus="loop">
                                                <div id="${nav.newId}" class="swim-lane col-2">
                                                    <c:set var="navigation" value="${dao.getNavBarLevel_1(nav.newTitle)}"/>
                                                    <h3 class="heading">${navigation.newsGroupName}</h3>
                                                    <c:forEach items="${dao.getAllNavBarLevel_2(navigation.newsGroupID)}" var="n" varStatus="loop">
                                                        <p class="task" draggable="true">
                                                            ${n.newTitle}
                                                            <span onclick="removeNavLevel2(${n.newId})" class="close">&times;</span>
                                                            <input type="hidden" name="${nav.newId}[]" value="${n.newId}">
                                                        </p>
                                                    </c:forEach>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div style="margin-top: 50px">
                                            <input type="hidden" id="selected-nav-id" name="selectedNavId">
                                            <button id="delete-button" disabled class="btn btn-primary delete-button" type="submit">Delete</button>
                                            <button type="submit" onclick="saveName()" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form>
                                    <form id="removeNavLevel2" hidden action="DeleteNavLevel2" method="POST">
                                        <input type="hidden" id="removeNavId2" name="removeNavId" value="">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="admin/assets/shared/js/delete-confirm.js"></script>

<script src="admin/assets/shared/js/kanban.js"></script>
<script src="admin/assets/shared/js/Todo.js"></script>

<script>
                                                function ShowHidePass(obj) {
                                                    var lengthOfText = document.getElementById("i" + obj).style.display !== "none";
                                                    if (lengthOfText) {
                                                        document.getElementById("p" + obj).style.display = "inline";
                                                        document.getElementById("i" + obj).style.display = "none";
                                                    } else {
                                                        document.getElementById("p" + obj).style.display = "none";
                                                        document.getElementById("i" + obj).style.display = "inline";
                                                    }
                                                }
</script>
<!-- End custom js for this page -->
</body>
</html>
