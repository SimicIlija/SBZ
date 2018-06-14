(function () {
    'use strict';

    angular
        .module('cdss')
        .service('DiseaseService', DiseaseService);

    DiseaseService.$inject = ['$http', 'CONFIG'];

    function DiseaseService($http, CONFIG) {
        var url = CONFIG.SERVICE_URL + 'api/disease/';
        var service = {
            createDisease: createDisease,
            readDisease: readDisease,
            updateDisease: updateDisease,
            deleteDisease: deleteDisease
        };

        return service;



        function createDisease(dto) {
            return $http.post(url, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function readDisease() {
            return $http.get(url)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function updateDisease(id, dto) {
            return $http.put(url + id, dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function deleteDisease(id) {
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