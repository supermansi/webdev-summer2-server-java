(function (){
	var userServiceClient = new UserServiceClient();
	
	var $usernameFld, $passwordFld,
		$password2Fld, $registerBtn;
	
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
		console.log("clicked!");
		if(passwordStr != password2Str) {
            $('#passwordUnmatched').css('display','block');
		}
		
		else {
            $('#passwordUnmatched').css('display','none');
            var userObj = {
					'username': usernameStr,
					'password' : passwordStr
			};
			
			userServiceClient
				.findUserByUserName(usernameStr)
                .then(function(response){
                    return response.text();
                })
                .then(function(user) {
                	if(user.length) {
                		registrationFailed();
                	}
                	else{
                		userServiceClient.register(userObj)
                		.then(registrationSuccessful);
                	}
                });
		}
	}
	
	function registrationSuccessful() {
        $('#alertRegisterSuccess').css('display','block');
		window.location.replace('/jquery/components/profile/profile.template.client.html');
	}
	
	function registrationFailed() {
        $('#alertRegisterDanger').css('display','block');
	}
})();