(function(){
	
	var $username, $firstName, $lastName;
	
	function init() {
		
		$username = $('#username');
		$firstName = $('#firstName');
		$lastName = $('#lastName');
		
		findUserById(32)
		.then(renderUser);
	}
	init();
	
	function renderUser(user) {
		$username.val(user.username);
		$firstName.val(user.firstName);
		$lastName.val(user.lastName);
	}
	
	function findUserById(userId) {
		return fetch('/api/user/' + userId)
			.then(function(response) {
				return response.json();
			});
	}
	
	function handleResponse() {
		console.log(response);
	}
	
	/*fetch('/checkLogin',{
		'credentials' : 'include'
	}).then(function(response){
		console.log(response);
	});*/
})();