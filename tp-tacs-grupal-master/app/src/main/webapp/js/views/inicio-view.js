var app = app || {};

app.InicioView = Backbone.View.extend({
	el: '#container',
	template: _.template($('#inicio-template').html()),

	initialize: function() {
		this.render();
	},

	render: function() {
		this.$el.html(this.template());
	}
});
