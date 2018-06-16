(function () {
    'use strict';

    angular
        .module('cdss')
        .service('ReportService', ReportService);

    ReportService.$inject = ['$http', 'CONFIG'];

    function ReportService($http, CONFIG) {
        var url = CONFIG.SERVICE_URL + 'api/reports/';
        var service = {
            getHronicne:getHronicne,
            getZavisne:getZavisne,
            getImunitet:getImunitet
        };

        return service;


        function getHronicne() {
            return $http.get(url + 'hronicni')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getZavisne() {
            return $http.get(url + 'zavisnici')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

        function getImunitet() {
            return $http.get(url + 'imunitet')
                .then(function success(response) {
                    return response.data;
                })
                .catch(function error(response) {
                    throw response.data;
                });
        }

    }
})();