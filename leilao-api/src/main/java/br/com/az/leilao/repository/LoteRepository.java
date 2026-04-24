package br.com.az.leilao.repository;

import br.com.az.leilao.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Integer> {
}

