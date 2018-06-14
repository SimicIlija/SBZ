(function () {
    'use strict';

    angular
        .module('cdss')
        .service('userService', userService);

    userService.$inject = ['$http', 'CONFIG'];

    function userService($http, CONFIG) {
        var service = {
            auth: auth,
            /*findBy: findBy,
            getAll: getAll,
            update: update,
            logout: logout*/
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

        /*function findBy(id) {
            return $http.get(CONFIG.SERVICE_URL + '/users/' + id)
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getAll() {
            return $http.get(CONFIG.SERVICE_URL + '/users')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function update(id, role) {
            return $http.post(CONFIG.SERVICE_URL + '/users/' + id, {
                message: role
            })
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
        }*/

    }
})();