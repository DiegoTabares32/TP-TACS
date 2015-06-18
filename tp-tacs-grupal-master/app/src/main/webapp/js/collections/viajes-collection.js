var app = app || {};

app.Viajes = Backbone.Collection.extend({
	model: app.Viaje,
	url: function() {
		return 'rest/' + app.id_usuario + '/viajes'
	}
});
