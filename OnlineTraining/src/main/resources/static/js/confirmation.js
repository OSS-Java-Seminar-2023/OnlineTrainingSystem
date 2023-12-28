    function showConfirmation() {
        document.getElementById('confirmation-dialog').style.display = 'block';
        return false;
    }

    function confirmDelete() {
        document.getElementById('confirmation-dialog').style.display = 'none';
        document.querySelector('form').submit();
    }

    function cancelDelete() {
        document.getElementById('confirmation-dialog').style.display = 'none';

    }