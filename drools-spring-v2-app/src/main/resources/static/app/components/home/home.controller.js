(function () {
    'use strict';

    angular
        .module('cdss')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['toastr'];

    function HomeController(toastr) {
        var homeVm = this;
        homeVm.init = init;

        init();

        function init() {
            toastr.success('Hello world!', 'Toastr fun!');
        }
    }

})();
