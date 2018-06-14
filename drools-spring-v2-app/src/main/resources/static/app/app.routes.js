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
        }).state('di', {
            url: "/di",
            data: {
                pageTitle: "Sastojak leka"
            },
            views: {
                'content@': {
                    templateUrl: "app/components/drugingredient/di.html",
                    controller: "DIController",
                    controllerAs: "diVm"
                }
            }
        }).state('symptoms', {
            url: "/symptoms",
            data: {
                pageTitle: "Simptom"
            },
            views: {
                'content@': {
                    templateUrl: "app/components/symptoms/symptoms.html",
                    controller: "symptomsController",
                    controllerAs: "symptomsVm"
                }
            }
        }).state('drug', {
            url: "/drug",
            data: {
                pageTitle: "Lek"
            },
            views: {
                'content@': {
                    templateUrl: "app/components/drug/drug.html",
                    controller: "drugController",
                    controllerAs: "drugVm"
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
