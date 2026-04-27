(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .directive('cnpjValidator', function () {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function (scope, element, attrs, ngModel) {
                    ngModel.$validators.cnpjValidator = function (modelValue, viewValue) {
                        var value = viewValue || modelValue;

                        if (!value) {
                            return true;
                        }

                        return isValidCnpj(value);
                    };
                }
            };
        });

    function isValidCnpj(value) {
        var digits = String(value || '').replace(/\D/g, '');

        if (digits.length !== 14 || /^(\d)\1{13}$/.test(digits)) {
            return false;
        }

        return calculateDigit(digits, 12) === Number(digits.charAt(12))
            && calculateDigit(digits, 13) === Number(digits.charAt(13));
    }

    function calculateDigit(digits, length) {
        var weights = length === 12
            ? [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
            : [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];
        var sum = 0;
        var index;

        for (index = 0; index < length; index++) {
            sum += Number(digits.charAt(index)) * weights[index];
        }

        sum = sum % 11;
        return sum < 2 ? 0 : 11 - sum;
    }
})();
