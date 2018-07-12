(function (){
	var userServiceClient = new UserServiceClient();
	
	var $usernameFld, $passwordFld,
		$password2Fld, $registerBtn;
	
	$(main);
	
	function main() {
		$usernameFld = $('#username');
		$passwordFld = $('#password');
		$password2Fld = $('#password2');
		$registerBtn = $('#registerBtn');
		$registerBtn.click(registerUser);
	}
	main();
	
	function registerUser() {
		var usernameStr = $usernameFld.val();
		var passwordStr = $passwordFld.val();
		var password2Str = $password2Fld.val();
		if(passwordStr != password2Str) {
			alert('Passwords don\'t match!');
		}
		
		else {
			var userObj = {
					'username': usernameStr,
					'password' : passwordStr
			};
			
			userServiceClient
				.register(userObj)
				.then(registrationSuccessful, registrationFailed)
		}
	}
	
	function registrationSuccessful(event) {
		console.log(event);
		window.location.replace('/jquery/components/profile/profile.template.client.html');
	}
	
	function registrationFailed() {
		alert('Oops! Something went wrong :/');
	}
})();