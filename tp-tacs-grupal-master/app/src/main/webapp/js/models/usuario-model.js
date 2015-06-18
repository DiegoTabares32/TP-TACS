var app = app || {};

app.Usuario = Backbone.Model.extend({
	url: function () {
		return '/rest/' + app.id_usuario + '/perfil'
	}

});
