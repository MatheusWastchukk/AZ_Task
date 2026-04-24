package br.com.az.leilao.repository;

import br.com.az.leilao.entity.Comprador;
import br.com.az.leilao.entity.CompradorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<Comprador, CompradorId> {
}

