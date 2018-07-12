(function(){
	
	var userServiceClient = new UserServiceClient();
	
	function init() {
		//tbody = $('tbody');
		//var promise = fetch('https://localhost:8080');
		userServiceClient
		.findAllUsers()
		.then(renderUsers);
	}
	init();
	
	var $usernameFld = $('#username');
	var $passwordld = $('#password');
	var $firstNameFld = $('#firstName');
	var $lastNameFld = $('#lastName');
	var $dateOfBirthFld = $('#dateOfBirthFld');
	var $phoneFld = $('#phoneFld');
	var $emailFld = $('#emailFld');
	var $roleFld = $('role');
	var tbody;
	
	var $createUserBtn = $('#wbdv-create');
	var $updateUserBtn = $('#wbdv-update');
	
	$createUserBtn.click(createUser);
	$updateUserBtn.click(updateUser);
	
	function createUser(event) {
		console.log(event);
	    var $button = $(event.currentTarget);
	    var id = $button.attr('id');
		console.log(id);
		
		var usernameStr = $usernameFld.val();
		var passwordStr = $passwordFld.val();
		var firstNameStr =$firstNameFld.val();
		var passwordStr = passwordFld.val();
	}
	
	function updateUser() {
        $tbody=$(".wbdv-form");
        var user = {
           firstName: $tbody.find("#firstNameFld").val(),
        lastName: $tbody.find("#lastNameFld").val(),
        role: $tbody.find("#roleFld").val()
        };


        userService
            .updateUser($userId, user)
            .then(findAllUsers);
    }
	
	function findAllUsers() {
		var url = "/api/user";
		return fetch(url)
		.then(function(response){
			return response.json();
		})
	}
	
	function findUserById(id) {
		return userServiceClient.findUserById(id);
	}
	
	function updateUser(even) {
		console.log(event);
		var $button = $(event.currentTarget);
		
	}
	
	function deleteUser(event) {
		//alert("Are you sure you want to delete?");
	    console.log(event);
	    var $button = $(event.currentTarget);
	    var id = $button.attr('id');

	    userServiceClient
	      .deleteUser(id)
	      .then(function () {
	        userServiceClient
	          .findAllUsers()
	          .then(renderUsers);
	      });
	}
	
	function renderUser(user) {
		tbody = $('#wbdv-form');
		 $tbody.find("#usernameFld").val(user.username);
	     $tbody.find("#firstNameFld").val(user.firstName);
	     $tbody.find("#lastNameFld").val(user.lastName);
	     $tbody.find("#dateOfBirthFld").val(user.dateOfBirth);
	     $tbody.find("#phoneFld").val(user.phone);
	     $tbody.find("#emailFld").val(user.email);
	     $tbody.find("#roleFld").val(user.role);
	}
	
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
			td.append("07/05/1992")
			tr.append(td);
			
			var td = $('<td>');
			td.append("(xxx)xxx-xxxx");
			tr.append(td);
			
			var td = $('<td>');
			td.append("test@example.com");
			tr.append(td);
			
			var td = $('<td>');
			td.append("Student");
			tr.append(td);
			
			var deleteBtn = $('<button class="btn btn-outline-primary fas fa-trash"></button>');
			deleteBtn.click(deleteUser);
			deleteBtn.attr('id', user.id);
			var updateBtn = $('<button class="btn btn-outline-primary fas fa-edit"></button>');
			updateBtn.click(updateUser);
			updateBtn.attr('id', user.id);
			var td = $('<td>');
			td.append(deleteBtn);	
			td.append(updateBtn)
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