package br.com.az.leilao.business;

import br.com.az.leilao.dto.CompradorRequest;
import br.com.az.leilao.dto.CompradorResponse;
import br.com.az.leilao.entity.Comprador;
import br.com.az.leilao.entity.CompradorId;
import br.com.az.leilao.exception.NotFoundException;
import br.com.az.leilao.repository.CompradorRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompradorBO {

    private final CompradorRepository compradorRepository;
    private final EmpresaBO empresaBO;
    private final LeilaoBO leilaoBO;

    public CompradorBO(CompradorRepository compradorRepository, EmpresaBO empresaBO, LeilaoBO leilaoBO) {
        this.compradorRepository = compradorRepository;
        this.empresaBO = empresaBO;
        this.leilaoBO = leilaoBO;
    }

    @Transactional(readOnly = true)
    public List<CompradorResponse> buscarTodos() {
        return compradorRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompradorResponse buscarPorId(Integer empresaId, Integer leilaoId) {
        return toResponse(buscarEntidade(new CompradorId(empresaId, leilaoId)));
    }

    @Transactional
    public CompradorResponse criar(CompradorRequest request) {
        Comprador comprador = new Comprador();
        comprador.setId(new CompradorId(request.getEmpresaId(), request.getLeilaoId()));
        comprador.setEmpresa(empresaBO.buscarEntidade(request.getEmpresaId()));
        comprador.setLeilao(leilaoBO.buscarEntidade(request.getLeilaoId()));
        return toResponse(compradorRepository.save(comprador));
    }

    @Transactional
    public CompradorResponse atualizar(Integer empresaId, Integer leilaoId, CompradorRequest request) {
        buscarEntidade(new CompradorId(empresaId, leilaoId));
        // Como a chave primaria e composta, a forma mais simples de "editar" a relacao e recriar a associacao.
        compradorRepository.deleteById(new CompradorId(empresaId, leilaoId));

        Comprador comprador = new Comprador();
        comprador.setId(new CompradorId(request.getEmpresaId(), request.getLeilaoId()));
        comprador.setEmpresa(empresaBO.buscarEntidade(request.getEmpresaId()));
        comprador.setLeilao(leilaoBO.buscarEntidade(request.getLeilaoId()));
        return toResponse(compradorRepository.save(comprador));
    }

    @Transactional
    public void remover(Integer empresaId, Integer leilaoId) {
        compradorRepository.delete(buscarEntidade(new CompradorId(empresaId, leilaoId)));
    }

    private Comprador buscarEntidade(CompradorId id) {
        return compradorRepository.findById(id).orElseThrow(() -> new NotFoundException("Comprador nao encontrado."));
    }

    private CompradorResponse toResponse(Comprador comprador) {
        CompradorResponse response = new CompradorResponse();
        response.setEmpresaId(comprador.getEmpresa().getId());
        response.setRazaoSocialEmpresa(comprador.getEmpresa().getRazaoSocial());
        response.setLeilaoId(comprador.getLeilao().getId());
        response.setCodigoLeilao(comprador.getLeilao().getCodigo());
        return response;
    }
}
