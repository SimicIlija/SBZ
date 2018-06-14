(function () {
    'use strict';

    angular.module('cdss', [
        'ui.bootstrap',
        'ui.router',
        'ngStorage',
        'toastr'
    ]).constant(
        'CONFIG', {
            'SERVICE_URL': 'http://localhost:8080/'
        }
    ).config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/404");

        $stateProvider
            .state('home', {
                url: "/home",
                data: {
                    pageTitle: "Home"
                },
                views: {
                    'content@': {
                        templateUrl: "app/components/home/home.html",
                        controller: "HomeController",
                        controllerAs: "homeVm"
                    }
                }
            }).state('login', {
            url: "/login",
            data: {
                pageTitle: "Login"
            },
            views: {
                'content@': {
                    templateUrl: "app/components/login/login.html",
                    controller: "LoginController",
                    controllerAs: "loginVm"
                }
            }
        }).state('404', {
            url: "/404",
            data: {
                pageTitle: "Error"
            },
            views: {
                'content@': {
                    templateUrl: "app/components/errorpage/errorpage.html",
                    controller: "ErrorController",
                    controllerAs: "errorVm"
                }
            }
        });

    });
})();
