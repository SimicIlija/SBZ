(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('reportController', reportController);

    reportController.$inject = ['toastr', 'ReportService'];

    function reportController(toastr, ReportService) {
        var reportVm = this;
        reportVm.hronicni = [];
        reportVm.zavisni = [];
        reportVm.imunitet = [];
        reportVm.showHr = showHr;
        reportVm.showZa = showZa;
        reportVm.showIm = showIm;
        reportVm.getHr = getHr;
        reportVm.getZa = getZa;
        reportVm.getIm = getIm;

        function getHr() {
            ReportService.getHronicne()
                .then(function (response) {
                    reportVm.hronicni = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function getZa() {
            ReportService.getZavisne()
                .then(function (response) {
                    reportVm.zavisni = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function getIm() {
            ReportService.getImunitet()
                .then(function (response) {
                    reportVm.imunitet = response;
                })
                .catch(function (response) {
                    toastr.error(response.message, 'Greska');
                });
        }

        function showHr(){
            return reportVm.hronicni.length > 0;
        }

        function showZa(){
            return reportVm.zavisni.length > 0;
        }

        function showIm(){
            return reportVm.imunitet.length > 0;
        }
    }

})();
