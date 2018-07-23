var MapSearch = {};
$(document).ready(function () {
    var mapContainer = document.getElementById('map'), 
            mapOption = {
                center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 2 // 지도의 확대 레벨
            };

    var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    var geocoder = new daum.maps.services.Geocoder();

    MapSearch.map = map;
    MapSearch.geocoder = geocoder;
});


if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(successFunction, errorFunction);
} else {
    alert('It seems like Geolocation, which is required for this page, is not enabled in your browser. Please use a browser which supports it.');
}


function setCurrentPosition(position) {
    MapSearch.currentPosition = new daum.maps.LatLng(position.get, 126.570667);
    
}

searchByMap = function () {
    setAddress(MapSearch.map.getCenter());
};

getCenter = function() {
    return MapSearch.map.getCenter();
};


var callback = function (result, status) {
    if (status === daum.maps.services.Status.OK) {
        console.log(result);
        $('#address').html(result[0].address.region_3depth_name);
    }
};

setAddress = function (point) {
    displayDong(MapSearch.geocoder, point, callback);
};

displayDong = function (geocoder, newPoint, callbackk) {
    geocoder.coord2Address(newPoint.getLng(), newPoint.getLat(), callback);
};

makePoint = function (position) {
    return new daum.maps.LatLng(position.coords.latitude, position.coords.longitude);
};

