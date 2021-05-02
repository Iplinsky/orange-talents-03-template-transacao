package br.com.zupacademy.transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.transacao.models.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
