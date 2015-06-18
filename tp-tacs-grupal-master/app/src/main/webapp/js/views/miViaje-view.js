var app = app || {};

app.MiViajeView = Backbone.View.extend({
	tagName: 'div',
	className: 'viaje',
	template: _.template($('#miViaje-template').html()),
	events: {
		'click #mapaButton': 'doVerMapa'
	},
	mapContainer: this.$('#map-canvas'),

	render: function() {
		this.$el.html(this.template(this.model.attributes));

		return this;
	},

	doVerMapa: function(event) {
		event.preventDefault();
		var input = event.delegateTarget;
		var divMap = $(input).find("#map-canvas")[0];
	
		var urlRoot = '/rest/geo/list?';
	  var queryString = '';

		var first = 1;

		var outboundAirportIdList = [];
		var inboundAirportIdList = [];

		var mapOptions = {
			zoom: 8,
			mapTypeId: google.maps.MapTypeId.TERRAIN
		};

		var map = new google.maps.Map(divMap, mapOptions);

		var bounds = new google.maps.LatLngBounds();

		outboundAirportIdList.push(this.model.attributes.outbound.segmentos[0].from);
		_.each(this.model.attributes.outbound.segmentos, function(segmento){
				outboundAirportIdList.push(segmento.to);
		});

		inboundAirportIdList.push(this.model.attributes.inbound.segmentos[0].from);
		_.each(this.model.attributes.inbound.segmentos, function(segmento){
				inboundAirportIdList.push(segmento.to);
		});


		//OUTBOUND
	  $.each(outboundAirportIdList, function(index, value) {
		

			if (first == 1) {
	    	queryString = queryString + 'airport_codes=' + value;
				first = 0;
			}	else {
				queryString = queryString + '&airport_codes=' + value;
			}
		});

	  $.getJSON(urlRoot + queryString, function(data) {
			var flightPlanCoordinates = [];

			$.each(data.path, function(index, value) {
				flightPlanCoordinates.push(new google.maps.LatLng(value.latitude, value.longitude));

				console.log(value.latitude);
				console.log(value.longitude);
			});

			console.log(flightPlanCoordinates);

			for(i=0; i < flightPlanCoordinates.length; i++){
				bounds.extend(flightPlanCoordinates[i]);
				new google.maps.Marker({
			    position: flightPlanCoordinates[i],
			    map: map
			  });
			 
			}

			map.fitBounds(bounds);
			map.panToBounds(bounds);

			  var flightPath = new google.maps.Polyline({
			    path: flightPlanCoordinates,
			    geodesic: true,
			    strokeColor: '#000099',
			    strokeOpacity: 1.0,
			    strokeWeight: 2
			  });

			  flightPath.setMap(map);

	  });

		//INBOUND
		queryString = [];
	  $.each(inboundAirportIdList, function(index, value) {
		

			if (first == 1) {
	    	queryString = queryString + 'airport_codes=' + value;
				first = 0;
			}	else {
				queryString = queryString + '&airport_codes=' + value;
			}
		});

	  $.getJSON(urlRoot + queryString, function(data) {
			var flightPlanCoordinates = [];

			$.each(data.path, function(index, value) {
				flightPlanCoordinates.push(new google.maps.LatLng(value.latitude, value.longitude));

			});

			for(i=0; i < flightPlanCoordinates.length; i++){
				bounds.extend(flightPlanCoordinates[i]);
				new google.maps.Marker({
			    position: flightPlanCoordinates[i],
			    map: map
			  });
			 
			}

			map.fitBounds(bounds);
			map.panToBounds(bounds);

			  var flightPath = new google.maps.Polyline({
			    path: flightPlanCoordinates,
			    geodesic: true,
			    strokeColor: '#FF0000',
			    strokeOpacity: 0.5,
			    strokeWeight: 6
			  });

			  flightPath.setMap(map);

	  });
	}

});
