function UserServiceClient() {
	
	this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.findUserByUserName = findUserByUserName;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.resetPassword = resetPassword;
    
    this.register = register;
    this.login = login;
    this.profile = profile;
    this.logout = logout;

	function createUser(user) {
    	var url = "/api/user";
    	return fetch(url, {
    		"method" : "POST",
    		body : JSON.stringify(user),
    		headers : {
    			"content-type" : "application/json"
    		}
    	});
    }

	function findAllUsers() {
		var url = "/api/user";
		return fetch(url)
			.then(function (response) {
		   return response.json();
		});	  
	  }

	function findUserById(id) {
		var url = "/api/user/" + id;
		return fetch(url)
			.then(function(response){
				return response.json();
			});
	}
	
	function findUserByUserName(userName) {
        return fetch('/api/user/username/' + userName);
    }
	  
	function updateUser(id, user) {
		var url = "/api/user/" + id;
		return fetch(url, {
			"method" : "PUT",
			"body" : JSON.stringify(user),
			"credentials" : "include",
			"headers" : {
				"content-type" : "application/json"
			}
		})
		.then(function(response){
			return response.json();
		})
	}
	
	function deleteUser(id) {
		var url = "/api/user/" + id;
		return fetch(url, {
			method: 'delete'
		});
	}

	function register(user) {
		var url = "/register";
		return fetch(url, {
			"method" : "POST",
			"credentials" : "same-origin",
			"body" : JSON.stringify(user),
			"headers" : {
				"content-type" : "application/json"
			}
		}).then(function(response){
			return response.json();
		});
	}
	
	function login(user) {
		var url = "/login";
		return fetch(url, {
			"method" : "POST",
			"body" : JSON.stringify(user),
			"credentials" : "same-origin",
			"headers" : {
				"content-type" : "application/json"
			}
		});
	}

	function profile(user) {
		var url = "/profile/" + user.username;
		return fetch(url, {
			"method" : "POST",
			"body" : JSON.stringify(user),
			"credentials" : "include",
			"headers" : {
				"content-type" : "application/json"
			}
		})
	}

	function resetPassword(email) {
		console.log("In service client");
		return fetch("/api/reset", {
			"method" : "POST",
			"body" : JSON.stringify(email),
			"headers" : {
				"content-type" : "application/json"
			}
		});
	}
	
	function logout() {
		var url = "/api/logout";
		return fetch(url, {
			"method" : "POST",
		})
	}
}