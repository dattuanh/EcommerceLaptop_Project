<%-- 
    Document   : toast
    Created on : Jun 13, 2023, 5:32:30 PM
    Author     : Giang Minh
--%>
<style>
/*    .toastify-content {
        text-align: center;
    }

    .toastify-timeline {
        position: relative;
        width: 2px;
        height: 80%;
        margin: 10% auto;
        background-color: #333;
    }*/
</style>

<input type="hidden" id="noti" value="${sessionScope.noti}" />
<input type="hidden" id="color" value="${sessionScope.color}" />
<input type="hidden" id="messs" value="${sessionScope.mess}" />

<script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
<script>
    function showToast() {
        Toastify({
            text: document.getElementById('messs').value + '\n' + document.getElementById('noti').value,
            duration: 2000, // Duration in milliseconds
            close: false, // Show close button
            gravity: "top", // Position of the toast
            backgroundColor: document.getElementById('color').value,
//            template: '<div class="toastify-content">{{text}}</div><div class="toastify-timeline"></div>',
            // Other options if needed
        }).showToast();
    }
    $(document).ready(showToast());
//    console.log(document.getElementById('mess'));
</script>

