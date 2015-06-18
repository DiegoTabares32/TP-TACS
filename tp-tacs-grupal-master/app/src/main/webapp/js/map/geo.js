var app = app || {};

app.fetchCoordinates = function(airportIdList) {
  console.log('fetching getCoordinate');
  var urlRoot = '/rest/geo/list?';
  var queryString = '';

	var first = 1;

  $.each(airportIdList, function(index, value) {
		

		if (first == 1) {
    	queryString = queryString + 'airport_codes=' + value;
			first = 0;
		}	else {
			queryString = queryString + '&airport_codes=' + value;
		}
	});

  $.getJSON(urlRoot + queryString, function(data) {
    console.log(data);
  });
}

