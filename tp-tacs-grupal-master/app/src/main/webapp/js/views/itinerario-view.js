var app = app || {};

app.ItinerarioView = Backbone.View.extend({
	tagName: 'div',
	className: 'itineario',
	template: _.template($('#itinerario-template').html()),
	events: {
		'click #crearButton': 'doCrear'
	},

	initialize: function() {
		this.outboundChilds = [];
		this.inboundChilds = [];
		this.selectedOutbound = {};
		this.selectedInbound = {};
	},

	render: function() {
		this.$el.html(this.template(this.model.attributes));

		this.renderOutboundCollection();
		this.renderInboundCollection();
		
		return this;
	},

	renderOutboundCollection: function() {
		var outboundCollection = new app.Opciones(this.model.get('outbound'));
		outboundCollection.each(function(item) {
			this.renderOutboundItem(item);
		}, this);
	},

	renderOutboundItem: function(item) {
		var opcionView = new app.OpcionView({
			model: item
		});

		opcionView.type = 'outbound';
		opcionView.parent = this;
		this.outboundChilds.push(opcionView);

		this.$('#opciones-outbound-container').append(opcionView.render().el);
	},

	renderInboundCollection: function() {

		var inboundCollection = new app.Opciones(this.model.get('inbound'));
		inboundCollection.each(function(item) {

			this.renderInboundItem(item);
		}, this);
	},

	renderInboundItem: function(item) {
		var opcionView = new app.OpcionView({
			model: item
		});

		opcionView.type = 'inbound';
		opcionView.parent = this;
		this.inboundChilds.push(opcionView);


		this.$('#opciones-inbound-container').append(opcionView.render().el);
	},

	opcionSelected: function(selectedChild) {

		if (selectedChild.type == 'outbound') {
			this.selectedOutbound = selectedChild;
			this.doSelection(selectedChild, this.outboundChilds);
		}
	
		if (selectedChild.type == 'inbound') {
			this.selectedInbound = selectedChild;
			this.doSelection(selectedChild, this.inboundChilds);
		}

		if (!($.isEmptyObject(this.selectedOutbound)) && !($.isEmptyObject(this.selectedInbound))) {
			this.$('#crearButton').removeAttr('disabled');
		}


	},

	doSelection: function(selectedChild, allChild) {
		for (var i = 0 ; i < allChild.length ; i++) {
			allChild[i].$('#opcionPanel').attr('class', 'panel panel-default');
		}

		selectedChild.$('#opcionPanel').attr('class', 'panel panel-success');
	},

	doCrear: function() {
	
		var viaje = new app.Viaje({
			from: $('#fromInput').val(),
			to: $('#toInput').val(),
			departure_date: $('#departureInput').val(),
			return_date: $('#returnInput').val(),
			precio: this.model.get("precio"),
			outbound: this.selectedOutbound.model, 
			inbound: this.selectedInbound.model
		});

		var viajes = new app.Viajes();
		viajes.create(viaje);

		this.$('#crearButton').attr('class', 'btn btn-success center-block');
		this.$('#crearButton').attr('disabled', 'disabled');
		this.$('#help-crear').html('(Viaje creado)');

	}

});
