(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .service('UnidadeService', function ($http, API_URL) {
            this.list = function () {
                return $http.get(API_URL + '/unidades');
            };

            this.create = function (payload) {
                return $http.post(API_URL + '/unidades', payload);
            };

            this.update = function (id, payload) {
                return $http.put(API_URL + '/unidades/' + id, payload);
            };

            this.remove = function (id) {
                return $http.delete(API_URL + '/unidades/' + id);
            };
        });
})();

