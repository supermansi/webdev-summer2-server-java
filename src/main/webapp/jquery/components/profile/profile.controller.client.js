(function () {

    var $username, $firstName, $lastName,
        $email, $phone, $dateOfBirth, $role;
    var $updateBtn;
    var $logoutBtn;
    var currentUser = null;

    var userServiceClient = new UserServiceClient();

    function init() {

        $username = $("#username");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $email = $('#email');
        $phone = $('#phone');
        $dateOfBirth = $('#dateOfBirth');
        $role = $('#role');

        $updateBtn = $("#updateBtn");
        $updateBtn.unbind('click').click(updateUser);

        $logoutBtn = $('#logoutBtn');
        $logoutBtn.click(logoutUser);

        //$logoutBtn = $('logoutBtn');
        //$logoutBtn.click(logoutUser);

        // findUserById(7)
        //   .then(renderUser)
        profile()
            .then(renderUser);
    }
    init();

    function updateUser() {

        $username = $("#username");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $email = $('#email');
        $phone = $('#phone');
        $dateOfBirth = $('#dateOfBirth');
        $role = $('#role');

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
        }).then(function(response){
            return response.json();
        });
    }

    function renderUser(user) {
        currentUser = user;
        $username.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $email.val(user.email);
        $phone.val(user.phone);
        $dateOfBirth.val(user.dateOfBirth);
        $role.val(user.role);
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

    function logoutUser() {
        userServiceClient.logout()
            .then(function(response) {
                return response.json();
            });
    }
})();