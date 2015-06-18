var app = app || {};

app.Itinerarios = Backbone.Collection.extend({
	model: app.Itinerario,
	url: 'rest/itinerarios'
});
