<%-- 
    Document   : toastify-react
    Created on : Jul 4, 2023, 12:41:49 PM
    Author     : Giang Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://unpkg.com/react@16.13.1/umd/react.production.min.js"></script>
        <script src="https://unpkg.com/react-dom@16.13.1/umd/react-dom.production.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/react-toastify@9.1.3/dist/react-toastify.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/react-toastify@9.1.3/dist/ReactToastify.min.css" rel="stylesheet">
    </head>
    <body>

        <button onclick="showToast()">Show Toast</button>

        <div id="toast-container"></div>

        <script>
            // Initialize React Toastify
            ReactToastify.configure();
            // Display a toast
            function showToast() {
            ReactToastify.toast("Hello, world!");
            }

            // Call the showToast function to display a toast
            showToast();
        </script>
    </body>
</html>
