var app = app || {};

app.BuscarView = Backbone.View.extend({
	el: '#container',
	
	template: _.template($('#buscar-template').html()),
	events: {
		'click #buscarButton': 'doBuscar'
	},

	initialize: function() {
		this.render();
	},

	render: function() {
		this.$el.html(this.template);
	},

	renderCollection: function() {
		$('#result-container').html('');
		this.collection.each(function(item) {
			this.renderItem(item);
		}, this);
	},

	renderItem: function(item) {
		var ItinerarioView = new app.ItinerarioView({
			model: item
		});

		$('#result-container').append(ItinerarioView.render().el);
	},

	doBuscar: function(event) {
		$('#result-container').html('Cargando...');
		event.preventDefault();
		this.collection = new app.Itinerarios();
		this.listenTo(this.collection, 'reset', this.renderCollection);		

		this.collection.fetch({
			reset: true,
			data: {
				from: $('#fromInput').val(),
				to: $('#toInput').val(),
				departure_date: $('#departureInput').val(),
				return_date: $('#returnInput').val()
			},
			error: function(collection, xhr, options) {
				var errorObject = $.parseJSON(xhr.responseText);

				var errorModel = new app.ErrorModel();
				errorModel.set('status', errorObject.status);
				errorModel.set('message', errorObject.message);
				//errorModel.set('status', xhr.status);
				//errorModel.set('message', xhr.responseText);					

				var errorView = new app.ErrorView({
					model: errorModel
				});
				
				$('#result-container').html(errorView.render().el);

					
				
			}
		});

	}
	
});
