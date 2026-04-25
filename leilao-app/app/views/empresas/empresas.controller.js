(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('EmpresasController', function ($location, EmpresaService) {
            var vm = this;

            vm.empresas = [];
            vm.loading = true;
            vm.error = null;

            vm.load = function () {
                vm.loading = true;
                vm.error = null;
                EmpresaService.list()
                    .then(function (response) {
                        vm.empresas = response.data;
                    })
                    .catch(function () {
                        vm.error = 'Não foi possível carregar as empresas.';
                    })
                    .finally(function () {
                        vm.loading = false;
                    });
            };

            vm.goToCreate = function () {
                $location.path('/empresa');
            };

            vm.goToEdit = function (id) {
                $location.path('/empresa/' + id);
            };

            vm.remove = function (empresa) {
                if (!window.confirm('Deseja excluir a empresa "' + empresa.razaoSocial + '"?')) {
                    return;
                }

                EmpresaService.remove(empresa.id)
                    .then(vm.load)
                    .catch(function () {
                        vm.error = 'Não foi possível excluir a empresa.';
                    });
            };

            vm.load();
        });
})();
