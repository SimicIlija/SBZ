(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['toastr', 'HomeService'];

    function HomeController(toastr, HomeService) {
        var homeVm = this;
        homeVm.patient = null;
        homeVm.patientList = [];
        homeVm.isSelected = false;
        homeVm.previousVisits = [];
        homeVm.patSymptoms = [];
        homeVm.symptom = {};
        homeVm.dis1 = [];
        homeVm.dis2 = [];
        homeVm.drugs = [];
        homeVm.allDis = [];
        homeVm.drug = null;
        homeVm.disease = null;
        homeVm.symDis = [];
        homeVm.init = init;
        homeVm.selected = selected;
        homeVm.getHistory = getHistory;
        homeVm.addSymptom = addSymptom;
        homeVm.deleteSymptom = deleteSymptom;
        homeVm.getDisease = getDisease;
        homeVm.getConnected = getConnected;
        homeVm.validateDrug = validateDrug;
        homeVm.validateSym = validateSym;
        homeVm.newVisit = newVisit;

        init();

        function init() {
            HomeService.getPatients()
                .then(function (response) {
                    homeVm.patient = null;
                    homeVm.patientList = [];
                    homeVm.isSelected = false;
                    homeVm.previousVisits = [];
                    homeVm.patSymptoms = [];
                    homeVm.symptom = {};
                    homeVm.dis1 = [];
                    homeVm.dis2 = [];
                    homeVm.drugs = [];
                    homeVm.allDis = [];
                    homeVm.drug = null;
                    homeVm.disease = null;
                    homeVm.symDis = [];
                    homeVm.patientList = response;
                    for (var i = 0; i < homeVm.patientList.length; i++) {
                        homeVm.patientList[i].name = homeVm.patientList[i].firstName + " " + homeVm.patientList[i].lastName;
                    }
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function selected() {
            if (homeVm.patient === null) {
                toastr.error('Izaberi pacijenta', 'Greska');
                homeVm.isSelected = false;
                homeVm.previousVisits = [];
                return;
            }
            homeVm.isSelected = true;
            homeVm.previousVisits = [];
            homeVm.getHistory(homeVm.patient.id);
        }

        function getHistory(id) {
            HomeService.getHistory(id)
                .then(function (response) {
                    homeVm.previousVisits = response;
                    HomeService.getAllDrugs(id)
                        .then(function (response) {
                            homeVm.drugs = response;
                            homeVm.drug = homeVm.drugs[0];
                            HomeService.getAllDisease(id)
                                .then(function (response) {
                                    homeVm.allDis = response;
                                    homeVm.disease = homeVm.allDis[0];
                                })
                                .catch(function (response) {
                                    toastr.error(response.message, 'Greska');
                                });
                        })
                        .catch(function (response) {
                            toastr.error(response.message, 'Greska');
                        });
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function addSymptom() {
            if (homeVm.symptom.name === undefined || homeVm.symptom.name === '') {
                toastr.error('Popuni opis simptoma', 'Greska');
            }
            homeVm.patSymptoms.push({
                'id': 0, 'description': homeVm.symptom.name,
                'value': homeVm.symptom.value || 0
            })
            homeVm.symptom = {};
        }

        function deleteSymptom(index) {
            homeVm.patSymptoms.splice(index, 1);
        }

        function getDisease() {
            var id = homeVm.patient.id;
            var dto = {};
            dto.symptoms = homeVm.patSymptoms;
            HomeService.getDisease(id, dto)
                .then(function (response) {
                    homeVm.dis1 = response.filter(function (n) { return n != null });;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function getConnected() {
            var id = homeVm.patient.id;
            var dto = {};
            dto.symptoms = homeVm.patSymptoms;
            HomeService.getConnect(id, dto)
                .then(function (response) {
                    homeVm.dis2 = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function validateSym() {
            var id = homeVm.disease.id;
            HomeService.validateDis(id)
                .then(function (response) {
                    homeVm.symDis = response.symptoms;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function validateDrug() {
            var drugId = homeVm.drug.id;
            var patientId = homeVm.patient.id;
            HomeService.validateDrug(drugId, patientId)
                .then(function (response) {
                    if (response) {
                        toastr.error('Promenite lek', 'Pacijent alergican');
                    } else {
                        toastr.success('Pacijent nije alergican', 'OK');
                    }

                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function newVisit() {
            var dto = {};
            dto.drug = homeVm.drug;
            dto.disease = homeVm.disease;
            dto.symptoms = homeVm.patSymptoms;
            dto.patient = homeVm.patient;
            HomeService.newVisit(dto)
                .then(function (response) {
                    toastr.success('Uspostavljena dijagnoza', 'OK');
                    init();
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }
    }

})();
