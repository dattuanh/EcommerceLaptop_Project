<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="slider" class="Base.Dal.DAOClient" scope="request"></jsp:useBean>
<%@ page import="Base.Util.SlugConverter" %>

<c:if test="${not empty slider.sliderNews}">
    <c:set var="firstNews" value="${slider.sliderNews[0]}" />
    <c:set var="secondNews" value="${slider.sliderNews[1]}" />
</c:if>

<div id="header-carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">

        <c:forEach var="news" items="${slider.sliderNews}">
            <div class="carousel-item" style="height: 410px;">
                <img class="img-fluid" src="images/News/${news.newsImage}" alt="Image">
                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    <div class="p-3" style="max-width: 700px;">
                        <h4 class="text-light text-uppercase font-weight-medium mb-3"><a href="TechSingleControl/${SlugConverter.convertToSlug(news.newTitle.replaceAll(" ", "-"))}-${news.newId}-${news.newsGroupID.newsGroupID}-${news.createBy.accountId}.html" style="color: #ffffff">${news.newTitle}</a></h4>
                        <h3 class="display-4 text-white font-weight-semi-bold mb-4">Fashionable Laptop</h3>
                        <a href="SerieList" class="btn btn-light py-2 px-3">Shop Now</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        
    </div>
    <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
        <div class="btn btn-dark" style="width: 45px; height: 45px;">
            <span class="carousel-control-prev-icon mb-n2"></span>
        </div>
    </a>
    <a class="carousel-control-next" href="#header-carousel" data-slide="next">
        <div class="btn btn-dark" style="width: 45px; height: 45px;">
            <span class="carousel-control-next-icon mb-n2"></span>
        </div>
    </a>
</div>

<script>
    var items = document.getElementsByClassName('carousel-item');

    for (let i = 0; i < items.length; i++) {
        console.log(items[i]);
        if (i == items.length - 1) {

            change(items[i], items[0]);
//                        setTimeout(change(items[i], items[0]), 5000);
            i = 0;
        } else {
            change(items[i], items[i + 1])
//                        setTimeout(change(items[i], items[i + 1]), 5000);
        }
        sleep(2000);
    }

    function change(x, y) {
        x.classList.remove("active");
        y.classList.add("active");
    }
</script>