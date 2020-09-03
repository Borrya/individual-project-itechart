function saveBook(){
    var request = new XMLHttpRequest();
    request.open('POST', '/add', true);
    request.setRequestHeader('SaveBook', 'application/x-www-form-urlencoded; charset=UTF-8');
    request.send(data);
}