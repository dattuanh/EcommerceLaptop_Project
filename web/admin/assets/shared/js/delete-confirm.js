function confirmDelete(deleteLink, deleteId) {
    var frm = document.createElement("form");
    frm.setAttribute('method', "post");
    frm.setAttribute('action', deleteLink + '?id=' + deleteId);
    document.getElementsByTagName('body')[0].appendChild(frm);
    if (confirm('Are you sure?')) {
        frm.submit();
    }
}