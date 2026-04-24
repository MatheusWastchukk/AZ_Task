(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('EmpresaFormController', function ($location, $routeParams, EmpresaService) {
            var vm = this;
            var empresaId = $routeParams.id;

            vm.form = {
                razaoSocial: '',
                cnpj: '',
                logradouro: '',
                municipio: '',
                numero: '',
                complemento: '',
                bairro: '',
                cep: '',
                telefone: '',
                email: '',
                site: '',
                usuario: '',
                senha: ''
            };
            vm.loading = false;
            vm.error = null;
            vm.isEdit = !!empresaId;

            vm.load = function () {
                if (!empresaId) {
                    return;
                }

                vm.loading = true;
                EmpresaService.getById(empresaId)
                    .then(function (response) {
                        vm.form = response.data;
                    })
                    .catch(function () {
                        vm.error = 'Nao foi possivel carregar a empresa.';
                    })
                    .finally(function () {
                        vm.loading = false;
                    });
            };

            vm.save = function (form) {
                if (form.$invalid) {
                    form.$setSubmitted();
                    return;
                }

                vm.error = null;

                var action = vm.isEdit
                    ? EmpresaService.update(empresaId, vm.form)
                    : EmpresaService.create(vm.form);

                action.then(function () {
                    $location.path('/empresas');
                }).catch(function (error) {
                    vm.error = extractError(error) || 'Nao foi possivel salvar a empresa.';
                });
            };

            vm.cancel = function () {
                $location.path('/empresas');
            };

            function extractError(error) {
                if (!error || !error.data || !error.data.details || !error.data.details.length) {
                    return null;
                }

                return error.data.details.join(' | ');
            }

            vm.load();
        });
})();

