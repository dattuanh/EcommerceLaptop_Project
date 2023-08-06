<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="client/user/user-access/images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="client/user/user-access/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="client/user/user-access/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="client/user/user-access/vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="client/user/user-access/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="client/user/user-access/vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="client/user/user-access/css/util.css">
        <link rel="stylesheet" type="text/css" href="client/user/user-access/css/main.css">
        <!--===============================================================================================-->
        <style>
            .wrap-login100 {
                background-image: image-set("client/user/user-access/images/web-pages-are-laptop-screen-mobile-phone-is-lying-modern-technological-background_593483-1024.webp");
                background-repeat: no-repeat;
                background-size: cover;
            }

            .input-container {
                position: relative;
            }

            .input-container i.showHidePass {
                position: absolute;
                top: 50%;
                right: 20px;
                transform: translateY(-50%);
                cursor: pointer;
            }
        </style>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" 
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100 row">
                    <form class="login100-form validate-form mx-auto col-7 row" action="Register" method="post">
                        <span class="login100-form-title" style="color: white">
                            Create new Account
                        </span>

                        <div class="wrap-input100 validate-input col-6" data-validate="Name must be 2-24 chars">
                            <input class="input100" type="text" name="firstName" placeholder="FirstName" value="${firstName}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-6" data-validate="Name must be 2-24 chars">
                            <input class="input100" type="text" name="lastName" placeholder="LastName" value="${lastName}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-12"
                             data-validate="Must not contain spaces or special characters">
                            <input class="input100" type="text" name="username" placeholder="Username" value="${username}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-12"
                             data-validate="Valid phone number must be exactly 10 digits">
                            <input class="input100" type="tel" name="phone" placeholder="Phone Number" value="${phoneNumber}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-phone" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-12"
                             data-validate="Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="email" placeholder="Email" value="${email}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-12 input-container" data-validate="Password is required">
                            <input class="input100" type="password" name="pass" placeholder="Password" value="${pass}" id="passwordField">
                            <i class="fa-solid fa-eye showHidePass" onclick="togglePasswordVisibility(this)"></i>
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input col-12 input-container" data-validate="RePassword must be same as Password">
                            <input class="input100" type="password" name="rePass" placeholder="Re Password" value="${repass}" id="rePasswordField">
                            <i class="fa-solid fa-eye showHidePass" onclick="toggleRePasswordVisibility(this)"></i>
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="container-login100-form-btn col-12">
                            <button class="login100-form-btn">
                                Create
                            </button>
                        </div>

                        <div class="text-center p-t-20">
                            <span class="txt1">
                                Forgot
                            </span>
                            <a class="txt2" href="ForgotPassword">
                                Your Password?
                            </a>
                        </div>

                        <div class="text-center p-t-50">
                            <a class="txt2" href="Login">
                                Have an Account ? Sign In
                                <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>




        <!--===============================================================================================-->	
        <script src="client/user/user-access/vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="client/user/user-access/vendor/bootstrap/js/popper.js"></script>
        <script src="client/user/user-access/vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="client/user/user-access/vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="client/user/user-access/vendor/tilt/tilt.jquery.min.js"></script>
        <script >
                                $('.js-tilt').tilt({
                                    scale: 1.1
                                })
        </script>
        <!--===============================================================================================-->
        <script src="client/user/user-access/js/main.js"></script>

        <c:if test = "${sessionScope.noti != null}">
            <jsp:include page="../../../shared/toastify.jsp" />
        </c:if>

        <script>
                                function togglePasswordVisibility(obj) {
                                    const passwordField = document.getElementById("passwordField");

                                    if (passwordField.type === "password") {
                                        obj.classList.replace(obj.classList[1], 'fa-eye-slash');
                                        passwordField.type = "text";
                                    } else {
                                        obj.classList.replace(obj.classList[1], 'fa-eye');
                                        passwordField.type = "password";
                                    }
                                }

                                function toggleRePasswordVisibility(obj) {
                                    const passwordField = document.getElementById("rePasswordField");

                                    if (passwordField.type === "password") {
                                        obj.classList.replace(obj.classList[1], 'fa-eye-slash');
                                        passwordField.type = "text";
                                    } else {
                                        obj.classList.replace(obj.classList[1], 'fa-eye');
                                        passwordField.type = "password";
                                    }
                                }
        </script>
    </body>

</html>