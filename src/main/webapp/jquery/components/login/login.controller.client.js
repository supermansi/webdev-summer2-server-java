(function (){
	var $username, $password, $loginBtn;

	var userServiceClient = new UserServiceClient();
	
	function init() {
		$username = $('#username');
		$password = $('#password');
		$loginBtn = $('#loginBtn');
		
		$loginBtn.click(login);
	}
	
	init();
	
	function login() {
		var user = {
				"username" : $username.val(),
				"password" : $password.val()
		}
		
		userServiceClient
			.login(user)
			.then(function(response) {
				return response.text();
			})
			.then(function(user) {
				if(user.length) {
					navigateToProfile();
				}
				else {
					unsuccessful();
				}
			});
	}
	
	function navigateToProfile() {
        $('#alertLoginSuccess').css('display','block');
		window.location.replace("../profile/profile.template.client.html");
	}

	function unsuccessful() {
        $('#alertLoginDanger').css('display','block');
	}
	
})();