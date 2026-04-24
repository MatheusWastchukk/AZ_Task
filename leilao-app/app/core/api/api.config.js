(function () {
    'use strict';

    angular.module('azLeilaoApp')
        // Resolve a API no mesmo host da pagina, variando apenas a porta.
        .constant('API_URL', window.location.protocol + '//' + window.location.hostname + ':8081');
})();
