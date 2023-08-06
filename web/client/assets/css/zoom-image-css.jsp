<%-- 
    Document   : zoom-image
    Created on : Jun 8, 2023, 10:18:10 AM
    Author     : Giang Minh
--%>

<style>
    #img-zoomer-box {
        max-width: 500px;
        height: auto;
        position: relative;
        margin: 10px auto;
    }

    #img-1 {
        width: 100%;
        height: auto;
    }

    #img-zoomer-box:hover, #img-zoomer-box:active {
        cursor: zoom-in;
        display: block;
    }

    #img-zoomer-box:hover #img-2, #img-zoomer-box:active #img-2 {
        opacity: 1;
    }
</style>
