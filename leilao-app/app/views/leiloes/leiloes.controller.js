(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('LeiloesController', function (ErrorMessageService, LeilaoService) {
            var vm = this;

            vm.leiloes = [];
            vm.loading = true;
            vm.error = null;

            vm.load = function () {
                vm.loading = true;
                vm.error = null;
                LeilaoService.list()
                    .then(function (response) {
                        vm.leiloes = response.data;
                    })
                    .catch(function (error) {
                        vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível carregar os leilões.');
                    })
                    .finally(function () {
                        vm.loading = false;
                    });
            };

            vm.load();
        });
})();
