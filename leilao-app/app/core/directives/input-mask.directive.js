(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .directive('inputMask', function () {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, element, attrs, ngModel) {
                    var type = attrs.inputMask;
                    var config = getMaskConfig(type);

                    if (!config) {
                        return;
                    }

                    function applyMask(rawValue) {
                        var digits = String(rawValue || '').replace(/\D/g, '').slice(0, config.maxDigits);
                        return config.formatter(digits);
                    }

                    function syncMaskedValue(rawValue) {
                        var masked = applyMask(rawValue);

                        if (element.val() !== masked) {
                            element.val(masked);
                        }

                        if (ngModel.$viewValue !== masked) {
                            ngModel.$setViewValue(masked);
                        }
                    }

                    element.on('input', function () {
                        scope.$evalAsync(function () {
                            syncMaskedValue(element.val());
                        });
                    });

                    ngModel.$formatters.push(function (value) {
                        return config.formatter(String(value || '').replace(/\D/g, '').slice(0, config.maxDigits));
                    });

                    ngModel.$render = function () {
                        element.val(ngModel.$viewValue || '');
                    };
                }
            };
        });

    function getMaskConfig(type) {
        var configs = {
            cnpj: {
                maxDigits: 14,
                formatter: formatCnpj
            },
            telefone: {
                maxDigits: 11,
                formatter: formatTelefone
            }
        };

        return configs[type];
    }

    function formatCnpj(digits) {
        if (!digits) {
            return '';
        }

        if (digits.length <= 2) {
            return digits;
        }

        if (digits.length <= 5) {
            return digits.slice(0, 2) + '.' + digits.slice(2);
        }

        if (digits.length <= 8) {
            return digits.slice(0, 2) + '.' + digits.slice(2, 5) + '.' + digits.slice(5);
        }

        if (digits.length <= 12) {
            return digits.slice(0, 2) + '.' + digits.slice(2, 5) + '.' + digits.slice(5, 8) + '/' + digits.slice(8);
        }

        return digits.slice(0, 2) + '.' + digits.slice(2, 5) + '.' + digits.slice(5, 8) + '/' + digits.slice(8, 12) + '-' + digits.slice(12);
    }

    function formatTelefone(digits) {
        if (!digits) {
            return '';
        }

        if (digits.length <= 2) {
            return '(' + digits;
        }

        if (digits.length <= 6) {
            return '(' + digits.slice(0, 2) + ') ' + digits.slice(2);
        }

        if (digits.length <= 10) {
            return '(' + digits.slice(0, 2) + ') ' + digits.slice(2, 6) + '-' + digits.slice(6);
        }

        return '(' + digits.slice(0, 2) + ') ' + digits.slice(2, 7) + '-' + digits.slice(7);
    }
})();
