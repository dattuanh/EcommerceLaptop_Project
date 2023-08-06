<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--<jsp:useBean id="dao" class="Base.Dal.DAO" scope="request" ></jsp:useBean>
<jsp:useBean id="daoCategory" class="Base.Dal.DaoCategory" scope="request" ></jsp:useBean>
<jsp:useBean id="daoSerie" class="Base.Dal.DaoSerie" scope="request" ></jsp:useBean>--%>
<jsp:useBean id="display" class="Base.Dal.DaoDisplay" scope="request"></jsp:useBean>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" 
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" 
          crossorigin="anonymous" referrerpolicy="no-referrer" />

<%
    String url = request.getRequestURI();
    boolean check = false;
    if(url.equals("/EcommerceLaptopSale/client/index.jsp") || url.equals("/EcommerceLaptopSale/")) {
        check = true;
    }
%>


<%
                if(check) {
%>
<div class="container-fluid mb-5">
    <%
                } else {%>
    <div class="container-fluid">
        <%
    }
        %>
        <div class="row border-top px-xl-5">
            <!--            <div class="col-lg-3 d-none d-lg-block">
                            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                                <h6 class="m-0">Categories</h6>
                                <i class="fa fa-angle-down text-dark"></i>
                            </a>
            <%
            if(check) {
            %>
            <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
            <%
                } else {%> 
            <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
            <%
}
            %>
            <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
            <c:forEach items="${daoCategory.allCategories}" var="c">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link" data-toggle="dropdown" onclick="displaySeris(${c.categoryID})">
                ${c.categoryName}
                <i class="fa fa-angle-down float-right mt-1"></i>
            </a>
            <div class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0 series" id="cate${c.categoryID}"></div>
        </div>
            </c:forEach>
        </div>
    </nav>
</div>-->
            <div class="col-lg-12">
                <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <a href="" class="text-decoration-none d-block d-lg-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">

                        <div class="navbar-nav mr-auto py-0">
                            <c:forEach items="${display.getAllNavBars()}" var="nav" varStatus="loop">
                                <c:if test="${display.getAllNavBarLevel_2(nav.newTitle).size()>0}">
                                    <div class="nav-item dropdown">
                                        <c:set var="navigation" value="${display.getNavBarLevel_1(nav.newTitle)}"/>
                                        <a href="${nav.newsContent}" class="nav-link dropdown-toggle" data-toggle="dropdown" >${navigation.newsGroupName}</a>
                                        <div class="dropdown-menu rounded-0 m-0">
                                            <c:forEach items="${display.getAllNavBarLevel_2(navigation.newsGroupID)}" var="n" varStatus="loop">
                                                <a href="${n.newsContent}" class="dropdown-item">${n.newTitle}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${display.getAllNavBarLevel_2(nav.newTitle).size()==0}">
                                    <a href="${nav.newsContent}" class="nav-item nav-link active">${display.getNavBarLevel_1(nav.newTitle).newsGroupName}</a>
                                </c:if>
                            </c:forEach>
                        </div>

                        <!--                        <div class="navbar-nav ml-auto py-0">
                                                    <div class="nav-item dropdown mr-5">
                                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                                                            <i class="fa-solid fa-user"></i>
                                                        </a>
                                                        <div class="dropdown-menu rounded-0 m-0">
                        <c:if test="${sessionScope.customer == null}">
                            <a href="Login" class="dropdown-item">Login</a>
                            <a href="Register" class="dropdown-item">Register</a>
                        </c:if>
                        <c:if test="${sessionScope.customer != null}">
                            <a href="Info" class="dropdown-item">Personal Info</a>
                            <a href="LogOut" class="dropdown-item">Log Out</a>
                        </c:if>
                    </div>
                </div>
            </div>-->
                        <div class="navbar-nav ml-auto py-0">
                            <c:if test="${sessionScope.customer == null}">
                                <a href="Login" class="dropdown-item">Login</a>
                                |
                                <a href="Register" class="dropdown-item">Register</a>
                            </c:if>
                            <c:if test="${sessionScope.customer != null}">
                                <a href="Info" class="dropdown-item">${sessionScope.customer.userName}</a>
                                |
                                <a href="LogOut" class="dropdown-item">Log Out</a>
                            </c:if>
                        </div>
                        
                    </div>
                </nav>
                <%
                    
                    if(check) {
                %>
                <jsp:include page="_slider.jsp" />
                <%
                    }
                %>
            </div>
        </div>
    </div>

    <!--    <script>
            function displaySeris(categoryId) {
                console.log($('.series'));
                if ($('#cate' + categoryId).hasClass('show')) {
                    $('.series').empty();
                    return;
                }
                $.ajax({
                    url: "CategoryNavbar",
                    data: {categoryId: categoryId},
                    type: "POST",
                    success: function (data) {
                        $('.series').empty();
                        var retrieve = JSON.parse(data);
                        for (let i = 0; i < retrieve.length; i++) {
                            var aTag = "<a href='SerieDetail?serieId=" + retrieve[i].productSeriId + "' class='dropdown-item'>" + retrieve[i].productSeriName.substring(0, 25) + "</a>";
                            $('.series').append(aTag);
                        }
                    }
                });
            }
        </script>-->