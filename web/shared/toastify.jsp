<%-- 
    Document   : toastify
    Created on : Jul 4, 2023, 11:13:39 AM
    Author     : Giang Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">

        <style>
            #progress{
                position: absolute;
                bottom: 0;
                left: 0;
                height: 3px;
                width: 100%;
                background: #ddd;
            }

            #progress:before{
                content: '';
                position: absolute;
                bottom: 0;
                right: 0;
                height: 100%;
                width: 100%;
                background-color: #4070f4;
            }

            #progress.active:before{
                animation: progress 5s linear forwards;
            }

            @keyframes progress {
                100%{
                    right: 100%;
                }
            }
        </style>
    </head>
    <body>
        <div class="toast-div">
            <input id="toast-content" type="hidden" value="${sessionScope.noti}" />
            <input id="toast-status" type="hidden" value="${sessionScope.status}" />
            <div class="progress"></div>
        </div>

            
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
        <script>
            var status = document.getElementById('toast-status').value;
            Toastify({
                text: document.getElementById('toast-content').value,
                duration: 6000,
                close: true,
                gravity: "top", // `top` or `bottom`
                position: "right", // `left`, `center` or `right`
                stopOnFocus: true, // Prevents dismissing of toast on hover
                style: {
                    background: status === "1" ? "green" : "red"
                },
                onClick: function () {} // Callback after click
            }).showToast();

        </script>
        
        <jsp:include page="removeToastSession.jsp"></jsp:include>
    </body>
</html>
