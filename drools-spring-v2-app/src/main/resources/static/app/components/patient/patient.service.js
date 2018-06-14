(function () {
    'use strict';

    angular
        .module('cdss')
        .service('PatientService', PatientService);

    PatientService.$inject = ['$http', 'CONFIG'];

    function PatientService($http, CONFIG) {
        var url = CONFIG.SERVICE_URL + 'api/patients/';
        var service = {
            createPatient: createPatient,
            readPatient: readPatient,
            updatePatient: updatePatient,
            deletePatient: deletePatient
        };

        return service;



        function createPatient(dto) {
            return $http.post(url, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function readPatient() {
            return $http.get(url)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function updatePatient(id, dto) {
            return $http.put(url + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function deletePatient(id) {
            return $http.delete(url + id)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }
    }
})();