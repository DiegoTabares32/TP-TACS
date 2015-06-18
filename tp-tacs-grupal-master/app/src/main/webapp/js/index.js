function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);

	if (response.status === 'connected') {
		url = new URL("http://localhost:8080/?id_usuario="+response.authResponse.userID+"&token="+response.authResponse.accessToken);
		$.ajax({			
			type:'POST',
			url : url,
			data: null,
			contentType: "application/json",
			dataType : 'text',
			success : function(data) {			
				document.location.href = 'app.html';
			}
		});

	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not in app.
		document.getElementById('status').innerHTML = 'Inicia sesion con tu cuenta de Facebook';
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		document.getElementById('status').innerHTML = 'Inicia sesion con tu cuenta de Facebook';
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

//Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/es_LA/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

window.fbAsyncInit = function() {
	FB.init({
		appId : '1439890256312274',
		cookie : true, 
		xfbml : true, 
		version : 'v2.3' 
	});

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
};






