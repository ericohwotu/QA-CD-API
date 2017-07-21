window.onload = function() {
    populateTable("http://localhost:8080/EESample/rest/cd/json");
    checkSize();
    getApiKey();
    $(document).on("click", "#tableContents > tr ", populateForm);
}

var apiKey = "";
var ID = 0;
var selected = false;
var creating = false;

function getApiKey () {
    var returnedApiKey = "";
    let xhttpGET = new XMLHttpRequest();
    xhttpGET.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            let returnAPI = JSON.parse(xhttpGET.response);
            returnedApiKey = returnAPI["apiKey"];
            console.log(returnedApiKey);
            apiKey = returnedApiKey;
            console.log("API Key set")
        }
    }
    xhttpGET.open("POST", "http://localhost:8080/EESample/rest/cd/key/user=system", true);
    xhttpGET.send();
}

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

function populateForm(e) {
    console.log("ID: " + e.currentTarget.id)
    var xhttp = new XMLHttpRequest();
    ID = e.currentTarget.id;
    console.log("Request sent");
    xhttp.onreadystatechange = function () {
        console.log("Status: " + this.status);
        if(this.readyState == 4 && this.status == 200) {
            console.log("Request received");
            let retVal = JSON.parse(xhttp.response);
            var album = document.getElementById("formAlbum");
            album.value = retVal[0]["name"];
            var artist = document.getElementById("formArtist");
            artist.value = retVal[0]["artist"];
            var genre = document.getElementById("formGenre");
            genre.value = retVal[0]["genre"];
            var year = document.getElementById("formYear");
            year.value = retVal[0]["year"];
            selected = true;
        }
    }
    var url = "http://localhost:8080/EESample/rest/cd/json/"+ID;
    xhttp.open("GET", url, true);
    xhttp.send();
}

function butClick () {
    var albName = document.getElementById("filByName");
    var artName = document.getElementById("filByArt");
    console.log(albName.value);
    console.log(artName.value);
    if(albName.value == "" && artName.value == "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json");
    } else if(albName.value != "" && artName.value == "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json/name="+albName.value);
    } else if(albName.value == "" && artName.value != "") {
        populateTable("http://localhost:8080/EESample/rest/cd/json/artist="+artName.value);
    }
}

function checkSize () {
    var cw = document.body.clientWidth;
    var percentageAmount = cw/1920;
    if(percentageAmount > 90) {
        percentageAmount = 100;
    }
    var newSize = 400*percentageAmount;
    var albumArt = document.getElementById("albumArt");
    albumArt.style.height = newSize+"px";
    albumArt.style.weidth = newSize+"px";
}

function startCreateAlbum () {
    if(creating) {
        document.getElementById("createButton").innerHTML = "Create Album";
        document.getElementById("submitButton").innerHTML = "Submit Changes";
        var album = document.getElementById("formAlbum");
        album.value = "";
        var artist = document.getElementById("formArtist");
        artist.value = "";
        var genre = document.getElementById("formGenre");
        genre.value = "";
        var year = document.getElementById("formYear");
        year.value = "";
        creating = false;
    } else {
        document.getElementById("createButton").innerHTML = "Cancel";
        document.getElementById("submitButton").innerHTML = "Submit New Album";
        creating = true;
        selected = false;
    }
}

function submitPress () {
    var album = document.getElementById("formAlbum");
    var artist = document.getElementById("formArtist");
    var genre = document.getElementById("formGenre");
    var year = document.getElementById("formYear");

    if(!creating) {
        if(selected) {
            var sendData = {"name":""+album.value+"", "artist":""+artist.value+"", "genre":""+genre.value+"", "year":""+year.value+""};

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if(this.readyState == 4 && this.status == 200) {
                    album.value = "";
                    artist.value = "";
                    genre.value = "";
                    year.value = "";
                    populateTable("http://localhost:8080/EESample/rest/cd/json");
                }
            }
            console.log(sendData);
            xhttp.open("PUT", "http://localhost:8080/EESample/rest/cd/json/key="+apiKey+"&iD="+ID, true);
            xhttp.send(JSON.stringify(sendData));
        }
    } else {
        if(album.value != "" && artist.value != "" && genre.value != "" && year.value != "") {

            var sendData = {"name":""+album.value+"", "artist":""+artist.value+"", "genre":""+genre.value+"", "year":""+year.value+""};

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if(this.readyState == 4 && this.status == 200) {
                    album.value = "";
                    artist.value = "";
                    genre.value = "";
                    year.value = "";
                    populateTable("http://localhost:8080/EESample/rest/cd/json");
                    creating = false;
                    document.getElementById("createButton").innerHTML = "Create Album";
                    document.getElementById("submitButton").innerHTML = "Submit Changes";
                }
            }
            console.log(sendData);
            xhttp.open("POST", "http://localhost:8080/EESample/rest/cd/json/key="+apiKey, true);
            xhttp.send(JSON.stringify(sendData));
         } else {
            window.alert("All fields are required to have an input.")
         }
    }
}