var app = app || {};

app.MisViajesView = Backbone.View.extend({
	el: '#container',
	
	template: _.template($('#misViajes-template').html()),


	initialize: function() {
		this.$el.html('Cargando...');
		this.render();
	},

	render: function() {
		this.$el.html(this.template);

		this.collection = new app.Viajes();
		this.listenTo(this.collection, 'reset', this.renderCollection);		

		this.collection.fetch({
			reset: true
		});

	},

	renderCollection: function() {
		this.collection.each(function(item) {
			this.renderItem(item);
		}, this);
	},

	renderItem: function(item) {
		var miViajeView = new app.MiViajeView({
			model: item
		});

		$('#miViaje-container').append(miViajeView.render().el);
	},

});
