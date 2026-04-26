(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('HomeController', function () {
            var vm = this;

            vm.links = [
                {
                    titulo: 'Unidades',
                    descricao: 'Consulte, cadastre, edite e exclua unidades.',
                    rota: '#/unidades',
                    acao: 'Abrir unidades'
                },
                {
                    titulo: 'Empresas',
                    descricao: 'Consulte, cadastre, edite e exclua empresas.',
                    rota: '#/empresas',
                    acao: 'Abrir empresas'
                },
                {
                    titulo: 'Leilões',
                    descricao: 'Consulte leilões.',
                    rota: '#/leiloes',
                    acao: 'Abrir leilões'
                }
            ];
        });
})();
