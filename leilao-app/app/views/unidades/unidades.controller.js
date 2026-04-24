(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('UnidadesController', function (UnidadeService) {
            var vm = this;

            vm.unidades = [];
            vm.modalOpen = false;
            vm.form = {};
            vm.loading = true;
            vm.error = null;

            vm.load = function () {
                vm.loading = true;
                vm.error = null;
                UnidadeService.list()
                    .then(function (response) {
                        vm.unidades = response.data;
                    })
                    .catch(function () {
                        vm.error = 'Nao foi possivel carregar as unidades.';
                    })
                    .finally(function () {
                        vm.loading = false;
                    });
            };

            vm.openCreate = function () {
                vm.form = {};
                vm.modalOpen = true;
            };

            vm.openEdit = function (unidade) {
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
                }).catch(function () {
                    vm.error = 'Nao foi possivel salvar a unidade.';
                });
            };

            vm.remove = function (unidade) {
                if (!window.confirm('Deseja excluir a unidade "' + unidade.nome + '"?')) {
                    return;
                }

                UnidadeService.remove(unidade.id)
                    .then(vm.load)
                    .catch(function () {
                        vm.error = 'Nao foi possivel excluir a unidade.';
                    });
            };

            vm.load();
        });
})();

