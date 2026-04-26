package br.com.az.leilao.business;

import br.com.az.leilao.dto.LeilaoListResponse;
import br.com.az.leilao.dto.LeilaoRequest;
import br.com.az.leilao.dto.LeilaoResponse;
import br.com.az.leilao.entity.Leilao;
import br.com.az.leilao.entity.Lote;
import br.com.az.leilao.exception.NotFoundException;
import br.com.az.leilao.repository.LeilaoRepository;
import br.com.az.leilao.repository.LoteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeilaoBO {

    private final LeilaoRepository leilaoRepository;
    private final LoteRepository loteRepository;
    private final EmpresaBO empresaBO;

    public LeilaoBO(LeilaoRepository leilaoRepository, LoteRepository loteRepository, EmpresaBO empresaBO) {
        this.leilaoRepository = leilaoRepository;
        this.loteRepository = loteRepository;
        this.empresaBO = empresaBO;
    }

    @Transactional(readOnly = true)
    public List<LeilaoListResponse> buscarTodos() {
        List<Lote> lotes = loteRepository.findAll();
        return leilaoRepository.findAll().stream()
                .map(leilao -> toListResponse(leilao, lotes))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeilaoResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public LeilaoResponse criar(LeilaoRequest request) {
        Leilao leilao = new Leilao();
        mapRequest(request, leilao);
        LocalDateTime now = LocalDateTime.now();
        leilao.setCreatedAt(now);
        leilao.setUpdatedAt(now);
        return toResponse(leilaoRepository.save(leilao));
    }

    @Transactional
    public LeilaoResponse atualizar(Integer id, LeilaoRequest request) {
        Leilao leilao = buscarEntidade(id);
        mapRequest(request, leilao);
        leilao.setUpdatedAt(LocalDateTime.now());
        return toResponse(leilaoRepository.save(leilao));
    }

    @Transactional
    public void remover(Integer id) {
        leilaoRepository.delete(buscarEntidade(id));
    }

    public Leilao buscarEntidade(Integer id) {
        return leilaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Leilao nao encontrado."));
    }

    private void mapRequest(LeilaoRequest request, Leilao leilao) {
        leilao.setCodigo(request.getCodigo());
        leilao.setDescricao(request.getDescricao());
        leilao.setVendedor(empresaBO.buscarEntidade(request.getVendedorId()));
        leilao.setInicioPrevisto(request.getInicioPrevisto());
    }

    private LeilaoResponse toResponse(Leilao leilao) {
        LeilaoResponse response = new LeilaoResponse();
        response.setId(leilao.getId());
        response.setCodigo(leilao.getCodigo());
        response.setDescricao(leilao.getDescricao());
        response.setVendedorId(leilao.getVendedor().getId());
        response.setRazaoSocialVendedor(leilao.getVendedor().getRazaoSocial());
        response.setInicioPrevisto(leilao.getInicioPrevisto());
        return response;
    }

    private LeilaoListResponse toListResponse(Leilao leilao, List<Lote> lotes) {
        LeilaoListResponse response = new LeilaoListResponse();
        response.setId(leilao.getId());
        response.setRazaoSocialVendedor(leilao.getVendedor().getRazaoSocial());
        response.setInicioPrevisto(leilao.getInicioPrevisto());
        // O enunciado pede o total consolidado do leilao como soma de quantidade * valorInicial de cada lote.
        response.setTotal(
                lotes.stream()
                        .filter(lote -> lote.getLeilao().getId().equals(leilao.getId()))
                        .map(this::calcularTotalLote)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        return response;
    }

    private BigDecimal calcularTotalLote(Lote lote) {
        if (lote.getQuantidade() == null || lote.getValorInicial() == null) {
            return BigDecimal.ZERO;
        }

        return lote.getQuantidade().multiply(lote.getValorInicial());
    }
}
