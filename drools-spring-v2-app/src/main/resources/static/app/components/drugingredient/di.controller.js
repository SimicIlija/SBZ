(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('DIController', DIController);

    DIController.$inject = ['toastr', 'diService'];

    function DIController(toastr, diService) {
        var diVm = this;
        diVm.list = [];
        diVm.dto = {};
        diVm.editDto = {};
        diVm.showEdit = false;
        diVm.init = init;
        diVm.deleteDI = deleteDI;
        diVm.createNew = createNew;
        diVm.startEdit = startEdit;
        diVm.updateDI = updateDI;

        init();

        function init() {
            diService.readDI()
                .then(function (response) {
                    diVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function deleteDI(id) {
            diService.deleteDI(id)
                .then(function (response) {
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            diService.createDI(diVm.dto)
                .then(function (response) {
                    init();
                    diVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function updateDI() {
            diService.updateDI(diVm.editDto.id, diVm.editDto)
                .then(function (response) {
                    init();
                    diVm.editDto = {};
                    diVm.showEdit = false;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function startEdit(id) {
            diVm.showEdit = true;
            for (var i = 0; i < diVm.list.length; i++) {
                if (diVm.list[i].id == id) {
                    diVm.editDto.id = diVm.list[i].id;
                    diVm.editDto.name = diVm.list[i].ingredient;
                }
            }
        }
    }

})();
