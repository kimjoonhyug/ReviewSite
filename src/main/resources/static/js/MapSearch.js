var MapSearch = {};







//START
$(document).ready(function () {
    getLocation();

    console.log(MapSearch.currentPosition);

});

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
        getResultsFromDong(MapSearch.currentAddress.dong);
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

function getResultsFromDong(dong) {
    $.ajax({
        url:"/search/map",
        type:"get",
        async: true,
        data:{
            dong:dong
        },
        success: function(response){
          console.log(response);  
        }
        
    });
}



