(function(){
	
	var userServiceClient = new UserServiceClient();

    var $usernameFld = $('#usernameFld');
    var $passwordFld = $('#passwordFld');
    var $firstNameFld = $('#firstNameFld');
    var $lastNameFld = $('#lastNameFld');
    var $dateOfBirthFld = $('#dateOfBirthFld');
    var $phoneFld = $('#phoneFld');
    var $emailFld = $('#emailFld');
    var $roleFld = $('#roleFld');
    var tbody;
    var $id;

    var $createUserBtn = $('#wbdv-create');
    var $updateUserBtn = $('#wbdv-update');

    $createUserBtn.click(createUser);
    $updateUserBtn.click(updateUser);
	
	function main() {
		//tbody = $('tbody');
		//var promise = fetch('https://localhost:8080');
		userServiceClient
		.findAllUsers()
		.then(renderUsers);
	}
	main();

	function createUser() {

		var usernameStr = $usernameFld.val();
		var passwordStr = $passwordFld.val();
		var firstNameStr =$firstNameFld.val();
		var lastNameStr = $lastNameFld.val();
		var dateOfBirthStr = $dateOfBirthFld.val();
		var phoneStr = $phoneFld.val();
		var emailStr = $emailFld.val();
		var roleStr = $roleFld.val();

		var userObj = {
			'username': usernameStr,
			'password' : passwordStr,
			'firstName' : firstNameStr,
			'lastName' : lastNameStr,
			'dateOfBirth' : dateOfBirthStr,
			'phone' : phoneStr,
			'email' : emailStr,
			'role' : roleStr
		};

		userServiceClient
			.register(userObj)
            .then(function() {
                userServiceClient.findAllUsers()
                    .then(renderUsers);
            });
	}
	
	function findAllUsers() {
		userServiceClient.findAllUsers();
	}
	
	function findUserById(id) {
		return userServiceClient.findUserById(id);
	}

    function selectUser(event) {
        var $button = $(event.currentTarget);
        var $id = $button.attr('id');
        findUserById($id)
            .then(renderUser);
    }
	
	function updateUser() {
        console.log($id);

        var usernameStr = $usernameFld.val();
        var passwordStr = $passwordFld.val();
        var firstNameStr =$firstNameFld.val();
        var lastNameStr = $lastNameFld.val();
        var dateOfBirthStr = $dateOfBirthFld.val();
        var phoneStr = $phoneFld.val();
        var emailStr = $emailFld.val();
        var roleStr = $roleFld.val();

        var user = {
            'username': usernameStr,
            'password' : passwordStr,
            'firstName' : firstNameStr,
            'lastName' : lastNameStr,
            'dateOfBirth' : dateOfBirthStr,
            'phone' : phoneStr,
            'email' : emailStr,
            'role' : roleStr
        };

		console.log(user);
        userServiceClient
            .updateUser($id, user)
            .then(function () {
                userServiceClient
                    .findAllUsers()
                    .then(renderUsers);
            });
		
	}
	
	function deleteUser(event) {
		//alert("Are you sure you want to delete?");
	    var $button = $(event.currentTarget);
	    var id = $button.attr('id');
	    console.log(event.currentTarget);
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
		$id = user.id;
		$usernameFld.val(user.username);
		$firstNameFld.val(user.firstName);
		$lastNameFld.val(user.lastName);
		$dateOfBirthFld.val(user.dateOfBirth);
		$phoneFld.val(user.phone);
		$emailFld.val(user.email);
		$roleFld.val(user.role);
	}
	
	function renderUsers(users) {
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
			td.append(user.dateOfBirth)
			tr.append(td);
			
			var td = $('<td>');
			td.append(user.phone);
			tr.append(td);
			
			var td = $('<td>');
			td.append(user.email);
			tr.append(td);
			
			var td = $('<td>');
			td.append(user.role);
			tr.append(td);
			
			var deleteBtn = $('<button class="btn btn-outline-primary fas fa-trash"></button>');
			deleteBtn.click(deleteUser);
			deleteBtn.attr('id', user.id);
			var editBtn = $('<button class="btn btn-outline-primary fas fa-edit"></button>');
			editBtn.click(selectUser);
			editBtn.attr('id', user.id);
			var td = $('<td>');
			td.append(deleteBtn);	
			td.append(editBtn)
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