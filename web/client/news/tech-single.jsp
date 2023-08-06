<%-- 
    Document   : tech-singer
    Created on : May 24, 2023, 11:32:18 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Base.Util.SlugConverter" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <!--     Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!--     Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--     Site Metas -->
    <title>${c.newTitle}</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!--     Site Icons -->
    <link rel="shortcut icon" href="client/images/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="client/images/apple-touch-icon.png">
    <base href="http://localhost:9999/EcommerceLaptopSale/"/>
    <!--     Design fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet"> 

    <!--     Bootstrap core CSS -->
    <link href="client/news/css/bootstrap.css" rel="stylesheet">

    <!--     FontAwesome Icons core CSS -->
    <link href="client/news/css/font-awesome.min.css" rel="stylesheet">

    <!--     Custom styles for this template -->
    <link href="client/news/style.css" rel="stylesheet">

    <!--     Responsive styles for this template -->
    <link href="client/news/css/responsive.css" rel="stylesheet">

    <!--     Colors for this template -->
    <link href="client/news/css/colors.css" rel="stylesheet">

    <!--     Version Tech CSS for this template -->
    <link href="client/news/css/version/tech.css" rel="stylesheet">
    <link href="client/css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!--    [if lt IE 9]>-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <!--    <![endif]-->
    <style>
        .text-primary{
            color: white!important;
        }
    </style>

    <!-- Fancybox CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css">

        <!-- Fancybox JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js"></script>
    <script>
        $(document).ready(function () {
            $('p').find('img').each(function () {
                $(this).wrap('<a data-fancybox="gallery" href="' + $(this).attr('src') + '"></a>');
            });

            // Kích hoạt Fancybox
            $('[data-fancybox="gallery"]').fancybox({
                buttons: [
                    'zoom',
                        'slideShow',
                        'fullScreen',
                        'close'
                ],
                loop: true
            });
        });
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
                                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Danh sách tin tức</a>
                                <ul class="dropdown-menu" aria-labelledby="dropdown01">
                                    <c:forEach items="${newsGroup}" var="c">
                                        <a class="dropdown-item" href="TechCategoryControl/${SlugConverter.convertToSlug(c.newsGroupName.replaceAll(" ","-"))}-${c.newsGroupID}.html">${c.newsGroupName}</a>
                                    </c:forEach>

                                </ul>
                            </li>                 
                            <!--                            <li class="nav-item">
                                                            <a class="nav-link" href="indexnews">Contact Us</a>
                                                        </li>-->
                        </ul>
                    </div>
                </nav>
            </div><!-- end container-fluid -->
        </header><!-- end market-header -->


        <section class="section single-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                        <div class="page-wrapper">
                            <div class="blog-title-area text-center">
                                <ol class="breadcrumb hidden-xs-down">
                                    <li class="breadcrumb-item"><a href="indexnews">Home</a></li>
                                    <!--                                    <li class="breadcrumb-item"><a href="#">Blog</a></li>-->
                                    <li class="breadcrumb-item active">${c.newTitle}</li>
                                </ol>

                                <span class="color-orange"><a href="TechCategoryControl/${SlugConverter.convertToSlug(c.newsGroupID.newsGroupName.replaceAll(" ","-"))}-${c.newsGroupID.newsGroupID}.html" title="">${c.newsGroupID.newsGroupName}</a></span>

                                <h3>${c.newTitle}</h3>

                                <div class="blog-meta big-meta">
                                    <small><a>${c.createdDate}</a></small>
                                    <small><a href="NewsAuthorControl/${c.createBy.accountId}&${c.createBy.firstName.replaceAll(" ","-")}${c.createBy.lastName.replaceAll(" ","-")}" title="">${c.createBy.firstName}${c.createBy.lastName}</a></small>
                                </div><!-- end meta -->

                            </div><!-- end title -->

                            <div class="single-post-media">
                                <img src="images/News/${c.newsImage}" alt="" class="img-fluid">
                            </div><!-- end media -->

                            <div class="blog-content ">  
                                <div class="pp">
                                    <p>${c.newsContent}</p>
                                </div><!-- end pp -->


                                <div class="pp">

                                </div><!-- end pp -->
                            </div><!-- end content -->

                            <div class="blog-title-area">
                                <div class="tag-cloud-single">
                                    <span>Groups</span>
                                    <c:forEach items="${newsGroup}" var="c">
                                        <small><a href="TechCategoryControl/${SlugConverter.convertToSlug(c.newsGroupName.replaceAll(" ","-"))}-${c.newsGroupID}.html" title="">${c.newsGroupName}</a></small>
                                        </c:forEach>
                                </div><!-- end meta -->

                            </div><!-- end title -->



                            <hr class="invis1">

                            <div class="custombox prevnextpost clearfix">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="blog-list-widget">
                                            <div class="list-group">
                                                <c:if test="${not empty prevNews}">
                                                    <a href="TechSingleControl/${SlugConverter.convertToSlug(prevNews.newTitle.replaceAll(" ", "-"))}-${prevNews.newId}-${prevNews.newsGroupID.newsGroupID}-${prevNews.createBy.accountId}.html" class="list-group-item list-group-item-action flex-column align-items-start">
                                                        <div class="w-100 justify-content-between text-right">
                                                            <img src="images/News/${prevNews.newsImage}" alt="" class="img-fluid float-right">
                                                            <h5 class="mb-1">${prevNews.newTitle}</h5>
                                                            <small>Prev Post</small>
                                                        </div>
                                                    </a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div><!-- end col -->

                                    <div class="col-lg-6">
                                        <div class="blog-list-widget">
                                            <div class="list-group">
                                                <c:if test="${not empty nextNews}">
                                                    <a href="TechSingleControl/${SlugConverter.convertToSlug(nextNews.newTitle.replaceAll(" ", "-"))}-${nextNews.newId}-${nextNews.newsGroupID.newsGroupID}-${nextNews.createBy.accountId}.html" class="list-group-item list-group-item-action flex-column align-items-start">
                                                        <div class="w-100 justify-content-between">
                                                            <img src="images/News/${nextNews.newsImage}" alt="" class="img-fluid float-left">
                                                            <h5 class="mb-1">${nextNews.newTitle}</h5>
                                                            <small>Next Post</small>
                                                        </div>
                                                    </a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div><!-- end col -->
                                </div><!-- end row -->
                            </div><!-- end author-box -->

                            <hr class="invis1">

                            <div class="custombox authorbox clearfix">
                                <h4 class="small-title">Biên soạn bởi</h4>
                                <div class="row">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                        <img src="images/Account/${acc.image}" alt="" class="img-fluid rounded-circle"> 
                                    </div><!-- end col -->

                                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                        <h4><a href="NewsAuthorControl/${acc.accountId}&${acc.firstName.replaceAll(" ","-")}${acc.lastName.replaceAll(" ","-")}">${acc.firstName} ${acc.lastName}</a></h4>
                                        <p>Hi I am ${acc.firstName} ${acc.lastName} come to <a href="#">visit my website</a>Thanks for always support us !</p>


                                    </div><!-- end col -->
                                </div><!-- end row -->
                            </div><!-- end author-box -->

                            <hr class="invis1">

                            <div class="custombox clearfix">
                                <h4 class="small-title">Bạn có thể quan tâm </h4>
                                <div class="row">
                                    <c:forEach items="${newsbg}" var="c">

                                        <div class="col-lg-6">
                                            <div class="blog-box">
                                                <div class="post-media">
                                                    <a href="TechSingleControl/${SlugConverter.convertToSlug(c.newTitle.replaceAll(" ", "-"))}-${c.newId}-${c.newsGroupID.newsGroupID}-${c.createBy.accountId}.html" title="">
                                                        <img src="images/News/${c.newsImage}" alt="" class="img-fluid">
                                                        <div class="hovereffect">
                                                            <span class=""></span>
                                                        </div><!-- end hover -->
                                                    </a>
                                                </div><!-- end media -->
                                                <div class="blog-meta">
                                                    <h4><a href="TechSingleControl/${SlugConverter.convertToSlug(c.newTitle.replaceAll(" ", "-"))}-${c.newId}-${c.newsGroupID.newsGroupID}-${c.createBy.accountId}.html" title="">${c.newTitle}</a></h4>
                                                    <small><a href="blog-category-01" title="">${c.createBy.firstName} ${c.createBy.lastName}</a></small>
                                                    <small><a title="">${c.createdDate}</a></small>
                                                </div><!-- end meta -->
                                            </div><!-- end blog-box -->
                                        </div><!-- end col -->
                                    </c:forEach>
                                </div><!-- end row -->
                            </div><!-- end custom-box -->

                            <hr class="invis1">


                            <hr class="invis1">


                        </div><!-- end page-wrapper -->
                    </div><!-- end col -->

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
