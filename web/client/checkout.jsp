<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>EShopper - Bootstrap Shop Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet">
        
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    </head>

    <body>
        <!-- Topbar Start -->
        <jsp:include page="../client/partials/_topbar.jsp" ></jsp:include>
        <!-- Topbar End -->

        <!-- Navbar Start -->
        <jsp:include page="../client/partials/_navbar.jsp"></jsp:include>
            <!-- Navbar End -->


            <!-- Page Header Start -->
            <div class="container-fluid bg-secondary mb-5">
                <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                    <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
                    <div class="d-inline-flex">
                        <p class="m-0"><a href="">Home</a></p>
                        <p class="m-0 px-2">-</p>
                        <p class="m-0">Checkout</p>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->


            <!-- Checkout Start -->
            <div class="container-fluid pt-5">
                <form action="PaymentController" method="post" id="frmCreateOrder">
                    <div class="row px-xl-5">
                        <div class="col-lg-8">
                            <div class="mb-4">
                                <div class="row">
                                    <div class="col-10">
                                        <h4 class="font-weight-semi-bold mb-4">Billing Address</h4>
                                    </div>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <div class="col-2">
                                            <a href="Info"><button type="button" class="btn btn-dark">Update Info</button></a>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                        <label>First Name</label>
                                    <c:if test="${RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="Joe" name="firstName" required>
                                    </c:if>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="Joe" name="firstName" value="${cus.firstName}" readonly>
                                    </c:if>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Last Name</label>
                                    <c:if test="${RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="Hoe" name="lastName" required>
                                    </c:if>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="Hoe" name="lastName" value="${cus.lastName}" readonly>
                                    </c:if>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>E-mail</label>
                                    <c:if test="${RequireCustomerInfo}">
                                        <input class="form-control" type="email" placeholder="example@email.com" name="email" required>
                                    </c:if>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <input class="form-control" type="email" placeholder="example@email.com" name="email" value="${cus.email}" readonly>
                                    </c:if>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Mobile No</label>
                                    <c:if test="${RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="+123 456 789" name="phone" required>
                                    </c:if>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="+123 456 789" name="phone" value="${cus.phone}" readonly>
                                    </c:if>
                                </div>
                                <div class="col-md-12 form-group">
                                    <label>Address</label>
                                    <c:if test="${RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="123 Street" name="address" required>
                                    </c:if>
                                    <c:if test="${not RequireCustomerInfo}">
                                        <input class="form-control" type="text" placeholder="123 Street" name="address" value="${cus.address}" required>
                                    </c:if>
                                </div>
<!--                                <div class="col-md-6 form-group">
                                    <label>City</label>
                                    <input class="form-control" type="text" name="city" placeholder="New York">
                                </div>
                                <div class="col-md-12 form-group">
                                    
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="shipto">
                                        <label class="custom-control-label" for="shipto"  data-toggle="collapse" data-target="#shipping-address">Ship to different address</label>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                        <div class="collapse mb-4" id="shipping-address">
                            <h4 class="font-weight-semi-bold mb-4">Shipping Address</h4>
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <label>First Name</label>
                                    <input class="form-control" type="text" placeholder="John">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Last Name</label>
                                    <input class="form-control" type="text" placeholder="Doe">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>E-mail</label>
                                    <input class="form-control" type="text" placeholder="example@email.com">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Mobile No</label>
                                    <input class="form-control" type="text" placeholder="+123 456 789">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Address Line 1</label>
                                    <input class="form-control" type="text" placeholder="123 Street">
                                </div>

                                <div class="col-md-6 form-group">
                                    <label>City</label>
                                    <input class="form-control" type="text" placeholder="New York">
                                </div>

                            </div>
                        </div>


                    </div>
                    <div class="col-lg-4">
                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Order Total</h4>
                            </div>
                            <div class="card-body">
                                <h5 class="font-weight-medium mb-3">Products</h5>
                                <c:forEach var="c" items="${cart.listItem}">
                                    <div class="d-flex justify-content-between">
                                        <p style="width: 200px">${c.productSeri.productSeriName}</p>
                                        <p style="width: 40px">${c.quantity}</p>
                                        <p><fmt:formatNumber value="${c.productSeri.price * c.quantity}" type="number" /></p>                                        
                                    </div>
                                </c:forEach>
                                
                                
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5 class="font-weight-bold">Total</h5>
                                    <input type="hidden" name="total" value="<fmt:formatNumber value="${cart.getTotalMoney()}" type="number" />">
                                    <h5 class="font-weight-bold"><fmt:formatNumber value="${cart.getTotalMoney()}" type="number" /></h5>
                                </div>
                            </div>
                        </div>
                        <div class="card border-secondary mb-5">

                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Payment</h4>
                            </div>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="paymentMethod" id="NCB" value="NCB" checked>
                                        <label class="custom-control-label" for="NCB">NCB Bank</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="paymentMethod" id="directcheck" value="Direct">
                                        <label class="custom-control-label" for="directcheck">Direct Check</label>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="card-footer border-secondary bg-transparent">
                                <button type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place Order</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>


        <!-- Checkout End -->


        <!-- Footer Start -->
        <jsp:include page="../client/partials/_footer.jsp"></jsp:include>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="client/lib/easing/easing.min.js"></script>
        <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="client/mail/jqBootstrapValidation.min.js"></script>
        <script src="client/mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="client/js/main.js"></script>

       
        
        <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var paymentMethod = $('input[name=paymentMethod]:checked').val();
                if (paymentMethod === 'NCB') {
                    var postData = $("#frmCreateOrder").serialize();
                    var submitUrl = $("#frmCreateOrder").attr("action");
                    $.ajax({
                        type: "POST",
                        url: submitUrl,
                        data: postData,
                        dataType: 'JSON',
                        success: function (x) {
                            console.log(x);
                            if (x.code === '00') {
                                if (window.vnpay) {
                                    vnpay.open({width: 768, height: 600, url: x.data});
                                } else {
                                    location.href = x.data;
                                }
                                return false;
                            } else {
                                alert(x.Message);
                            }
                        }
                    });
                    return false;
                } else {
                    // do nothing
                    //return false;
                }
            });
        </script>
    </body>

</html>
