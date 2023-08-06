<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
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
    </head>
    <body>
        <form id="GoogleSignInForm" action="Login" method="post">
            <!--Add your form fields here--> 
            <input type="hidden" id="name" name="name" >
            <input type="hidden" id="email" name="email" >
            <input type="hidden" id="exp" name="exp" >

            <!--Add a hidden input field to store the Google ID token--> 
            <input type="hidden" id="googleIdToken" name="googleIdToken">
        </form>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <div class="login100-pic js-tilt" data-tilt>
                        <img src="client/user/user-access/images/img-01.png" alt="IMG">
                    </div>

                    <form class="login100-form validate-form" action="Login" method="post">
                        <input type="submit" hidden />
                        <span class="login100-form-title">
                            Login
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" id="email" name="email" placeholder="Email" value="${email}" autofocus>
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="pass" placeholder="Password" value="${pass}">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="form-check-inline">
                            <input type="checkbox" id="rememberMe" name="remember">
                            <label class="txt1" for="rememberMe">Remember</label>
                        </div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Login
                            </button>
                        </div>

                        <div class="text-center p-t-12">
                            <span class="txt1">
                                Forgot
                            </span>
                            <a class="txt2" href="ForgotPassword">
                                Your Password?
                            </a>
                        </div>

                        <div style="width: 100%; height: 20px; border-bottom: 1px solid black; text-align: center;
                             margin-bottom: 20px; margin-top: 10px">
                            <span style="background-color: #F3F5F6; padding: 0 10px;">
                                Hoac
                            </span>
                        </div>

                        <div id="buttonDiv" style="padding-left: 40px;"></div>

                        <div class="text-center p-t-36">
                            <a class="txt2" href="Register">
                                Create your Account
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

        <!--@*START google sign-in*@-->
        <script src="https://accounts.google.com/gsi/client" async defer></script>
        <script src="https://unpkg.com/jwt-decode/build/jwt-decode.js"></script>
        <script>
            function handleCredentialResponse(response) {
                var decodedToken = jwt_decode(response.credential);
                document.getElementById('googleIdToken').value = decodedToken;
                document.getElementById('name').value = decodedToken.name;
                document.getElementById('email').value = decodedToken.email;
                document.getElementById('exp').value = decodedToken.exp;
                // Auto-submit the form
                document.getElementById('GoogleSignInForm').submit();
            }

            window.onload = function () {
                google.accounts.id.initialize({
                    client_id: "402411054593-bih1d0ce9chi1453ae6ugvpcavu2f228.apps.googleusercontent.com",
                    callback: handleCredentialResponse
                });
                google.accounts.id.renderButton(
                        document.getElementById("buttonDiv"),
                        {theme: "filled_blue", size: "large", width: 100}  // customization attributes
                );

                google.accounts.id.prompt();
            }

        </script>
        <!--@*END google sign-in*@-->
    </body>
</html>