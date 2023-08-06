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
            /*            input, input:active, select, select:active {
                            outline: none;
                            border: none
                        }*/

            .hide-input-css {
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
                                    <input type="hidden" name="id" value="1" >
                                    <div class="row">
                                        <div class="col-xl-3 col-lg-4 col-md-4 col-xs-12">
                                            <div class="social-timeline-left">
                                                <div class="card">
                                                    <div class="social-profile">
                                                        <img class="img-fluid width-100" id="output"
                                                             src="images/Customer/${c.image}"
                                                             onerror="this.onerror=null;this.src='images/others/img-14.jpg';" />
                                                        <script>
                                                            var loadFile = function (event) {
                                                                var output = document.getElementById('output');
                                                                output.src = URL.createObjectURL(event.target.files[0]);
                                                                output.onload = function () {
                                                                    URL.revokeObjectURL(output.src); // free memory
                                                                };
                                                            };
                                                        </script>
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
                                                                <input id="file-upload" type="file" name="image" value="${c.image}" onchange="loadFile(event)"/>
                                                            </div>
                                                            <div class="col-4">
                                                                <!--                                                                <button class="btn btn-danger btn-icon" data-toggle="tooltip"
                                                                                                                                        data-placement="top" title data-original-title="2k">
                                                                                                                                    <i class="fa fa-thumbs-o-up"></i>
                                                                                                                                </button>-->
                                                            </div>
                                                            <div class="col-4">
                                                                <!--                                                                <button class="btn btn-success btn-icon" data-toggle="tooltip"
                                                                                                                                        data-placement="top" title data-original-title="90">
                                                                                                                                    <i class="fa fa-eye"></i>
                                                                                                                                </button>-->
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-5">
                                                                <button type="submit"
                                                                        class="btn btn-outline-primary waves-effect btn-block">
                                                                    <i class="icofont icofont-ui-user m-r-10"></i> Save</button>
                                                            </div>
                                                            <div class="col-2"></div>
                                                            <div class="col-5">
                                                                <a href="Home" class="btn btn-outline-primary waves-effect btn-block">
                                                                    Back
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-xl-9 col-lg-8 col-md-8 col-xs-12 ">
                                            <div class="card social-tabs">
                                                <ul class="nav nav-tabs md-tabs tab-timeline" role="tablist">
                                                    <li class="nav-item active">
                                                        <a class="nav-link" data-toggle="tab" href="#about" role="tab">About</a>
                                                        <div class="slide"></div>
                                                    </li>
                                                    <li class="nav-item active">
                                                        <a class="nav-link" data-toggle="tab" href="#order" role="tab">Order History</a>
                                                        <div class="slide"></div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="about">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div class="card">
                                                                <div class="card-header">
                                                                    <h5 class="card-header-text">Basic Information</h5>
                                                                    <button id="edit-btn" type="button"
                                                                            class="btn btn-primary waves-effect waves-light f-right">
                                                                        <i class="icofont icofont-edit"></i>
                                                                    </button>
                                                                </div>
                                                                <div class="card-block">
                                                                    <div id="view-info" class="row">
                                                                        <div class="col-lg-8 col-md-12">

                                                                            <table class="table table-responsive m-b-0">
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th
                                                                                            class="social-label b-none p-t-0">
                                                                                            First Name
                                                                                        </th>
                                                                                        <td>
                                                                                            <input type="text" name="firstName" value="${c.firstName}" 
                                                                                                   class="social-user-name b-none p-t-0 text-muted hide-input-css"/>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th
                                                                                            class="social-label b-none p-t-0">
                                                                                            Last Name
                                                                                        </th>
                                                                                        <td>
                                                                                            <input type="text" name="lastName" value="${c.lastName}" 
                                                                                                   class="social-user-name b-none p-t-0 text-muted hide-input-css"/>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th class="social-label b-none">
                                                                                            Gender</th>
                                                                                        <td>
                                                                                            <select name="sex" class="social-user-name b-none text-muted"
                                                                                                    style="border: none">
                                                                                                <option value="1" ${c.gender == true ? "selected" : ""}>Male</option>
                                                                                                <option value="0" ${c.gender == false ? "selected" : ""}>Female</option>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th
                                                                                            class="social-label b-none p-b-0">
                                                                                            Address</th>
                                                                                        <td>
                                                                                            <input type="text" name="address" value="${c.address}"
                                                                                                   class="social-user-name b-none p-b-0 text-muted hide-input-css">
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-12">
                                                            <div class="card">
                                                                <div class="card-header">
                                                                    <h5 class="card-header-text">Contact Information</h5>
                                                                    <button id="edit-Contact" type="button"
                                                                            class="btn btn-primary waves-effect waves-light f-right">
                                                                        <i class="icofont icofont-edit"></i>
                                                                    </button>
                                                                </div>
                                                                <div class="card-block">
                                                                    <div id="contact-info" class="row">
                                                                        <div class="col-lg-8 col-md-12">
                                                                            <table class="table table-responsive m-b-0">
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th class="social-label b-none p-t-0 ">
                                                                                            Mobile Number</th>
                                                                                        <td>
                                                                                            <input type="tel" name="phone" value="${c.phone}"
                                                                                                   class="social-user-name b-none p-b-0 text-muted hide-input-css">
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th class="social-label b-none">Email
                                                                                            Address
                                                                                        </th>
                                                                                        <td>
                                                                                            <input type="email" name="email" value="${c.email}" style="width: 100%"
                                                                                                   class="social-user-name b-none p-b-0 text-muted hide-input-css" readonly>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th class="social-label b-none">
                                                                                            UserName
                                                                                        </th>
                                                                                        <td>
                                                                                            <input type="text" name="userName" value="${c.userName}"
                                                                                                   class="social-user-name b-none p-b-0 text-muted hide-input-css">
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th class="social-label b-none">Password
                                                                                        </th>
                                                                                        <td>
                                                                                            <!--                                                                                            <input type="password" type="password" name="password" 
                                                                                                                                                                                               value="${c.password}" class="social-user-name b-none p-b-0 text-muted">-->
                                                                                            <!--<a class="btn btn-danger" href="ChangePassword?id=0">Đổi mật khẩu</a>-->
                                                                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModalCenter">
                                                                                                Đổi mật khẩu
                                                                                            </button>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <!--                                                                                    <tr>
                                                                                                                                                                            <th class="social-label b-none">Confirm Password
                                                                                                                                                                            </th>
                                                                                                                                                                            <td>
                                                                                                                                                                                <input type="password" type="password" name="confirmPassword" 
                                                                                                                                                                                       value="${c.password}" class="social-user-name b-none p-b-0 text-muted">
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>-->
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="tab-pane active" id="order">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div class="card">
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
                                                                                        <th>OrderID</th>
                                                                                        <th>Order Date</th>
                                                                                        <th>Total Price</th>
                                                                                        <th>Status</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach var="o" items="${olist}">
                                                                                        <tr>
                                                                                            <td>
                                                                                                <a href="Info?OrderDetailId=${o.orderId}">${o.orderId}</a>
                                                                                            </td>
                                                                                            <td>${o.orderDate}</td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.totalPrice}" type="number" />
                                                                                            </td>
                                                                                            <td>${o.status.orderStatusName}</td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
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
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>   
        <c:if test = "${sessionScope.noti != null}">
            <jsp:include page="../../../shared/toastify.jsp" />
        </c:if>

        <!-- Button trigger modal -->


        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Đổi mật khẩu</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <form action="Info" method="post">
                        <input type="hidden" name="id" value="2" >
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="oldPass">Old password</label>
                                <input type="password" name="oldPass" class="form-control col-8" id="oldPass">
                            </div>

                            <div class="form-group">
                                <label for="newPass">New password</label>
                                <input type="password" name="newPass" class="form-control col-8" id="newPass">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger">Change</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
