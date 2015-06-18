var app = app || {};

app.Router = Backbone.Router.extend({
	routes: {
		'misViajes': 'misViajes',
		'busqueda': 'busqueda',
		':id_usuario/perfil': 'perfil',
		':id_amigo/amigo': 'perfilAmigo',
		'cerrarSesion': 'cerrarSesion',
		'': 'inicio',
	},

	busqueda:function() {
		$('#nav-viajes').attr('class', 'active');
		$('#nav-inicio').attr('class','');
		$('#nav-perfil').attr('class','');
		$('#nav-misViajes').attr('class','');
		$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');

		new app.BuscarView();
	},

	perfil:function() {
		$('#nav-perfil').attr('class', 'active');
		$('#nav-viajes').attr('class','');
		$('#nav-inicio').attr('class','');
		$('#nav-misViajes').attr('class','');
		$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');

		var usuario = new app.Usuario();
		new app.PerfilView({model: usuario});
	},

	perfilAmigo:function(id_amigo){
		var amigo = new app.Usuario();
		amigo.url = '/rest/'+id_amigo+'/perfil';
		new app.PerfilView({model: amigo});
	},

	inicio:function() {
		$('#nav-inicio').attr('class', 'active');
		$('#nav-perfil').attr('class','');
		$('#nav-viajes').attr('class','');
		$('#nav-misViajes').attr('class','');
		$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');

		new app.InicioView();
	},
	
	cerrarSesion:function() {
		$('#nav-cerrarSesion').attr('class', 'active');
		$('#nav-inicio').attr('class','');
		$('#nav-perfil').attr('class','');
		$('#nav-misViajes').attr('class','');
		$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');
	},

	misViajes:function() {
		$('#nav-misViajes').attr('class', 'active');
		$('#nav-inicio').attr('class','');
		$('#nav-perfil').attr('class','');
		$('#nav-viajes').attr('class','');
		$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');

		new app.MisViajesView();
	}

});
