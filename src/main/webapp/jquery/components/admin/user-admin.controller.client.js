(function(){
	var $usernameFld = $('#username');
	var $passwordld = $('#password');
	var $firstNameFld = $('#firstName');
	var $lastNameFld = $('#lastName');
	var $roleFld = $('role');
	var tbody;
	
	function main() {
		tbody = $('tbody');
		var promise = fetch('https://localhost:8080');
		
	}
	
	function renderUsers() {
		for(var i=0; i<users.length; i++){
			var user = users[i];
			console.log(user);
			var clone = template.clone();
			clong.find('.username')
				.html(user.username);
			tbody.append(clone);
		}
	}
})();