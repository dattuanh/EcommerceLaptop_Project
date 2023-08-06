<%-- 
    Document   : indexnews
    Created on : May 24, 2023, 11:23:14 AM
    Author     : MSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="Base.Util.SlugConverter" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Site Metas -->
        <title>Home</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <base href="http://localhost:9999/EcommerceLaptopSale/"/>
        <!-- Site Icons -->
        <link rel="shortcut icon" href="news/images/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="news/images/apple-touch-icon.png">

        <!-- Design fonts -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet"> 
        <!-- Bootstrap core CSS -->
        <link href="client/news/css/bootstrap.css" rel="stylesheet">

        <!-- FontAwesome Icons core CSS -->
        <link href="client/news/css/font-awesome.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="client/news/style.css" rel="stylesheet">

        <!-- Responsive styles for this template -->
        <link href="client/news/css/responsive.css" rel="stylesheet">

        <!-- Colors for this template -->
        <link href="client/news/css/colors.css" rel="stylesheet">

        <!-- Version Tech CSS for this template -->
        <link href="client/news/css/version/tech.css" rel="stylesheet">
        <link href="client/css/style.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .hidden{
                display: none!important;
            }
            .text-primary{
                color: white!important;
            }


        </style>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>

            function search(index) {
                var search = $("#search").val();
                var filter = $("#filter").val();
                var entry = $("#entry").val();
                console.log(entry);

                $.ajax({
                    url: "indexnews",
                    data: {search: search, filter: filter, index: index, entry: entry},
                    type: "POST",
                    success: function (data) {
                        var searchList = document.getElementById('content');
                        searchList.innerHTML = data;
                        document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());
                        document.querySelector('.check-all-submit-btn').classList.add('disabled');
                    }
                });
            }

            $(document).ready(function () {
//                document.addEventListener('DOMContentLoaded', deleteCheckedBoxes());

                $("#search").on("keyup", function () {
                    search(-1);
                });

                $("#entry").on("change", function () {
                    search(-1);
                });

                $('button[name="pageLink"]').click(function () {
                    var pageIndex = $(this).val();
                    search(pageIndex);
                });
            });

            //                $("#danhMuc").on("change", function () {
//                    var danhMuc = $("#danhMuc").val();
//                    console.log(danhMuc);
//                    document.getElementById('danhMucHidden').value = danhMuc;
//                    document.getElementById('formDanhMuc').submit();
//                });
        </script>
        
    </head>
    <body>

        <div id="wrapper">
            <header class="tech-header header">
                <div class="container-fluid">
                    <nav class="navbar navbar-toggleable-md fixed-top bg-inverse" style="color: #ffffff !important;
                         box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
                         background: rgba(0, 0, 0, 0) linear-gradient(to right, #000000 0px, #000000 100%) repeat scroll 0 0 !important ">
                        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon" style=""></span>
                        </button>
                        <a class="navbar-brand" href="Home"><h1 class="m-0 display-5 font-weight-semi-bold" style="color: white"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Computer</h1></a>
                        <div class="collapse navbar-collapse" id="navbarCollapse">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="indexnews">Trang chủ</a>
                                </li>
                                <li class="dropdown has-submenu">
                                    <a class="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Danh sách tin tức</a>
                                    <ul class="dropdown-menu" aria-labelledby="dropdown01">
                                        <c:forEach items="${newsGroup}" var="c">
                                            <a class="dropdown-item" href="TechCategoryControl/${SlugConverter.convertToSlug(c.newsGroupName.replaceAll(" ","-"))}-${c.newsGroupID}.html">${c.newsGroupName}</a>
                                        </c:forEach>

                                    </ul>
                                </li>                 
<!--                                <li class="nav-item">
                                    <a class="nav-link" href="indexnews">Contact Us</a>
                                </li>-->
                            </ul>

                        </div>
                    </nav>
                </div><!-- end container-fluid -->
            </header><!-- end market-header -->


            <section class="section first-section">
                <div class="container-fluid">
                    <div class="masonry-blog clearfix">


                        <c:forEach items="${onTopNews}" var="c">

                            <div class="second-slot">
                                <div class="masonry-box post-media">
                                    <img src="images/News/${c.newsImage}" alt="" class="img-fluid">
                                    <div class="shadoweffect">
                                        <div class="shadow-desc">
                                            <div class="blog-meta">
                                                <span class="bg-orange"><a href="TechCategoryControl/${SlugConverter.convertToSlug(c.newsGroupID.newsGroupName.replaceAll(" ","-"))}-${c.newsGroupID.newsGroupID}.html" title="">${c.newsGroupID.newsGroupName}</a></span>
                                                <h4><a href="TechSingleControl/${SlugConverter.convertToSlug(c.newTitle.replaceAll(" ", "-"))}-${c.newId}-${c.newsGroupID.newsGroupID}-${c.createBy.accountId}.html" title="">${c.newTitle}</a></h4>
                                                <small><a title="">${c.createdDate}</a></small>
                                                <small><a href="NewsAuthorControl/${c.createBy.accountId}&${c.createBy.firstName.replaceAll(" ","-")}${c.createBy.lastName.replaceAll(" ","-")}" title="">by ${c.createBy.firstName} ${c.createBy.lastName}</a></small>
                                            </div><!-- end meta -->
                                        </div><!-- end shadow-desc -->
                                    </div><!-- end shadow -->
                                </div><!-- end post-media -->
                            </div><!-- end second-side -->
                        </c:forEach>


                    </div><!-- end masonry -->
                </div>
            </section>
            <div class="row">
                <div class="col-md-2 d-flex"></div>
                <div class="col-md-2 d-flex">
                    <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                    <select class="form-control" id="entry" style="margin-right: 10px;">
                        <option value="2" ${entries == 2 ? 'selected' : ''}>2</option>
                        <option value="5" ${entries == 5 ? 'selected' : ''}>5</option>
                        <option value="10" ${entries == 10 ? 'selected' : ''}>10</option>
                        <option value="50" ${entries == 50 ? 'selected' : ''}>50</option>
                        <option value="100" ${entries == 100 ? 'selected' : ''}>100</option>
                    </select>
                </div>
                <div class="col-md-2 d-flex">
                    <i class="mdi mdi-filter-outline" style="font-size: 22px"></i>
                    <select class="form-control" id="filter" name="filter" required>
                        <c:if test="${not empty filter }">
                            <option value="${filter}" selected hidden>${filter}</option>
                        </c:if>
                        <option value="All">All</option>
                        <option value="Title">Title</option>
                        <option value="Heading">Heading</option>
                        <!-- <option value="NewsGroup">News Group</option> -->
                    </select>
                </div>
                <div class="col-lg-6 col-6 text-left d-flex">
                    <div class="input-group">
                        <input type="text" class="form-control" id="search" name="search" placeholder="Search News" value="${search}" autofocus>
                    </div>
                </div>
            </div>


            <section class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                            <div class="page-wrapper">

                                <div class="blog-top clearfix">
                                    <h4 class="pull-left">Tin tức<a href="#"><i class="fa fa-rss"></i></a></h4>
                                </div><!-- end blog-top -->

                                <div class="blog-list clearfix" id="content">
                                    <c:forEach items="${listNews}" var="n">
                                        <div class="blog-box row">
                                            <div class="col-md-4">
                                                <div class="post-media">
                                                    <a href="TechSingleControl/${SlugConverter.convertToSlug(n.newTitle.replaceAll(" ", "-"))}-${n.newId}-${n.newsGroupID.newsGroupID}-${n.createBy.accountId}.html">
                                                        <img src="images/News/${n.newsImage}" alt="" class="img-fluid">
                                                        <div class="hovereffect"></div>
                                                    </a>
                                                </div><!-- end media -->
                                            </div><!-- end col -->

                                            <div class="blog-meta big-meta col-md-8">
                                                <h4><a href="TechSingleControl/${SlugConverter.convertToSlug(n.newTitle.replaceAll(" ", "-"))}-${n.newId}-${n.newsGroupID.newsGroupID}-${n.createBy.accountId}.html">${n.newTitle}</a></h4>

                                                <p>${n.newsHeading}...</p>
                                                <small class="firstsmall"><a class="bg-orange" href="TechCategoryControl/${SlugConverter.convertToSlug(n.newsGroupID.newsGroupName.replaceAll(" ","-"))}-${n.newsGroupID.newsGroupID}.html" title="">${n.newsGroupID.newsGroupName}</a></small>
                                                <small><a href="" title="">${n.createdDate}</a></small>
                                                <small><a href="NewsAuthorControl/${n.createBy.accountId}&${n.createBy.firstName.replaceAll(" ","-")}${n.createBy.lastName.replaceAll(" ","-")}" title="">By ${n.createBy.firstName} ${n.createBy.lastName}</a></small>
                                            </div><!-- end meta -->
                                        </div><!-- end blog-box -->

                                        <hr class="invis">
                                    </c:forEach>

                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-start">
                                            <li class="page-item ${currentPage > 1 ? "" : "hidden"}">
                                                <a class="page-link" href="indexnews?index=${currentPage - 1}&entries=${entries}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <c:forEach begin="1" end="${endPage}" var="i">
                                                <li class="page-item ${currentPage == i ? "active" : ""} ">
                                                    <a class="page-link" href="indexnews?index=${i}&entries=${entries}">${i}
                                                    </a>
                                                </li>                                                </c:forEach>
                                            <li class="page-item ${currentPage < endPage ? "" : "hidden"}">
                                                <a class="page-link" href="indexnews?index=${currentPage + 1}&entries=${entries}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div><!-- end blog-list -->
                            </div><!-- end page-wrapper -->

                            <hr class="invis">

                            <div class="row">
                                <div class="col-md-12">
                                </div><!-- end col -->
                            </div><!-- end row -->
                        </div><!-- end col -->

                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                            <div class="sidebar">
                                <!--                            <div class="widget">
                                                                <div class="banner-spot clearfix">
                                                                    <div class="banner-img">
                                                                        <img src="upload/banner_07.jpg" alt="" class="img-fluid">
                                                                    </div> end banner-img 
                                                                </div> end banner 
                                                            </div> end widget -->

                                <div class="widget">
                                    <h2 class="widget-title">Tin mới nhất</h2>
                                    <div class="blog-list-widget">
                                        <c:forEach items="${recentNews}" var="r">
                                            <div class="list-group">
                                                <a href="TechSingleControl/${SlugConverter.convertToSlug(r.newTitle.replaceAll(" ", "-"))}-${r.newId}-${r.newsGroupID.newsGroupID}-${r.createBy.accountId}.html" class="list-group-item list-group-item-action flex-column align-items-start">
                                                    <div class="w-100 justify-content-between">
                                                        <img src="images/News/${r.newsImage}" alt="" class="img-fluid float-left">
                                                        <h5 class="mb-1">${r.newTitle}</h5>
                                                        <small>${r.createdDate}</small>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div><!-- end blog-list -->
                                </div><!-- end widget -->

                            </div><!-- end sidebar -->
                        </div><!-- end col -->
                    </div><!-- end row -->
                </div><!-- end container -->
            </section>
                                                    
            <%@ include file="../news/footer.jsp" %>

        </div><!-- end wrapper -->

        <!-- Core JavaScript
        ================================================== -->
        <script src="js/jquery.min.js"></script>
        <script src="js/tether.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

    </body>
</html>
