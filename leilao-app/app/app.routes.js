(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .config(function ($routeProvider, $locationProvider) {
            $locationProvider.hashPrefix('');

            $routeProvider
                .when('/unidades', {
                    templateUrl: 'app/views/unidades/unidades.html',
                    controller: 'UnidadesController',
                    controllerAs: 'vm'
                })
                .when('/empresas', {
                    templateUrl: 'app/views/empresas/empresas.html',
                    controller: 'EmpresasController',
                    controllerAs: 'vm'
                })
                .when('/empresa', {
                    templateUrl: 'app/views/empresa-form/empresa-form.html',
                    controller: 'EmpresaFormController',
                    controllerAs: 'vm'
                })
                .when('/empresa/:id', {
                    templateUrl: 'app/views/empresa-form/empresa-form.html',
                    controller: 'EmpresaFormController',
                    controllerAs: 'vm'
                })
                .when('/leiloes', {
                    templateUrl: 'app/views/leiloes/leiloes.html',
                    controller: 'LeiloesController',
                    controllerAs: 'vm'
                })
                .otherwise({
                    redirectTo: '/unidades'
                });
        });
})();
