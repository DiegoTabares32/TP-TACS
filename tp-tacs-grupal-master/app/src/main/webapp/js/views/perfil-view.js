var app = app || {};

app.PerfilView = Backbone.View.extend({
	el: '#container',
	template: _.template($('#perfil-template').html()),

	initialize: function() {
		$('#container').html('Cargando...');
		this.listenTo(this.model, 'change', this.render);
		this.model.fetch();
	},

	render: function() {
		this.$el.html(this.template(this.model.attributes));
	}
});
