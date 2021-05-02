package br.com.zupacademy.transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.transacao.models.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
