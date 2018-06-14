(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('diseaseController', diseaseController);

    diseaseController.$inject = ['toastr', 'DiseaseService'];

    function diseaseController(toastr, DiseaseService) {
        var diseaseVm = this;
        diseaseVm.list = [];
        diseaseVm.dto = {};
        diseaseVm.editDto = {};
        diseaseVm.showEdit = false;
        diseaseVm.init = init;
        diseaseVm.deleteDI = deleteDI;
        diseaseVm.createNew = createNew;
        diseaseVm.startEdit = startEdit;
        diseaseVm.updateDI = updateDI;

        init();

        function init() {
            DiseaseService.readDisease()
                .then(function (response) {
                    diseaseVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function deleteDI(id) {
            DiseaseService.deleteDisease(id)
                .then(function (response) {
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            DiseaseService.createDisease(diseaseVm.dto)
                .then(function (response) {
                    init();
                    diseaseVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function updateDI() {
            DiseaseService.updateDisease(diseaseVm.editDto.id, diseaseVm.editDto)
                .then(function (response) {
                    init();
                    diseaseVm.editDto = {};
                    diseaseVm.showEdit = false;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function startEdit(id) {
            diseaseVm.showEdit = true;
            for (var i = 0; i < diseaseVm.list.length; i++) {
                if (diseaseVm.list[i].id == id) {
                    diseaseVm.editDto.id = diseaseVm.list[i].id;
                    diseaseVm.editDto.name = diseaseVm.list[i].name;
                }
            }
        }
    }

})();
