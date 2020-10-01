function addRows(rowsMin) {
    var tableBody = document.getElementById('tableBody');
    var id = 1;
    for (var j = 0; j < 10; j++) {
        var newRow = document.createElement("tr");
        var firstCell = document.createElement("th");
        firstCell.setAttribute("scope", "row");
        firstCell.innerHTML = id;
        newRow.appendChild(firstCell);
        for (var i = 0; i < 4; i++) {
            var otherCells = document.createElement("td");
            newRow.appendChild(otherCells);
        }
        id++;

        tableBody.appendChild(newRow);
    }
}
