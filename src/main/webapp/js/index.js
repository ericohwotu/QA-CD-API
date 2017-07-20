function populateTable(url) {
    $("tbody").children().remove();
    var pendingTable = document.getElementById("tableContents");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            let retVal = JSON.parse(xhttp.response);
            for(i = 0; i < retVal.length; i++) {
                var row = document.createElement("tr");
                row.id = retVal[i]["iD"];

                var rID = document.createElement("td");
                rID.appendChild(document.createTextNode(retVal[i]["iD"]));
                row.appendChild(rID);

                var rName = document.createElement("td")
                rName.appendChild(document.createTextNode(retVal[i]["name"]));
                row.appendChild(rName);

                var rArtist = document.createElement("td")
                rArtist.appendChild(document.createTextNode(retVal[i]["artist"]));
                row.appendChild(rArtist);

                var rGenre = document.createElement("td")
                rGenre.appendChild(document.createTextNode(retVal[i]["genre"]));
                row.appendChild(rGenre);

                pendingTable.appendChild(row);
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
}

populateTable("http://localhost:8080/EESample/rest/cd/json");

function butClick () {
    var albName = document.getElementById("filByName");
    var artName = document.getElementById("filByArt");
    if(albName.value == "" && artName.value == "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json");
    } else if(albName.value != "" && artName == "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json/name="+albName.value);
    } else if(albName.value == "" && artName != "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json/artist="+artName.value);
    }
}