(function () {
    'use strict';

    angular
        .module('cdss')
        .service('SymptomsService', SymptomsService);

    SymptomsService.$inject = ['$http', 'CONFIG'];

    function SymptomsService($http, CONFIG) {
        var url = CONFIG.SERVICE_URL + 'api/symptoms/';
        var service = {
            createSymptoms: createSymptoms,
            readSymptoms: readSymptoms,
            updateSymptoms: updateSymptoms,
            deleteSymptoms: deleteSymptoms
        };

        return service;



        function createSymptoms(dto) {
            return $http.post(url, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function readSymptoms() {
            return $http.get(url)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function updateSymptoms(id, dto) {
            return $http.put(url + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function deleteSymptoms(id) {
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