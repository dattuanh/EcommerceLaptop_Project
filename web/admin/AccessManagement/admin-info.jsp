<%-- 
    Document   : user-info
    Created on : Jun 28, 2023, 10:03:37 AM
    Author     : Giang Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Corona Admin</title>
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
    </head>
    <body>

        <div class="container-scroller">
            <!-- partial:partials/_sidebar.html -->
            <jsp:include page="../partials/_sidebar.jsp"></jsp:include>
                <!-- partial -->
                <div class="container-fluid page-body-wrapper">
                    <!-- partial:partials/_navbar.jsp -->
                <jsp:include page="../partials/_navbar.jsp"></jsp:include>
                    <div class="main-panel">
                        <div class="content-wrapper">
                            <div class="page-header">
                                <h3 class="page-title"> Profile </h3>
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#">Sample Pages</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Profile</li>
                                    </ol>
                                </nav>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <div class="border-bottom text-center pb-4">
                                                        <img src="../../../assets/images/faces/face12.jpg" alt="profile" class="img-lg rounded-circle mb-3">
                                                        <p>Bureau Oberhaeuser is a design bureau focused on Information- and Interface Design. </p>
                                                        <div class="d-flex justify-content-between">
                                                            <button class="btn btn-success">Hire Me</button>
                                                            <button class="btn btn-success">Follow</button>
                                                        </div>
                                                    </div>
                                                    <div class="border-bottom py-4">
                                                        <p>Skills</p>
                                                        <div>
                                                            <label class="badge badge-outline-light">Chalk</label>
                                                            <label class="badge badge-outline-light">Hand lettering</label>
                                                            <label class="badge badge-outline-light">Information Design</label>
                                                            <label class="badge badge-outline-light">Graphic Design</label>
                                                            <label class="badge badge-outline-light">Web Design</label>
                                                        </div>
                                                    </div>
                                                    <div class="border-bottom py-4">
                                                        <div class="d-flex mb-3">
                                                            <div class="progress progress-md flex-grow">
                                                                <div class="progress-bar bg-primary" role="progressbar" aria-valuenow="55" style="width: 55%" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                        <div class="d-flex">
                                                            <div class="progress progress-md flex-grow">
                                                                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="75" style="width: 75%" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="py-4">
                                                        <p class="clearfix">
                                                            <span class="float-left"> Status </span>
                                                            <span class="float-right text-muted"> Active </span>
                                                        </p>
                                                        <p class="clearfix">
                                                            <span class="float-left"> Phone </span>
                                                            <span class="float-right text-muted"> 006 3435 22 </span>
                                                        </p>
                                                        <p class="clearfix">
                                                            <span class="float-left"> Mail </span>
                                                            <span class="float-right text-muted"> Jacod@testmail.com </span>
                                                        </p>
                                                        <p class="clearfix">
                                                            <span class="float-left"> Facebook </span>
                                                            <span class="float-right text-muted">
                                                                <a href="#">David Grey</a>
                                                            </span>
                                                        </p>
                                                        <p class="clearfix">
                                                            <span class="float-left"> Twitter </span>
                                                            <span class="float-right text-muted">
                                                                <a href="#">@davidgrey</a>
                                                            </span>
                                                        </p>
                                                    </div>
                                                    <button class="btn btn-primary btn-block">Preview</button>
                                                </div>
                                                <div class="col-lg-8">
                                                    <div class="d-flex justify-content-between">
                                                        <div>
                                                            <h3>David Grey. H</h3>
                                                            <div class="d-flex align-items-center">
                                                                <h5 class="mb-0 me-2 text-muted">Canada</h5>
                                                                <div class="br-wrapper br-theme-css-stars"><select id="profile-rating" name="rating" autocomplete="off" style="display: none;">
                                                                        <option value="1">1</option>
                                                                        <option value="2">2</option>
                                                                        <option value="3">3</option>
                                                                        <option value="4">4</option>
                                                                        <option value="5">5</option>
                                                                    </select><div class="br-widget"><a href="#" data-rating-value="1" data-rating-text="1" class="br-selected br-current"></a><a href="#" data-rating-value="2" data-rating-text="2"></a><a href="#" data-rating-value="3" data-rating-text="3"></a><a href="#" data-rating-value="4" data-rating-text="4"></a><a href="#" data-rating-value="5" data-rating-text="5"></a></div></div>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <button class="btn btn-outline-secondary btn-icon">
                                                                <i class="mdi mdi-comment-processing"></i>
                                                            </button>
                                                            <button class="btn btn-primary">Request</button>
                                                        </div>
                                                    </div>
                                                    <div class="mt-4 py-2 border-top border-bottom">
                                                        <ul class="nav profile-navbar">
                                                            <li class="nav-item">
                                                                <a class="nav-link" href="#">
                                                                    <i class="mdi mdi-account-outline"></i> Info </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link active" href="#">
                                                                    <i class="mdi mdi-newspaper"></i> Feed </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" href="#">
                                                                    <i class="mdi mdi-calendar"></i> Agenda </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" href="#">
                                                                    <i class="mdi mdi-attachment"></i> Resume </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="profile-feed">
                                                        <div class="d-flex align-items-start profile-feed-item">
                                                            <img src="../../../assets/images/faces/face13.jpg" alt="profile" class="img-sm rounded-circle">
                                                            <div class="ms-4">
                                                                <h6> Mason Beck <small class="ms-4 text-muted"><i class="mdi mdi-clock me-1"></i>10 hours</small>
                                                                </h6>
                                                                <p> There is no better advertisement campaign that is low cost and also successful at the same time. </p>
                                                                <p class="small text-muted mt-2 mb-0">
                                                                    <span>
                                                                        <i class="mdi mdi-star me-1"></i>4 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-comment me-1"></i>11 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-reply"></i>
                                                                    </span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="d-flex align-items-start profile-feed-item">
                                                            <img src="../../../assets/images/faces/face16.jpg" alt="profile" class="img-sm rounded-circle">
                                                            <div class="ms-4">
                                                                <h6> Willie Stanley <small class="ms-4 text-muted"><i class="mdi mdi-clock me-1"></i>10 hours</small>
                                                                </h6>
                                                                <img src="../../../assets/images/samples/1280x768/12.jpg" alt="sample" class="rounded mw-100">
                                                                <p class="small text-muted mt-2 mb-0">
                                                                    <span>
                                                                        <i class="mdi mdi-star me-1"></i>4 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-comment me-1"></i>11 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-reply"></i>
                                                                    </span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                        <div class="d-flex align-items-start profile-feed-item">
                                                            <img src="../../../assets/images/faces/face19.jpg" alt="profile" class="img-sm rounded-circle">
                                                            <div class="ms-4">
                                                                <h6> Dylan Silva <small class="ms-4 text-muted"><i class="mdi mdi-clock me-1"></i>10 hours</small>
                                                                </h6>
                                                                <p> When I first got into the online advertising business, I was looking for the magical combination that would put my website into the top search engine rankings </p>
                                                                <img src="../../../assets/images/samples/1280x768/5.jpg" alt="sample" class="rounded mw-100">
                                                                <p class="small text-muted mt-2 mb-0">
                                                                    <span>
                                                                        <i class="mdi mdi-star me-1"></i>4 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-comment me-1"></i>11 </span>
                                                                    <span class="ms-2">
                                                                        <i class="mdi mdi-reply"></i>
                                                                    </span>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- content-wrapper ends -->
                        <!-- partial:../../partials/_footer.html -->
                        <footer class="footer">
                            <div class="d-sm-flex justify-content-center justify-content-sm-between">
                                <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2021 <a href="https://www.bootstrapdash.com/" target="_blank">BootstrapDash</a>. All rights reserved.</span>
                                <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted &amp; made with <i class="mdi mdi-heart text-danger"></i></span>
                            </div>
                        </footer>
                        <!-- partial -->
                    </div>

                <jsp:include page="../partials/_footer.jsp"></jsp:include>
                <!-- partial -->
            </div>
            <!-- main-panel ends -->
        </div>
        <!-- page-body-wrapper ends -->

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
    </body>
</html>
