(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .factory('ErrorMessageService', function () {
            return {
                fromHttp: fromHttp
            };

            function fromHttp(error, fallbackMessage) {
                var details;

                if (error && error.status === 0) {
                    return 'Não foi possível conectar ao servidor. Verifique se a API está disponível e tente novamente.';
                }

                details = extractDetails(error);
                if (details) {
                    return details;
                }

                return fallbackMessage || 'Não foi possível concluir a operação no momento.';
            }

            function extractDetails(error) {
                if (!error || !error.data) {
                    return null;
                }

                if (angular.isArray(error.data.details) && error.data.details.length) {
                    return 'Revise os campos informados: ' + error.data.details.join(' | ');
                }

                if (error.data.message) {
                    return error.data.message;
                }

                return null;
            }
        });
})();
