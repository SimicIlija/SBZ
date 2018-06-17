(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$localStorage', '$location'];

    function NavbarController($localStorage, $location) {
        var navbarVm = this;
        navbarVm.$storage = $localStorage;
        navbarVm.logout = logout;
        navbarVm.isGuest = isGuest;
        navbarVm.isDoctor = isDoctor;
        navbarVm.isAdmin = isAdmin;


        function isGuest() {
            return navbarVm.$storage.user === undefined;
        }

        function isDoctor() {
            console.log(navbarVm.$storage.user);
            return !isGuest() && navbarVm.$storage.user.userRole === 'DOCTOR';
        }

        function isAdmin() {
            return !isGuest() && navbarVm.$storage.user.userRole === 'ADMIN';
        }

        function logout() {
            $localStorage.$reset();
            $location.path('/login');
        }

    }

})();
