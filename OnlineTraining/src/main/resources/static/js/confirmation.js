function showConfirmation(form) {
    document.getElementById('confirmation-dialog').style.display = 'block';

    window.deleteForm = form;
    return false;
}

function confirmDelete() {
    document.getElementById('confirmation-dialog').style.display = 'none';

    if (window.deleteForm) {
        window.deleteForm.submit();
    }
}

function cancelDelete() {
    document.getElementById('confirmation-dialog').style.display = 'none';
}
