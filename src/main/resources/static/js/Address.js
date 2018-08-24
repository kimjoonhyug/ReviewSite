function setLatLng(address) {
    findAddressAndDo(address, callback);
}

function findAddressAndDo(address, callback) {
    var geocoder = new daum.maps.services.Geocoder();

    var options = {
        page: 1,
        size: 1
    };

    geocoder.addressSearch(address, callback, options);

}

function callback(result, status) {
    if (status === daum.maps.services.Status.OK) {
        //TODO set data into lat,lng input
        console.log(result);
        $('#lat').val(result[0].road_address.y);
        $('#lng').val(result[0].road_address.x);
    }
}



