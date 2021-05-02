package br.com.zupacademy.transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.transacao.models.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
