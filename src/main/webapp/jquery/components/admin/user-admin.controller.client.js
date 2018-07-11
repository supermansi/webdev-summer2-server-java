(function(){
	var $usernameFld = $('#username');
	var $passwordld = $('#password');
	var $firstNameFld = $('#firstName');
	var $lastNameFld = $('#lastName');
	var $roleFld = $('role');
	var tbody;
	
	function main() {
		//tbody = $('tbody');
		//var promise = fetch('https://localhost:8080');
		findAllUsers()
		.then(renderUsers);
	}
	
	main();
	
	function createUser() {}
	
	function findAllUsers() {
		var url = "/api/user";
		return fetch(url)
		.then(function(response){
			return response.json();
		})
	}
	
	function findUserById() {}
	
	function updateUser() {}
	
	function deleteUser(event) {
		console.log(event);
		var $button = $(event.currentTarget);
		var id = $button.attr('id');
		var url = "/api/user/" + id;
		
		fetch(url, {
			'method' : 'delete'
		}).then(function(){
			findAllUsers()
			.then(renderUsers);
		})
//		alert("Are you sure you want to delete user?");
	}
	
	function renderUser() {}
	
	function renderUsers(users) {
		console.log(users);
		var tbody = $('tbody');
		tbody.empty();
		for(var i=0; i<users.length; i++) {
			var user = users[i];
			
			var tr = $('<tr>');
			var td = $('<td>');
			td.append(user.username);
			tr.append(td);
			
			var td = $('<td>');
			td.append("********");
			tr.append(td);
			
			var td = $('<td>');
			td.append(user.firstName);
			tr.append(td);
			
			var td = $('<td>');
			td.append(user.lastName);
			tr.append(td);
			
			var td = $('<td>');
			td.append("test@example.com");
			tr.append(td);
			
			var td = $('<td>');
			td.append("Student");
			tr.append(td);
			
			var deleteBtn = $('<button class="btn btn-outline-primary">Delete</button>');
			deleteBtn.click(deleteUser);
			deleteBtn.attr('id', user.id);
			var td = $('<td>');
			td.append(deleteBtn);
			tr.append(td);
			
			tbody.append(tr);
		}
		/*for(var i=0; i<users.length; i++){
			var user = users[i];
			console.log(user);
			var clone = template.clone();
			clong.find('.username')
				.html(user.username);
			tbody.append(clone);
		}*/
	}
	
})();