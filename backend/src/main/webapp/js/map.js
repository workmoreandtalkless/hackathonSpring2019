function initMap() {
    var myLatLng = {lat: 40.735389, lng: -73.814835};

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 4,
        center: myLatLng
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: 'Perfect!'
    });
    console.log("hello");
}