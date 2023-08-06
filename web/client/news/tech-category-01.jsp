<%-- 
    Document   : tech-catagory-01
    Created on : May 24, 2023, 11:30:11 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Base.Util.SlugConverter" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Site Metas -->
        <title>${ng.newsGroupName}</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="news/images/favicon.ico" type="image/x-icon" />
        <link rel="apple-touch-icon" href="news/images/apple-touch-icon.png">
        <base href="http://localhost:9999/EcommerceLaptopSale/"/>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Danh sách tin tức</a>
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


            <div class="page-title lb single-wrapper">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                            <h2><i class="fa fa-gears bg-orange"></i> ${ng.newsGroupName} <small class="hidden-xs-down hidden-sm-down"></small></h2>
                        </div><!-- end col -->
                        <div class="col-lg-4 col-md-4 col-sm-12 hidden-xs-down hidden-sm-down">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Blog</a></li>
                                <li class="breadcrumb-item active">${ng.newsGroupName}</li>
                            </ol>
                        </div><!-- end col -->                    
                    </div><!-- end row -->
                </div><!-- end container -->
            </div><!-- end page-title -->

            <section class="section">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
                            <div class="sidebar">

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

                        <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                            <div class="page-wrapper">
                                <div class="blog-grid-system">
                                    <div class="row">
                                        <c:forEach items="${newsBg}" var="a">
                                            <div class="col-md-6">
                                                <div class="blog-box">
                                                    <div class="post-media">
                                                        <a href="TechSingleControl/${SlugConverter.convertToSlug(a.newTitle.replaceAll(" ", "-"))}-${a.newId}-${a.newsGroupID.newsGroupID}-${a.createBy.accountId}.html" title="">
                                                            <img src="images/News/${a.newsImage}" alt="" class="img-fluid">
                                                            <div class="hovereffect">
                                                                <span></span>
                                                            </div><!-- end hover -->
                                                        </a>
                                                    </div><!-- end media -->
                                                    <div class="blog-meta big-meta">
                                                        <span class="color-orange"><a href="TechCategoryControl/${SlugConverter.convertToSlug(a.newsGroupID.newsGroupName.replaceAll(" ","-"))}-${a.newsGroupID.newsGroupID}.html" title="">${a.newsGroupID.newsGroupName}</a></span>
                                                        <h4><a href="TechSingleControl/${SlugConverter.convertToSlug(a.newTitle.replaceAll(" ", "-"))}-${a.newId}-${a.newsGroupID.newsGroupID}-${a.createBy.accountId}.html" title="">${a.newTitle}</a></h4>
                                                        <p>${a.newsHeading}...</p>
                                                        <small><a title="">${a.createdDate}</a></small>
                                                        <small><a href="NewsAuthorControl/${a.createBy.accountId}&${a.createBy.firstName.replaceAll(" ","-")}${a.createBy.lastName.replaceAll(" ","-")}" title="">by ${a.createBy.firstName}${a.createBy.lastName}</a></small>
                                                    </div><!-- end meta -->
                                                </div><!-- end blog-box -->
                                            </div><!-- end col -->
                                        </c:forEach>
                                    </div><!-- end row -->
                                </div><!-- end blog-grid-system -->

                            </div><!-- end page-wrapper -->

                            <hr class="invis3">
                        </div><!-- end col -->
                    </div><!-- end row -->
                </div><!-- end container -->
            </section>

            <%@ include file="../news/footer.jsp" %>


            <div class="dmtop">Scroll to Top</div>

        </div><!-- end wrapper -->

        <!-- Core JavaScript
        ================================================== -->
        <script src="js/jquery.min.js"></script>
        <script src="js/tether.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

    </body>
</html>
