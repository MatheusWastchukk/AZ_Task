(function () {
    'use strict';

    angular.module('azLeilaoApp', ['ngRoute'])
        .run(function ($rootScope) {
            $rootScope.appError = null;
        });
})();

