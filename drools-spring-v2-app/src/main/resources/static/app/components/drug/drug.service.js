(function () {
    'use strict';

    angular
        .module('cdss')
        .service('DrugService', DrugService);

    DrugService.$inject = ['$http', 'CONFIG'];

    function DrugService($http, CONFIG) {
        var url = CONFIG.SERVICE_URL + 'api/drugs/';
        var service = {
            createDrug: createDrug,
            readDrug: readDrug,
            updateDrug: updateDrug,
            deleteDrug: deleteDrug
        };

        return service;



        function createDrug(dto) {
            return $http.post(url, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function readDrug() {
            return $http.get(url)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function updateDrug(id, dto) {
            return $http.put(url + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function deleteDrug(id) {
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