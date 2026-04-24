(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .service('LeilaoService', function ($http, API_URL) {
            this.list = function () {
                return $http.get(API_URL + '/leiloes');
            };
        });
})();

