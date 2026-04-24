package br.com.az.leilao.business;

import br.com.az.leilao.dto.LoteRequest;
import br.com.az.leilao.dto.LoteResponse;
import br.com.az.leilao.entity.Lote;
import br.com.az.leilao.exception.NotFoundException;
import br.com.az.leilao.repository.LoteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoteBO {

    private final LoteRepository loteRepository;
    private final LeilaoBO leilaoBO;

    public LoteBO(LoteRepository loteRepository, LeilaoBO leilaoBO) {
        this.loteRepository = loteRepository;
        this.leilaoBO = leilaoBO;
    }

    @Transactional(readOnly = true)
    public List<LoteResponse> buscarTodos() {
        return loteRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LoteResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public LoteResponse criar(LoteRequest request) {
        Lote lote = new Lote();
        mapRequest(request, lote);
        LocalDateTime now = LocalDateTime.now();
        lote.setCreatedAt(now);
        lote.setUpdatedAt(now);
        return toResponse(loteRepository.save(lote));
    }

    @Transactional
    public LoteResponse atualizar(Integer id, LoteRequest request) {
        Lote lote = buscarEntidade(id);
        mapRequest(request, lote);
        lote.setUpdatedAt(LocalDateTime.now());
        return toResponse(loteRepository.save(lote));
    }

    @Transactional
    public void remover(Integer id) {
        loteRepository.delete(buscarEntidade(id));
    }

    public Lote buscarEntidade(Integer id) {
        return loteRepository.findById(id).orElseThrow(() -> new NotFoundException("Lote nao encontrado."));
    }

    private void mapRequest(LoteRequest request, Lote lote) {
        lote.setNumeroLote(request.getNumeroLote());
        lote.setDescricao(request.getDescricao());
        lote.setQuantidade(request.getQuantidade());
        lote.setValorInicial(request.getValorInicial());
        lote.setUnidade(request.getUnidade());
        lote.setLeilao(leilaoBO.buscarEntidade(request.getLeilaoId()));
    }

    private LoteResponse toResponse(Lote lote) {
        LoteResponse response = new LoteResponse();
        response.setId(lote.getId());
        response.setNumeroLote(lote.getNumeroLote());
        response.setDescricao(lote.getDescricao());
        response.setQuantidade(lote.getQuantidade());
        response.setValorInicial(lote.getValorInicial());
        response.setUnidade(lote.getUnidade());
        response.setLeilaoId(lote.getLeilao().getId());
        return response;
    }
}
