(function () {
    'use strict';

    angular
        .module('cdss')
        .service('userService', userService);

    userService.$inject = ['$http', 'CONFIG'];

    function userService($http, CONFIG) {
        var service = {
            auth: auth,
            getAll: getAll,
            createNew: createNew,
            logout: logout
        };

        return service;

        function auth(credentials) {
            return $http.post(CONFIG.SERVICE_URL + 'api/users/login', credentials)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getAll() {
            return $http.get(CONFIG.SERVICE_URL + 'api/users')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function logout() {
            return $http.get(CONFIG.SERVICE_URL + '/auth/logout')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function createNew(dto) {
            return $http.post(CONFIG.SERVICE_URL + 'api/users', dto)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

    }
})();