package br.com.zupacademy.transacao.transacao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

	List<Transacao> findTop10ByCartaoId(String id, Pageable pageable);

}
