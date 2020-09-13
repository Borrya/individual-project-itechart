/*function saveBook(){
    var request = new XMLHttpRequest();
    request.open('POST', '/add', true);
    request.setRequestHeader('SaveBook', 'application/x-www-form-urlencoded; charset=UTF-8');
    request.send(data);
}*/

function img() {
    var fd = new FormData();
    fd.append('file', document.getElementById("cover").files[0]);
    var req;
    if (window.ActiveXObject) {
        req=new ActiveXObject();
    } else {
        req=new XMLHttpRequest();
    }
    req.open("post", "Image", true);
    req.send(fd);
}

document.querySelector('.custom-file-input').addEventListener('change',function(e){
    var fileName = document.getElementById("cover").files[0].name;
    var nextSibling = e.target.nextElementSibling
    nextSibling.innerText = fileName
})