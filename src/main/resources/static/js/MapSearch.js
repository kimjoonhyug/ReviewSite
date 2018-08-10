var MapSearch = {
    RESULTS_ID: "results"
};







//START


function setMapAndGeo() {
    var mapOption;
    if (MapSearch.currentPosition !== undefined) {
        mapOption = makeOptions(MapSearch.currentPosition);
    } else {
        mapOption = makeOptions(new daum.maps.LatLng(33.450701, 126.570667));
    }

    var map = new daum.maps.Map($('#map')[0], mapOption); // 지도를 생성합니다
    var geocoder = new daum.maps.services.Geocoder();

    MapSearch.map = map;
    MapSearch.geocoder = geocoder;
}

function getLocation() {
    getPositionAndDoSomething(setCurrentPosition);
}

function getPositionAndDoSomething(successFunction) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(successFunction, NoGeo);
    } else {
        NoGeo();
    }
}

function makeOptions(point) {
    return     (mapOption = {
        center: point,
        level: 2 // 지도의 확대 레벨
    });
}

function NoGeo() {
    alert('It seems like Geolocation, which is required for this page, is not enabled in your browser. Please use a browser which supports it.');
}

setCurrentPosition = function (position) {
    if (position) {
        MapSearch.currentPosition = new daum.maps.LatLng(position.coords.latitude, position.coords.longitude);
        setMapAndGeo();
    } else {
        NoGeo();
    }
};

displayDong = function () {
    setAddress(MapSearch.map.getCenter());
};

getCenter = function () {
    return MapSearch.map.getCenter();
};


var callback = function (result, status) {
    if (status === daum.maps.services.Status.OK) {
        console.log(result);
        $('#address').html(result[0].address.region_3depth_name);
    }
};

findAddress = function (point) {
    doSomethingWithMapPoint(MapSearch.geocoder, point, callback);
};

doSomethingWithMapPoint = function (geocoder, newPoint, callback) {
    geocoder.coord2Address(newPoint.getLng(), newPoint.getLat(), callback);
};

makePoint = function (position) {
    return new daum.maps.LatLng(position.coords.latitude, position.coords.longitude);
};







//MAP SEARCH
function searchByMap() {
    doSomethingWithMapPoint(MapSearch.geocoder, getCenter(), search);
}

function search(result, status) {
    if (status === daum.maps.services.Status.OK) {
        setAddress(result);
        getResultsFromDong(MapSearch.currentAddress.code, MapSearch.currentAddress.dong);
    }
}

function setAddress(result) {

    MapSearch.currentAddress = new Address(
            result[0].address.region_1depth_name,
            result[0].address.region_2depth_name,
            result[0].address.region_3depth_name,
            result[0].address.main_address_no,
            result[0].address.zip_code
            );
}

function Address(city, gun, dong, road, code) {
    this.city = city;
    this.gun = gun;
    this.dong = dong;
    this.road = road;
    this.code = code;
}

function getResultsFromDong(zipCode, dong) {
    $.ajax({
        url: "/search/map",
        type: "get",
        async: true,
        data: {
            zipCode: zipCode,
            dong: dong
        },
        success: function (response) {
            parseResponse(response);
        }

    });
}

function parseResponse(response) {
    var json = response;
    var places = json.content;
    MapSearch.currentPage = json.number;
    MapSearch.totalPages = json.totalPages;
    addResultsToPage(places);
}

function addResultsToPage(places) {
    $('#results').empty();
    $.each(places, function (i, place) {
        $result = $("<div></div>", {id: 'result' + i, class: 'result', click: function () {
                window.open("/place/" + place.id);
            }
        });

        $result.append($("<div></div>", {
            id: 'result-name' + i, 'class': 'result-name', html: place.name }));
        
        $result.append($("<div></div>", {
            id: 'result-phone' + i, class: 'result-phone', html: place.phone}));
        
        $result.append($("<div></div>", {
            id: 'result-address' + i, class: 'result-address', html: place.address.full}));
        
        $result.append($("<div></div>", {
            id: 'result-hours' + i, class: 'result-hours', html: place.hours}));
        
        $('#results').append($result);
        
        addMarker(place);
    });

}
//DB to connect  -- 118.131.179.138
function addMarker(place) {
     makeMarker(place.lat,place.lng).setMap(MapSearch.map);
}

function makeMarker(lat, lng) {
    return new daum.maps.Marker({
        position: new daum.maps.LatLng(lat, lng)
    });
}




function setupAndDisplayMap(lat, lng) {
    var mapOption;
    mapOption = makeOptions(new daum.maps.LatLng(lat, lng));

    var map = new daum.maps.Map($('#map')[0], mapOption);
    makeMarker(lat,lng).setMap(map);
}



