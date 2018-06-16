(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('userController', userController);

    userController.$inject = ['toastr', 'userService'];

    function userController(toastr, userService) {
        var userVm = this;
        userVm.list = [];
        userVm.dto = {};
        userVm.types = ["ADMIN", "DOCTOR"];
        userVm.selected = "DOCTOR";
        userVm.init = init;
        userVm.createNew = createNew;

        init();

        function init() {
            userService.getAll()
                .then(function (response) {
                    userVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            userVm.dto.userRole = userVm.selected;
            if(userVm.dto.email === undefined || userVm.dto.email === ''){
                toastr.error('Nedostaje email', 'Greska');
                return;
            }
            if(userVm.dto.password === undefined || userVm.dto.password === ''){
                toastr.error('Nedostaje sifra', 'Greska');
                return;
            }
            userService.createNew(userVm.dto)
                .then(function (response) {
                    init();
                    userVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }
       
    }

})();
