<%-- 
    Document   : footer
    Created on : Jun 19, 2023, 9:37:52 PM
    Author     : MSI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="Base.Util.SlugConverter" %>
<jsp:useBean id="news" class="Base.Dal.DAOClient" scope="request"></jsp:useBean>
<jsp:useBean id="policy" class="Base.Dal.DAONews" scope="request"></jsp:useBean>
<!DOCTYPE html>
<footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                        <a href="" class="text-decoration-none">
                            <h1 class="mb-4 display-5 font-weight-semi-bold" style="color: white" ><span class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Computer</h1>
                        </a>
                        <p></p>
                        <p class="mb-0"></i>${news.infoNews.newsContent}</p>
                    </div>



                    <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12">
                        <div class="widget">
                            <h2 class="widget-title"style="font-size: 16px">Chính sách công ty </h2>
                            <div class="link-widget ">
                                <ul>
                                    <c:forEach var="a" items="${policy.policy}">
                                        <li ><a href="TechSingleControl/${SlugConverter.convertToSlug(a.newTitle.replaceAll(" ", "-"))}-${a.newId}-${a.newsGroupID.newsGroupID}-${a.createBy.accountId}.html" style="font-size: 14px;" >${a.newTitle}</a></li>
                                    </c:forEach>
                                </ul>
                            </div><!-- end link-widget -->
                        </div><!-- end widget -->
                    </div><!-- end col -->
                </div>

                <div class="row">
                    <div class="col-md-12 text-center">
                        <br>
                        <div class="copyright">&copy; Tech Blog. Design: <a href="http://html.design">HTML Design</a>.</div>
                    </div>
                </div>
            </div><!-- end container -->
        </footer><!-- end footer -->
