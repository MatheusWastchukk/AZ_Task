package br.com.az.leilao.business;

import br.com.az.leilao.dto.CepResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class CepBO {

    private final RestTemplate restTemplate;

    @Value("${viacep.base-url:https://viacep.com.br/ws}")
    private String viaCepBaseUrl;

    public CepBO(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Transactional(readOnly = true)
    public CepResponse buscarPorCep(String cep) {
        String cepNormalizado = normalizarCep(cep);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<CepResponse> response;
        String url;

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        url = UriComponentsBuilder.fromHttpUrl(viaCepBaseUrl)
                .pathSegment(cepNormalizado)
                .pathSegment("json")
                .toUriString();

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Void>(headers), CepResponse.class);
        } catch (HttpStatusCodeException exception) {
            if (exception.getStatusCode().value() == 404) {
                throw new ResponseStatusException(NOT_FOUND, "CEP nao encontrado.");
            }

            throw new ResponseStatusException(
                    BAD_GATEWAY,
                    "Falha ao consultar a API do ViaCEP. Status externo: " + exception.getStatusCode().value()
            );
        } catch (RestClientException exception) {
            throw new ResponseStatusException(BAD_GATEWAY, "Falha ao comunicar com a API do ViaCEP.");
        }

        if (!response.hasBody() || response.getBody() == null) {
            throw new ResponseStatusException(BAD_GATEWAY, "Resposta vazia da API de CEP.");
        }

        if (Boolean.TRUE.equals(response.getBody().getErro())) {
            throw new ResponseStatusException(NOT_FOUND, "CEP nao encontrado.");
        }

        return response.getBody();
    }

    private String normalizarCep(String cep) {
        String digits = (cep == null ? "" : cep.replaceAll("\\D", ""));

        if (digits.length() != 8) {
            throw new ResponseStatusException(BAD_REQUEST, "CEP deve conter 8 digitos.");
        }

        return digits;
    }
}
