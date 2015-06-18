var app = app || {};

app.ErrorView = Backbone.View.extend({
	tagName: 'div',
	className: 'error',
	template: _.template($('#error-template').html()),

	render: function() {
		this.$el.html(this.template(this.model.attributes));
		return this;
	},

});
