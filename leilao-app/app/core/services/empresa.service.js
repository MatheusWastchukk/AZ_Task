(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .service('EmpresaService', function ($http, API_URL) {
            this.list = function () {
                return $http.get(API_URL + '/empresas');
            };

            this.getById = function (id) {
                return $http.get(API_URL + '/empresas/' + id);
            };

            this.create = function (payload) {
                return $http.post(API_URL + '/empresas', payload);
            };

            this.update = function (id, payload) {
                return $http.put(API_URL + '/empresas/' + id, payload);
            };

            this.remove = function (id) {
                return $http.delete(API_URL + '/empresas/' + id);
            };
        });
})();

