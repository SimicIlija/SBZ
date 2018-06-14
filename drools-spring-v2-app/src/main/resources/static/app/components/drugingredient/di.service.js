(function () {
    'use strict';

    angular
        .module('cdss')
        .service('diService', diService);

    diService.$inject = ['$http', 'CONFIG'];

    function diService($http, CONFIG) {
        var service = {
            createDI: createDI,
            readDI: readDI,
            updateDI: updateDI,
            deleteDI: deleteDI
        };

        return service;

        function createDI(dto) {
            return $http.post(CONFIG.SERVICE_URL + 'api/drugingredient', dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function readDI() {
            return $http.get(CONFIG.SERVICE_URL + 'api/drugingredient')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function updateDI(id, dto) {
            return $http.put(CONFIG.SERVICE_URL + 'api/drugingredient/' + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function deleteDI(id) {
            return $http.delete(CONFIG.SERVICE_URL + 'api/drugingredient/' + id)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }
    }
})();