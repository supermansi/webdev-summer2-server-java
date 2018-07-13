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
			.then(navigateToProfile, unsuccessful);
	}
	
	function navigateToProfile() {
        $('#alertLoginSuccess').css('display','block');
		window.location.replace("../profile/profile.template.client.html");
	}

	function unsuccessful() {
        $('#alertLoginDanger').css('display','block');
	}
	
})();