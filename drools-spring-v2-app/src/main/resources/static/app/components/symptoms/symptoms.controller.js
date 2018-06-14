(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('symptomsController', symptomsController);

    symptomsController.$inject = ['toastr', 'SymptomsService'];

    function symptomsController(toastr, SymptomsService) {
        var symptomsVm = this;
        symptomsVm.list = [];
        symptomsVm.dto = {};
        symptomsVm.editDto = {};
        symptomsVm.showEdit = false;
        symptomsVm.init = init;
        symptomsVm.deleteDI = deleteDI;
        symptomsVm.createNew = createNew;
        symptomsVm.startEdit = startEdit;
        symptomsVm.updateDI = updateDI;

        init();

        function init() {
            SymptomsService.readSymptoms()
                .then(function (response) {
                    symptomsVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function deleteDI(id) {
            SymptomsService.deleteSymptoms(id)
                .then(function (response) {
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            SymptomsService.createSymptoms(symptomsVm.dto)
                .then(function (response) {
                    init();
                    symptomsVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function updateDI() {
            SymptomsService.updateSymptoms(symptomsVm.editDto.id, symptomsVm.editDto)
                .then(function (response) {
                    init();
                    symptomsVm.editDto = {};
                    symptomsVm.showEdit = false;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function startEdit(id) {
            symptomsVm.showEdit = true;
            for (var i = 0; i < symptomsVm.list.length; i++) {
                if (symptomsVm.list[i].id == id) {
                    symptomsVm.editDto.id = symptomsVm.list[i].id;
                    symptomsVm.editDto.name = symptomsVm.list[i].description;
                }
            }
        }
    }

})();
