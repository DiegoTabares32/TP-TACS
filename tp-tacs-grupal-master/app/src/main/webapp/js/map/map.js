function showMap() {
  var mapOptions = {
    zoom: 16,
    center: new google.maps.LatLng(-34.599896, -58.438771),
    mapTypeId: google.maps.MapTypeId.TERRAIN
  };

  var map = new google.maps.Map(document.getElementById("map-canvas"),
      mapOptions);

  var flightPlanCoordinates = [
   new google.maps.LatLng(-34.599896, -58.438771),     
   new google.maps.LatLng(-34.599773, -58.437720),
   new google.maps.LatLng(-34.597265, -58.438726),
];  

for(i=0; i < flightPlanCoordinates.length; i++){

 var marker = new google.maps.Marker({
      position: flightPlanCoordinates[i],
      map: map
    });
 
}

  var flightPath = new google.maps.Polyline({
    path: flightPlanCoordinates,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

  flightPath.setMap(map);
}

    