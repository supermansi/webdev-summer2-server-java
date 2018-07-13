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
		console.log("clicked!");
		var user = {
				"username" : $username.val(),
				"password" : $password.val()
		}
		
		userServiceClient
			.login(user)
			.then(navigateToProfile);
	}
	
	function navigateToProfile() {
		window.location.replace("../profile/profile.template.client.html");
	}
	
})();