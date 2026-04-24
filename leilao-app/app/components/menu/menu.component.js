(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('MenuController', function ($location) {
            var vm = this;

            vm.isActive = function (path) {
                return $location.path() === path;
            };
        });
})();

