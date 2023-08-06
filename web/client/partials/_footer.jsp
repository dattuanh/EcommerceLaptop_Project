<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="news" class="Base.Dal.DaoDisplay" scope="request"></jsp:useBean>

    <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
        <div class="row px-xl-5 pt-5">
            <div class=" row col-lg-9 col-md-12">
                <!--<div class="row">-->
                <div class="col-md-4 mb-5">
                    <h1 style="display:inline-block" class="mb-4 display-5 font-weight-semi-bold inline">
                        <span class="text-primary font-weight-bold px-3 mr-1">
                            <img class="border border-white" id="output" src="images/News/${news.companyInformation.newsImage}" 
                             onerror="this.onerror=null;this.src='images/others/img-14.jpg';" 
                             style="max-width: 20%"/>
                    </span>
                    ${news.companyInformation.newsHeading}
                </h1>
                <p class="mb-0">${news.companyInformation.newsContent}</p>
            </div>
            <c:forEach var="nav" items="${news.getAllFooters()}">
                <div class="col mb-5">
                    <c:set var="footer" value="${news.getFooterLevel_1(nav.newTitle)}"/>
                    <h5 class="font-weight-bold text-dark mb-4">${footer.newsGroupName}</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <c:forEach items="${news.getAllFooterLevel_2(footer.newsGroupID)}" var="n" varStatus="loop">
                            <a class="text-dark mb-2" href="${n.newsContent}"><i class="fa fa-angle-right mr-2"></i>${n.newTitle}</a>
                            </c:forEach>
                    </div>
                </div>
            </c:forEach>
            <!--</div>-->
        </div>
        <div class="col-lg-3 col-md-12">
            <div class="mb-5">
                <h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>
                <form action="SendMail" method="post">
<!--                    <div class="form-group">
                        <input type="text" class="form-control border-0 py-4" placeholder="Your Name" required="required" name="name" />
                    </div>-->
                    <div class="form-group">
                        <input type="email" class="form-control border-0 py-4" placeholder="Your Email"
                               name="email" required="required" />
                    </div>
                    <div>
                        <button class="btn btn-primary btn-block border-0 py-3" type="submit">Subscribe Now</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--    <div class="row border-top border-light mx-xl-5 py-4">
        <div class="col-md-6 px-xl-0">
            <p class="mb-md-0 text-center text-md-left text-dark">
                &copy; <a class="text-dark font-weight-semi-bold" href="#">Your Site Name</a>. All Rights Reserved. Designed
                by
                <a class="text-dark font-weight-semi-bold" href="https://htmlcodex.com">HTML Codex</a><br>
                Distributed By <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
            </p>
        </div>
        <div class="col-md-6 px-xl-0 text-center text-md-right">
            <img class="img-fluid" src="img/payments.png" alt="">
        </div>
    </div>-->
</div>
