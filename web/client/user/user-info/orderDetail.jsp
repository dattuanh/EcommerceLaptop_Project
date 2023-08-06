<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : in4
    Created on : Jun 11, 2023, 8:40:38 PM
    Author     : Giang Minh
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://netdna.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet">
        <link href="client/user/user-info/info.css" rel="stylesheet" >
        <style>
            input, input:active, select, select:active {
                outline: none;
                border: none
            }

            input[type="file"] {
                display: none;
            }
            .custom-file-upload {
                border: 1px solid #ccc;
                display: inline-block;
                padding: 6px 12px;
                cursor: pointer;
            }
        </style>

    </head>
    <body>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div>
                        <div class="content social-timeline">
                            <div class>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="social-wallpaper">
                                            <div class="profile-hvr">
                                                <i class="icofont icofont-ui-edit p-r-10"></i>
                                                <i class="icofont icofont-ui-delete"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form action="Info" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-xl-3 col-lg-4 col-md-4 col-xs-12">
                                            <div class="social-timeline-left">
                                                <div class="card">
                                                    <div class="social-profile">
                                                        <img class="img-fluid width-100"
                                                             src="images/Customer/${c.image}"
                                                             onerror="this.onerror=null;this.src='assets/shared/images/others/img-14.jpg';" />
                                                        <div class="profile-hvr m-t-15">
                                                            <i class="icofont icofont-ui-edit p-r-10"></i>
                                                            <i class="icofont icofont-ui-delete"></i>
                                                        </div>
                                                    </div>

                                                    <div class="card-block social-follower">
                                                        <h4>${c.lastName} ${c.firstName}</h4>
                                                        <div class="row follower-counter">
                                                            <div class="col-4">
                                                                <label for="file-upload" class="custom-file-upload btn btn-primary btn-icon">
                                                                    <i class="fa fa-cloud-upload"></i>
                                                                </label>
                                                                <input id="file-upload" type="file" name="image" value="${c.image}" />
                                                                <!--<input type="file" value=""/>-->
                                                            </div>
                                                            <div class="col-4">
                                                                <button class="btn btn-danger btn-icon" data-toggle="tooltip"
                                                                        data-placement="top" title data-original-title="2k">
                                                                    <i class="fa fa-thumbs-o-up"></i>
                                                                </button>
                                                            </div>
                                                            <div class="col-4">
                                                                <button class="btn btn-success btn-icon" data-toggle="tooltip"
                                                                        data-placement="top" title data-original-title="90">
                                                                    <i class="fa fa-eye"></i>
                                                                </button>
                                                            </div>
                                                        </div>

                                                        <div>
                                                            <button type="submit"
                                                                    class="btn btn-outline-primary waves-effect btn-block">
                                                                <i class="icofont icofont-ui-user m-r-10"></i> Save</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xl-9 col-lg-8 col-md-8 col-xs-12 ">
                                            <div class="row col-4">
                                                <a href="javascript:history.back()" class="mr-3">
                                                    <i class="fa fa-arrow-left" style="font-size: 20px"></i>
                                                </a>
                                                <h4>Order: Tuan Dat</h4>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-6 grid-margin stretch-card">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <h5 class="card-header-text">Customer Information</h5>
                                                            <button id="edit-btn" type="button"
                                                                    class="btn btn-primary waves-effect waves-light f-right">
                                                                <i class="icofont icofont-edit"></i>
                                                            </button>
                                                        </div>
                                                        <div class="card-block">
                                                            <div id="view-info" class="row">
                                                                <div class="col-lg-6 col-md-12">
                                                                    <table class="table table-responsive m-b-0">
                                                                        <tbody>
                                                                            <tr>
                                                                                <th
                                                                                    class="social-label b-none p-t-0">
                                                                                    Customer Name
                                                                                </th>
                                                                                <td>
                                                                                    <input type="text" name="Name" value="${c.firstName} ${c.lastName}" 
                                                                                           class="social-user-name b-none p-t-0 text-muted"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th
                                                                                    class="social-label b-none p-t-0">
                                                                                    Payment Method
                                                                                </th>
                                                                                <td>
                                                                                    <input type="text" name="PaymentMethod" value="${o.paymentMethod}" 
                                                                                           class="social-user-name b-none p-t-0 text-muted"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th class="social-label b-none">
                                                                                    Gender</th>
                                                                                <td>
                                                                                    <input type="text" name="gender" value="${c.gender == true ? "Male":"Female"}" 
                                                                                           class="social-user-name b-none p-t-0 text-muted"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th
                                                                                    class="social-label b-none p-b-0">
                                                                                    Address</th>
                                                                                <td>
                                                                                    <input type="text" name="address" value="${c.address}"
                                                                                           class="social-user-name b-none p-b-0 text-muted">
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th
                                                                                    class="social-label b-none p-b-0">
                                                                                    Phone</th>
                                                                                <td>
                                                                                    <input type="text" name="phone" value="${c.phone}"
                                                                                           class="social-user-name b-none p-b-0 text-muted">
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6 grid-margin stretch-card">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <h5 class="card-header-text">Order Information</h5>
                                                            <button id="edit-btn" type="button"
                                                                    class="btn btn-primary waves-effect waves-light f-right">
                                                                <i class="icofont icofont-edit"></i>
                                                            </button>
                                                        </div>
                                                        <div class="card-block">
                                                            <div id="view-info" class="row">
                                                                <div class="col-lg-6 col-md-12">
                                                                    <table class="table table-responsive m-b-0">
                                                                        <tbody>
                                                                            <tr>
                                                                                <th
                                                                                    class="social-label b-none p-t-0">
                                                                                    Order Status
                                                                                </th>
                                                                                <td>
                                                                                    <c:if test="${o.status.orderStatusName eq 'Pending'}">
                                                                                        <input readonly type="text" name="status" 
                                                                                               value="Thanh toán thành công" class="social-user-name b-none p-t-0 text-muted"
                                                                                               style="width: 200px">
                                                                                    </c:if>
                                                                                    <c:if test="${o.status.orderStatusName eq 'Approved'}">
                                                                                        <input readonly type="text" name="status" 
                                                                                               value="Đơn hàng đang được giao" class="social-user-name b-none p-t-0 text-muted"
                                                                                               style="width: 200px">
                                                                                    </c:if>
                                                                                    <c:if test="${o.status.orderStatusName eq 'Closed'}">
                                                                                        <input readonly type="text" name="status" value="Đơn hàng đã được giao" class="social-user-name b-none p-t-0 text-muted">
                                                                                    </c:if>
                                                                                    <c:if test="${o.status.orderStatusName eq 'Rejected'}">
                                                                                        <input readonly type="text" name="status" value="Đơn hàng đã được hủy" class="social-user-name b-none p-t-0 text-muted">
                                                                                    </c:if>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th class="social-label b-none">
                                                                                    Order Date</th>
                                                                                <td>
                                                                                    <input type="text" readonly name="firstName" value="<fmt:formatDate value="${o.orderDate}" pattern="dd-MM-yyyy" />" 
                                                                                           class="social-user-name b-none p-t-0 text-muted"/>
                                                                                </td>
                                                                            </tr>
                                                                            <c:if test="${o.status.orderStatusName eq 'Approved'}">
                                                                                <tr>
                                                                                    <th class="social-label b-none">
                                                                                        Estimated time</th>
                                                                                    <td>
                                                                                        <p style="width: 200px" class="social-user-name text-muted">
                                                                                            Đơn hàng dự tính sẽ ship sau ${randomNum} ngày (<fmt:formatDate value="${randomShippingDate}" pattern="dd-MM-yyyy" />)
                                                                                        </p>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>                     
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="card" style="margin: 10px">
                                                        <div class="card-header">
                                                            <h5 class="card-header-text">My Orders</h5>
                                                            <button id="edit-btn" type="button"
                                                                    class="btn btn-primary waves-effect waves-light f-right">
                                                                <i class="icofont icofont-edit"></i>
                                                            </button>
                                                        </div>
                                                        <div class="card-block">
                                                            <div id="view-info" class="row">
                                                                <div class="col-lg-12 col-md-12">
                                                                    <table class="table table-responsive m-b-0">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>Image</th>
                                                                                <th>ProductName</th>
                                                                                <th>Price</th>
                                                                                <th>Quantity</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <c:forEach var="orderItem" items="${itemOrderList}">
                                                                                <tr>
                                                                                    <td style="padding: 0;">
                                                                                        <img id="img-1" src="images/ProductSeri/${orderItem.productId.productSerieId.imagePreview}" alt="" style="width: 150px;">
                                                                                    </td>
                                                                                    <td>${orderItem.productId.productSerieId.productSeriName}</td>
                                                                                    <td>
                                                                                        <input type="text" name="totalPrice"
                                                                                               value="<fmt:formatNumber value="${orderItem.productId.productSerieId.price}" type="number"/>" 
                                                                                               style="outline: none; border: none">
                                                                                    </td>
                                                                                    <td>${orderItem.quantity}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <c:if test="${o.status.orderStatusName eq 'Rejected'}">
                                                        <a href="RefundOrder">
                                                            <button id="edit-btn" type="button" class="btn btn-primary float-right" style="margin: 0px 10px;">
                                                                <i class="icofont icofont-edit" >Refund</i>
                                                            </button>
                                                        </a>
                                                    </c:if>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
