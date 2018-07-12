/*(function(){
	
	var $username, $firstName, $lastName;
	
	function init() {
		
		$username = $('#username');
		$firstName = $('#firstName');
		$lastName = $('#lastName');
		$updateBtn = $('#updateBtn');
		
		$updateBtn.click(updateUser);
		
		findUserById(52)
		.then(renderUser);
	}
	init();
	
	function renderUser(user) {
		$username.val(user.username);
		$firstName.val(user.firstName);
		$lastName.val(user.lastName);
		console.log($username);
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
	
	function updateUser() {
		
	}
	
	fetch('/checkLogin',{
		'credentials' : 'include'
	}).then(function(response){
		console.log(response);
	});
})();*/


(function () {

	  var $username, $firstName, $lastName,
	  	$email, $phone, $dateOfBirth, $role;
	    $updateBtn;
	  var currentUser = null;

	  function init() {

	    $username = $("#username");
	    $firstName = $("#firstName");
	    $lastName = $("#lastName");
	    $email = $('#email');
	    $phone = $('#phone');
	    $dateOfBirth = $('#dateOfBirth');
	    $role = $('#role');
	    
	    $updateBtn = $("#updateBtn");
	    $updateBtn.click(updateUser);

	    // findUserById(7)
	    //   .then(renderUser)
	    profile()
	      .then(renderUser);
	  }
	  init();
	  
	  function updateUser() {
	    var user = {
	      "username" : $username.val(),
	      "firstName": $firstName.val(),
	      "lastName": $lastName.val(),
	      "email" : $email.val(),
	      "phone" : $phone.val(),
	      "dateOfBirth" : $dateOfBirth.val(),
	      "role" : $role.val()
	    };

	    fetch("/api/user/" + currentUser.id, {
	      method: 'put',
	      body: JSON.stringify(user),
	      'credentials': 'include',
	      headers: {
	        'content-type': 'application/json'
	      }
	    });
	  }

	  function renderUser(user) {
	    currentUser = user;
	    $username.val(user.username);
	    $firstName.val(user.firstName);
	    $lastName.val(user.lastName);
	    $email.val(user.email);
	    $phone.val(user.phone);
	    $dateOfBirth(user.dateOfBirth);
	    $role(user.role);
	  }

	  function profile() {
	    return fetch('/profile', {
	      'credentials': 'include'
	    })
	    .then(function (response) {
	      return response.json();
	    });
	  }

	  function findUserById(userId) {
	    return fetch('/api/user/' + userId)
	      .then(function (response) {
	        return response.json();
	      });
	  }
	  
	  function handleResponse() {
	    
	  }
	})();