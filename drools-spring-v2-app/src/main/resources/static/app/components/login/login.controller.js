(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['toastr', 'userService', '$localStorage', '$location'];
    function LoginController(toastr, userService, $localStorage, $location) {
        var loginVm = this;
        loginVm.credentials = {};
        loginVm.auth = auth;

        function auth() {
            if (loginVm.credentials.email == undefined ||
                loginVm.credentials.password == undefined ||
                loginVm.credentials.email.length == 0 ||
                loginVm.credentials.password.length == 0) {
                toastr.error('Nedostaju kredencijali', 'Greska');
            } else {
                userService
                    .auth(loginVm.credentials)
                    .then(function (response) {
                        toastr.success('Uspesno ulogovan', 'Cestitam');
                        $localStorage.user = response;
                        $location.path('/home');
                    })
                    .catch(function (response) {
                        toastr.error(response.message, 'Greska');
                    });
            }
            return;
        }
    }

})();
