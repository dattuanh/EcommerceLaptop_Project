<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="news" class="Base.Dal.DaoDisplay" scope="request"></jsp:useBean>

    <script>
        $(document).ready(function () {
            $("#topSearch").on("keyup", function () {
                var search = $("#topSearch").val();
                if (search === null || $.isEmptyObject(search)) {
                    document.getElementById('list').innerHTML = "";
                } else {
                    $.ajax({
                        url: "Home",
                        data: {search: search},
                        type: "POST",
                        success: function (data) {
                            var searchList = document.getElementById('list');
                            searchList.innerHTML = data;
                            document.getElementById('clear').innerHTML = "";
                        }
                    });
                }
            });
        });

    </script>

    <div class="container-fluid">
<!--        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">FAQs</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Help</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Support</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>-->
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="Home" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold px-3 mr-1">
                            <img class="border border-white" id="output" src="images/News/${news.companyInformation.newsImage}" 
                             onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                             style="max-width: 20%"/></span>
                    ${news.companyInformation.newsHeading}</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <form action="SearchSerie" method="Get">
                <div class="input-group">
                    <input type="text" class="form-control" name="topSearch" id ="topSearch" placeholder="Search for products">
                    <span class="input-group-text bg-transparent text-primary">
                        <i class="fa fa-search"></i>
                    </span>
                </div>
                <ul class="list input-group align-items-center col-lg-11 col-11" id="list">

                </ul>
            </form>
        </div>
        <div class="col-lg-3 col-6 text-right">
<!--            <a href="" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge">0</span>
            </a>-->
            <a href="ViewCart" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
                        
            </a>

        </div>
    </div>
</div>