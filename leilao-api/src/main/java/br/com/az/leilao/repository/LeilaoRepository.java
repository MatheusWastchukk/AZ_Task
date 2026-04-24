package br.com.az.leilao.repository;

import br.com.az.leilao.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeilaoRepository extends JpaRepository<Leilao, Integer> {
}

