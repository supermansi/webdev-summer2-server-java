(function (){
	var usernameFld = $('#username');
	var passwordFld = $('#password');
	var password2Fld = $('#password2');
	var registerBtn = $('#registerBtn');
	
	registerBtn.click(registerUser);
	
	function registerUser() {
		var usernameStr = usernameFld.val();
		var passwordStr = passwordFld.val();
		var password2Str = password2Fld.val();
		
		if(passwordStr != password2Str) {
			alert('Passwords don\'t match!');
		}
		
		else {
			var userObj = {
					'username': usernameStr,
					'password' : passwordStr
			}
			
			var userObjStr = JSON.stringify(userObj);
			
			fetch('/register', {
				'method' : 'POST',
				'body' : userObjStr,
				'headers' : {
					'content-type' : 'application/json'
				},
				'credentials' : 'include'
			}).then(registrationSuccessful, registrationFailed)
		}
	}
	
	function registrationSuccessful() {
		window.location.replace('/jquery/components/profile/profile.template.client.html');
	}
	
	function registrationFailed() {
		alert('Oops! Something went wrong :/');
	}
})();