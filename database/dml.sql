INSERT INTO unidade (id, nome, created_at, updated_at) VALUES
    (1, 'Kg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Tonelada', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Litro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Metro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Caixa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Pacote', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'Unidade', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'Lote', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 'Peca', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 'Saca', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT setval('seq_unidade', 10, true);

INSERT INTO empresa (
    id, razao_social, cnpj, logradouro, municipio, numero, complemento, bairro, cep,
    telefone, email, site, usuario, senha, created_at, updated_at
) VALUES
    (1, 'Armazens do Norte LTDA', '10.000.000/0001-00', 'Rua A', 'Sao Paulo', '100', 'Sala 1', 'Centro', '01000-000', '(11) 3000-0001', 'contato1@empresa.com', 'https://empresa1.com', 'empresa01', 'senhaSegura01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Comercial Vale Verde SA', '10.000.000/0002-00', 'Rua B', 'Campinas', '200', 'Galpao 2', 'Jardins', '13000-000', '(19) 3000-0002', 'contato2@empresa.com', 'https://empresa2.com', 'empresa02', 'senhaSegura02', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Industria Boa Safra LTDA', '10.000.000/0003-00', 'Rua C', 'Ribeirao Preto', '300', 'Bloco B', 'Campos', '14000-000', '(16) 3000-0003', 'contato3@empresa.com', 'https://empresa3.com', 'empresa03', 'senhaSegura03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Transportes Horizonte LTDA', '10.000.000/0004-00', 'Rua D', 'Santos', '400', 'Conjunto 4', 'Porto', '11000-000', '(13) 3000-0004', 'contato4@empresa.com', 'https://empresa4.com', 'empresa04', 'senhaSegura04', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Agro Minas Exportacao', '10.000.000/0005-00', 'Rua E', 'Uberlandia', '500', 'Casa', 'Industrial', '38000-000', '(34) 3000-0005', 'contato5@empresa.com', 'https://empresa5.com', 'empresa05', 'senhaSegura05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Logistica Costa Azul', '10.000.000/0006-00', 'Rua F', 'Vitoria', '600', 'Andar 6', 'Praia', '29000-000', '(27) 3000-0006', 'contato6@empresa.com', 'https://empresa6.com', 'empresa06', 'senhaSegura06', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'Materiais Brasil Central', '10.000.000/0007-00', 'Rua G', 'Goiania', '700', 'Sala 7', 'Centro', '74000-000', '(62) 3000-0007', 'contato7@empresa.com', 'https://empresa7.com', 'empresa07', 'senhaSegura07', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'Metalurgica Pioneira', '10.000.000/0008-00', 'Rua H', 'Curitiba', '800', 'Fundos', 'CIC', '81000-000', '(41) 3000-0008', 'contato8@empresa.com', 'https://empresa8.com', 'empresa08', 'senhaSegura08', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 'Construtora Serra Alta', '10.000.000/0009-00', 'Rua I', 'Belo Horizonte', '900', 'Loja', 'Savassi', '30000-000', '(31) 3000-0009', 'contato9@empresa.com', 'https://empresa9.com', 'empresa09', 'senhaSegura09', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 'Recicla Sul Cooperativa', '10.000.000/0010-00', 'Rua J', 'Porto Alegre', '1000', 'Bloco 10', 'Navegantes', '90000-000', '(51) 3000-0010', 'contato10@empresa.com', 'https://empresa10.com', 'empresa10', 'senhaSegura10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT setval('seq_empresa', 10, true);

INSERT INTO leilao (id, codigo, descricao, vendedor, inicio_previsto, created_at, updated_at) VALUES
    (1, 1001, 'Leilao de graos', 1, CURRENT_TIMESTAMP + INTERVAL '1 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 1002, 'Leilao de fertilizantes', 2, CURRENT_TIMESTAMP + INTERVAL '2 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 1003, 'Leilao de metais', 3, CURRENT_TIMESTAMP + INTERVAL '3 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 1004, 'Leilao de maquinas', 4, CURRENT_TIMESTAMP + INTERVAL '4 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 1005, 'Leilao de sementes', 5, CURRENT_TIMESTAMP + INTERVAL '5 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 1006, 'Leilao de combustiveis', 6, CURRENT_TIMESTAMP + INTERVAL '6 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 1007, 'Leilao de materiais', 7, CURRENT_TIMESTAMP + INTERVAL '7 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 1008, 'Leilao de sucata', 8, CURRENT_TIMESTAMP + INTERVAL '8 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 1009, 'Leilao de equipamentos', 9, CURRENT_TIMESTAMP + INTERVAL '9 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 1010, 'Leilao de reciclaveis', 10, CURRENT_TIMESTAMP + INTERVAL '10 day', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT setval('seq_leilao', 10, true);

INSERT INTO lote (
    id, numero_lote, descricao, quantidade, valor_inicial, unidade, leilao, created_at, updated_at
) VALUES
    (1, 1, 'Milho amarelo', 1000, 12.50, 'Kg', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 1, 'Adubo NPK', 250, 220.00, 'Saca', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 1, 'Chapas de aco', 90, 850.00, 'Peca', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 1, 'Trator usado', 3, 95000.00, 'Unidade', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 1, 'Sementes de soja', 400, 180.00, 'Caixa', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 1, 'Diesel S10', 12000, 5.70, 'Litro', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 1, 'Tubos de PVC', 1500, 18.20, 'Metro', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 1, 'Ligas metalicas', 48, 1300.00, 'Peca', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 1, 'Geradores', 12, 24000.00, 'Unidade', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 1, 'Papel prensado', 70, 410.00, 'Pacote', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT setval('seq_lote', 10, true);

INSERT INTO comprador (empresa, leilao) VALUES
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 4),
    (6, 5),
    (7, 6),
    (8, 7),
    (9, 8),
    (10, 9),
    (1, 10);
