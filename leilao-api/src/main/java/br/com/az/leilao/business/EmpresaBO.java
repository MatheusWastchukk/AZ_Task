package br.com.az.leilao.business;

import br.com.az.leilao.dto.EmpresaRequest;
import br.com.az.leilao.dto.EmpresaResponse;
import br.com.az.leilao.entity.Empresa;
import br.com.az.leilao.exception.NotFoundException;
import br.com.az.leilao.repository.EmpresaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpresaBO {

    private final EmpresaRepository empresaRepository;

    public EmpresaBO(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponse> buscarTodos() {
        return empresaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpresaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidade(id));
    }

    @Transactional
    public EmpresaResponse criar(EmpresaRequest request) {
        Empresa empresa = new Empresa();
        mapRequest(request, empresa);
        LocalDateTime now = LocalDateTime.now();
        empresa.setCreatedAt(now);
        empresa.setUpdatedAt(now);
        return toResponse(empresaRepository.save(empresa));
    }

    @Transactional
    public EmpresaResponse atualizar(Integer id, EmpresaRequest request) {
        Empresa empresa = buscarEntidade(id);
        mapRequest(request, empresa);
        empresa.setUpdatedAt(LocalDateTime.now());
        return toResponse(empresaRepository.save(empresa));
    }

    @Transactional
    public void remover(Integer id) {
        Empresa empresa = buscarEntidade(id);
        empresaRepository.delete(empresa);
    }

    public Empresa buscarEntidade(Integer id) {
        return empresaRepository.findById(id).orElseThrow(() -> new NotFoundException("Empresa nao encontrada."));
    }

    private void mapRequest(EmpresaRequest request, Empresa empresa) {
        empresa.setRazaoSocial(request.getRazaoSocial());
        empresa.setCnpj(request.getCnpj());
        empresa.setLogradouro(request.getLogradouro());
        empresa.setMunicipio(request.getMunicipio());
        empresa.setNumero(request.getNumero());
        empresa.setComplemento(request.getComplemento());
        empresa.setBairro(request.getBairro());
        empresa.setCep(request.getCep());
        empresa.setTelefone(request.getTelefone());
        empresa.setEmail(request.getEmail());
        empresa.setSite(request.getSite());
        empresa.setUsuario(request.getUsuario());
        empresa.setSenha(request.getSenha());
    }

    private EmpresaResponse toResponse(Empresa empresa) {
        EmpresaResponse response = new EmpresaResponse();
        response.setId(empresa.getId());
        response.setRazaoSocial(empresa.getRazaoSocial());
        response.setCnpj(empresa.getCnpj());
        response.setLogradouro(empresa.getLogradouro());
        response.setMunicipio(empresa.getMunicipio());
        response.setNumero(empresa.getNumero());
        response.setComplemento(empresa.getComplemento());
        response.setBairro(empresa.getBairro());
        response.setCep(empresa.getCep());
        response.setTelefone(empresa.getTelefone());
        response.setEmail(empresa.getEmail());
        response.setSite(empresa.getSite());
        response.setUsuario(empresa.getUsuario());
        return response;
    }
}

