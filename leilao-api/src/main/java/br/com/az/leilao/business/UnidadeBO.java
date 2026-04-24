package br.com.az.leilao.business;

import br.com.az.leilao.dto.UnidadeRequest;
import br.com.az.leilao.entity.Unidade;
import br.com.az.leilao.exception.NotFoundException;
import br.com.az.leilao.repository.UnidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UnidadeBO {

    private final UnidadeRepository unidadeRepository;

    public UnidadeBO(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Transactional(readOnly = true)
    public List<Unidade> buscarTodos() {
        return unidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Unidade buscarPorId(Integer id) {
        return buscarEntidade(id);
    }

    @Transactional
    public Unidade criar(UnidadeRequest request) {
        Unidade unidade = new Unidade();
        unidade.setNome(request.getNome());
        LocalDateTime now = LocalDateTime.now();
        unidade.setCreatedAt(now);
        unidade.setUpdatedAt(now);
        return unidadeRepository.save(unidade);
    }

    @Transactional
    public Unidade atualizar(Integer id, UnidadeRequest request) {
        Unidade unidade = buscarEntidade(id);
        unidade.setNome(request.getNome());
        unidade.setUpdatedAt(LocalDateTime.now());
        return unidadeRepository.save(unidade);
    }

    @Transactional
    public void remover(Integer id) {
        unidadeRepository.delete(buscarEntidade(id));
    }

    public Unidade buscarEntidade(Integer id) {
        return unidadeRepository.findById(id).orElseThrow(() -> new NotFoundException("Unidade nao encontrada."));
    }
}

