(function () {
    'use strict';

    angular.module('azLeilaoApp')
        .controller('HomeController', function () {
            var vm = this;

            vm.links = [
                {
                    titulo: 'Unidades',
                    descricao: 'Cadastre, edite e exclua unidades em uma tela com modal e integração completa com a API.',
                    rota: '#/unidades',
                    acao: 'Abrir unidades'
                },
                {
                    titulo: 'Empresas',
                    descricao: 'Consulte empresas, acesse o formulário de cadastro e realize edição ou exclusão.',
                    rota: '#/empresas',
                    acao: 'Abrir empresas'
                },
                {
                    titulo: 'Leilões',
                    descricao: 'Visualize o vendedor, o início previsto e o total consolidado de cada leilão.',
                    rota: '#/leiloes',
                    acao: 'Abrir leilões'
                }
            ];
        });
})();
