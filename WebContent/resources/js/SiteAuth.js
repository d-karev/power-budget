/**
 * author: Dobromir Karev
 * year: 2017
 */

function onSuccess(googleUser) {
	var token = googleUser.getAuthResponse().id_token;

	$("[id='authFormIn:passIdToken']").val(token);
	$("[id='authFormIn:signInUser']").click();
	console.log('Logged in as: ' + googleUser.getBasicProfile().getName());		    	
}

function onFailure(error) {
	console.log(error);
	signOut();
}		   

function signOut() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
	});
	
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function () {
		console.log('User logged out.');
		$("[id='authFormOut:signOutUser']").click();
	});	
}

function renderButton() {		
	if ($('#my-signin2-footer').length) {
		gapi.signin2.render('my-signin2-footer', {
			'scope': 'profile email',
			'width': 120,
			'height': 28,
			'longtitle': false,
			'theme': 'light',
			'onfailure': onFailure
		});	
	}	
	
	if ($('#my-signin2-index').length) {
		gapi.signin2.render('my-signin2-index', {
			'scope': 'profile email',
			'width': 178,
			'height': 34,
			'longtitle': true,
			'theme': 'dark',
			'onsuccess': onSuccess,
			'onfailure': onFailure
		});
	}		
}