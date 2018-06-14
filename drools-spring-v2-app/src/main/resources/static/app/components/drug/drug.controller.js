(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('drugController', drugController);

    drugController.$inject = ['toastr', 'DrugService'];

    function drugController(toastr, DrugService) {
        var drugVm = this;
        drugVm.list = [];
        drugVm.dto = {};
        drugVm.editDto = {};
        drugVm.showEdit = false;
        drugVm.types = ["Antibiotic", "Analgesic", "Other"];
        drugVm.selected = "Antibiotik";
        drugVm.editSelected = "Antibiotik";
        drugVm.init = init;
        drugVm.deleteDI = deleteDI;
        drugVm.createNew = createNew;
        drugVm.startEdit = startEdit;
        drugVm.updateDI = updateDI;
        drugVm.translate = translate;
        drugVm.reverse = reverse;

        init();

        function init() {
            DrugService.readDrug()
                .then(function (response) {
                    drugVm.list = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function deleteDI(id) {
            DrugService.deleteDrug(id)
                .then(function (response) {
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function createNew() {
            drugVm.dto.drugType = drugVm.reverse(drugVm.selected);
            DrugService.createDrug(drugVm.dto)
                .then(function (response) {
                    init();
                    drugVm.dto = {};
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function updateDI() {
            drugVm.editDto.drugType = drugVm.reverse(drugVm.editSelected);
            DrugService.updateDrug(drugVm.editDto.id, drugVm.editDto)
                .then(function (response) {
                    init();
                    drugVm.editDto = {};
                    drugVm.showEdit = false;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function startEdit(id) {
            drugVm.showEdit = true;
            for (var i = 0; i < drugVm.list.length; i++) {
                if (drugVm.list[i].id == id) {
                    drugVm.editDto.id = drugVm.list[i].id;
                    drugVm.editDto.editSelected = drugVm.translate(drugVm.list[i].drugType);
                    drugVm.editDto.name = drugVm.list[i].name;
                }
            }
        }

        function translate(drugType) {
            if (drugType === 'Antibiotic') {
                return 'Antibiotik';
            }
            if (drugType === 'Analgesic') {
                return 'Analgetik';
            }
            if (drugType === 'Other') {
                return 'Ostali';
            }
            return '';
        }

        function reverse(drugType) {
            if (drugType === 'Antibiotik') {
                return 'Antibiotic';
            }
            if (drugType === 'Analgetik') {
                return 'Analgesic';
            }
            if (drugType === 'Ostali') {
                return 'Other';
            }
            return '';
        }
    }

})();
