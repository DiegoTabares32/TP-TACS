var app = app || {};
var id;
var token;

$(function() {

	//Usuario anonimo para esta entrega
	app.id_usuario = 0;
	//app.viajes = new app.Viajes();

	$('#link-perfil').attr('href','#/' + app.id_usuario + '/perfil');
	//new app.InicioView();
	app.router = new app.Router();
	Backbone.history.start();

	//Tooltips
	$('[data-toggle="tooltip"]').tooltip();
});

$(function() {
	  $.ajaxSetup({ cache: true });
	  $.getScript('//connect.facebook.net/en_US/sdk.js', function(){
	    FB.init({
	      appId: '1439890256312274',
	      version: 'v2.3'
	    });
	    FB.getLoginStatus(function(response) {
			updateStatusCallback(response);			
		});
	  });
});

$("#cerrarSesion").click(function(){	
	FB.logout(function(response) {		
		var url = "/";    
		$(location).attr('href',url);		
	});
});










