<script>
    function validate() {
        var size = 256000;

        var file_size = document.getElementById('file_upload').files[0].size;
        if (file_size >= size) {
            alert('File too large. It should not be more than ' + size / 1000 + ' kb');
            return false;
        }
        var type = 'image/jpeg';
        var file_type = document.getElementById('file_upload').files[0].type;
        if (file_type != type) {
            alert('Format not supported,Only .jpg images are accepted');
            return false;
        }
    }

    function validate1() {
        var size = 256000;
        var file_size1 = document.getElementById('file_upload1').files[0].size;
        if (file_size1 >= size) {
            alert('File too large. It should not be more than ' + size / 1000 + ' kb');
            return false;
        }
        var type1 = 'image/jpeg';
        var file_type1 = document.getElementById('file_upload1').files[0].type;
        if (file_type1 != type1) {
            alert('Format not supported,Only .jpg images are accepted');
            return false;
        }
    }
</script>