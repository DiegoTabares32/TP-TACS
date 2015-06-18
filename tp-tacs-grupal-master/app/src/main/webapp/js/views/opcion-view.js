var app = app || {};

app.OpcionView = Backbone.View.extend({
	tagName: 'div',
	className: 'opcion',
	template: _.template($('#opcion-template').html()),
	events: {
		'click': 'seleccion'
	},

	render: function() {
		this.$el.html(this.template(this.model.attributes));
		return this;
	},

	seleccion: function() {
		this.parent.opcionSelected(this);
		//this.$('#opcionPanel').attr('class', 'panel panel-success');
	}

});
