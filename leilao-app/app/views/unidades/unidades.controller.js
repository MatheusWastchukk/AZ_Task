(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('UnidadesController', function ($location, $scope, ErrorMessageService, UnidadeService) {
            var vm = this;

            vm.unidades = [];
            vm.modalOpen = false;
            vm.form = {};
            vm.loading = true;
            vm.error = null;
            vm.isModalContextValid = isModalContextValid;

            vm.load = function () {
                vm.loading = true;
                vm.error = null;
                UnidadeService.list()
                    .then(function (response) {
                        vm.unidades = response.data;
                    })
                    .catch(function (error) {
                        vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível carregar as unidades.');
                    })
                    .finally(function () {
                        vm.loading = false;
                    });
            };

            vm.openCreate = function () {
                if (!isModalContextValid()) {
                    return;
                }

                vm.form = {};
                vm.modalOpen = true;
            };

            vm.openEdit = function (unidade) {
                if (!isModalContextValid()) {
                    return;
                }

                vm.form = angular.copy(unidade);
                vm.modalOpen = true;
            };

            vm.closeModal = function () {
                vm.modalOpen = false;
                vm.form = {};
            };

            vm.save = function (form) {
                if (form.$invalid) {
                    form.$setSubmitted();
                    return;
                }

                var action = vm.form.id
                    ? UnidadeService.update(vm.form.id, { nome: vm.form.nome })
                    : UnidadeService.create({ nome: vm.form.nome });

                action.then(function () {
                    vm.closeModal();
                    vm.load();
                }).catch(function (error) {
                    vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível salvar a unidade.');
                });
            };

            vm.remove = function (unidade) {
                if (!window.confirm('Deseja excluir a unidade "' + unidade.nome + '"?')) {
                    return;
                }

                UnidadeService.remove(unidade.id)
                    .then(vm.load)
                    .catch(function (error) {
                        vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível excluir a unidade.');
                    });
            };

            $scope.$on('$routeChangeStart', function () {
                vm.closeModal();
            });

            function isModalContextValid() {
                return $location.path() === '/unidades';
            }

            vm.load();
        });
})();
