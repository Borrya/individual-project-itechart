function img() {
    var fd = new FormData();
    fd.append('file', document.getElementById("cover").files[0]);
    var req;
    if (window.ActiveXObject) {
        req = new ActiveXObject();
    } else {
        req = new XMLHttpRequest();
    }
    req.open("post", "Image", true);
    req.send(fd);
}

document.querySelector('.custom-file-input').addEventListener('change', function (e) {
    var fileName = document.getElementById("cover").files[0].name;
    var nextSibling = e.target.nextElementSibling;
    nextSibling.innerText = fileName
})

(function () {
    'use strict';
    window.addEventListener('load', function () {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();