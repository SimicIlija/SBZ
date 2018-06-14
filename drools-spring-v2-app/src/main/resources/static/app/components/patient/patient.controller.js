(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('patientController', patientController);

    patientController.$inject = ['toastr', 'PatientService'];

    function patientController(toastr, PatientService) {
        var patientVm = this;
        patientVm.list = [];
        patientVm.dto = {};
        patientVm.editDto = {};
        patientVm.showEdit = false;
        patientVm.init = init;
        patientVm.deleteDI = deleteDI;
        patientVm.createNew = createNew;
        patientVm.startEdit = startEdit;
        patientVm.updateDI = updateDI;

        init();

        function init() {
            PatientService.readPatient()
                .then(function (response) {
                    patientVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function deleteDI(id) {
            PatientService.deletePatient(id)
                .then(function (response) {
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            PatientService.createPatient(patientVm.dto)
                .then(function (response) {
                    init();
                    patientVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function updateDI() {
            PatientService.updatePatient(patientVm.editDto.id, patientVm.editDto)
                .then(function (response) {
                    init();
                    patientVm.editDto = {};
                    patientVm.showEdit = false;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function startEdit(id) {
            patientVm.showEdit = true;
            for (var i = 0; i < patientVm.list.length; i++) {
                if (patientVm.list[i].id == id) {
                    patientVm.editDto.id = patientVm.list[i].id;
                    patientVm.editDto.firstName = patientVm.list[i].firstName;
                    patientVm.editDto.lastName = patientVm.list[i].lastName;
                }
            }
        }
    }

})();
