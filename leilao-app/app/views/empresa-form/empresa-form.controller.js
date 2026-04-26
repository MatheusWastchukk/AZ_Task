(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('EmpresaFormController', function ($location, $routeParams, EmpresaService, ErrorMessageService) {
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
            vm.patterns = {
                cnpj: /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/,
                telefone: /^\(\d{2}\)\s\d{4,5}-\d{4}$/,
                cep: /^\d{5}-\d{3}$/,
                numero: /^\d+$/
            };

            vm.applyCepMask = function () {
                var digits = onlyDigits(vm.form.cep).slice(0, 8);
                vm.form.cep = digits.length > 5 ? digits.slice(0, 5) + '-' + digits.slice(5, 8) : digits;
            };

            vm.applyNumberMask = function () {
                vm.form.numero = onlyDigits(vm.form.numero).slice(0, 10);
            };

            vm.load = function () {
                if (!empresaId) {
                    return;
                }

                vm.loading = true;
                EmpresaService.getById(empresaId)
                    .then(function (response) {
                        vm.form = normalizeLoadedForm(response.data);
                    })
                    .catch(function (error) {
                        vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível carregar a empresa.');
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
                    ? EmpresaService.update(empresaId, buildPayload())
                    : EmpresaService.create(buildPayload());

                action.then(function () {
                    $location.path('/empresas');
                }).catch(function (error) {
                    vm.error = ErrorMessageService.fromHttp(error, 'Não foi possível salvar a empresa.');
                });
            };

            vm.cancel = function () {
                $location.path('/empresas');
            };

            function buildPayload() {
                return {
                    razaoSocial: vm.form.razaoSocial,
                    cnpj: vm.form.cnpj,
                    logradouro: vm.form.logradouro,
                    municipio: vm.form.municipio,
                    numero: vm.form.numero,
                    complemento: vm.form.complemento,
                    bairro: vm.form.bairro,
                    cep: vm.form.cep,
                    telefone: vm.form.telefone,
                    email: vm.form.email,
                    site: vm.form.site,
                    usuario: vm.form.usuario,
                    senha: vm.form.senha
                };
            }

            function normalizeLoadedForm(data) {
                var form = angular.extend({}, vm.form, data);

                if (form.cep) {
                    form.cep = onlyDigits(form.cep).slice(0, 8);
                    form.cep = form.cep.length > 5 ? form.cep.slice(0, 5) + '-' + form.cep.slice(5, 8) : form.cep;
                }

                if (form.numero) {
                    form.numero = onlyDigits(form.numero).slice(0, 10);
                }

                return form;
            }

            function onlyDigits(value) {
                return (value || '').replace(/\D/g, '');
            }

            vm.load();
        });
})();
