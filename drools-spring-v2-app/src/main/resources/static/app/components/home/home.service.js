(function () {
    'use strict';

    angular
        .module('cdss')
        .service('HomeService', HomeService);

    HomeService.$inject = ['$http', 'CONFIG'];

    function HomeService($http, CONFIG) {
        var base_url = CONFIG.SERVICE_URL;
        var service = {
            getPatients: getPatients,
            getHistory: getHistory,
            getDisease: getDisease,
            getConnect: getConnect,
            getAllDisease: getAllDisease,
            getAllDrugs: getAllDrugs,
            validateDis: validateDis,
            validateDrug: validateDrug,
            newVisit: newVisit
        };

        return service;


        function getPatients() {
            return $http.get(base_url + 'api/patients/')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getHistory(id) {
            return $http.get(base_url + 'api/diagnose/history/' + id)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getDisease(id, dto) {
            return $http.post(base_url + 'api/diagnose/' + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getConnect(id, dto) {
            return $http.post(base_url + 'api/diagnose/connected/' + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getAllDisease() {
            return $http.get(base_url + 'api/disease/')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getAllDrugs() {
            return $http.get(base_url + 'api/drugs/')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function validateDis(id){
            return $http.get(base_url + 'api/diagnose/bolesti/' + id)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function validateDrug(drugId, patientId){
            return $http.get(base_url + 'api/diagnose/drug/' + drugId + '/patient/' + patientId)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function newVisit(dto){
            return $http.post(base_url + 'api/diagnose/visit/', dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

    }
})();