package br.com.zupacademy.transacao.transacao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.transacao.feign.ComunicaComSistemaBancarioFeign;
import br.com.zupacademy.transacao.feign.SistemaBancarioFeignRequest;
import br.com.zupacademy.transacao.handler.exception.ApiErroException;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	final ComunicaComSistemaBancarioFeign comunicaComSistemaBancarioFeign;
	final TransacaoRepository transacaoRepository;

	public TransacaoController(ComunicaComSistemaBancarioFeign comunicaComSistemaBancarioFeign,
			TransacaoRepository transacaoRepository) {
		this.comunicaComSistemaBancarioFeign = comunicaComSistemaBancarioFeign;
		this.transacaoRepository = transacaoRepository;
	}

	@PostMapping
	public void gerarTransacao(@RequestBody @Valid TransacaoRequestDto requestDto) {
		try {
			comunicaComSistemaBancarioFeign.realizarComunicacaoParaGerarTransacao(new SistemaBancarioFeignRequest(requestDto.getId(), requestDto.getEmail()));
		} catch (ApiErroException ex) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Houve uma falha na tentativa de gerar transação.");
		}
	}

	@DeleteMapping("/{id}")
	public void pararTransacao(@PathVariable String id) {
		try {
			comunicaComSistemaBancarioFeign.realizarComunicacaoParaSuspenderOProcessoDeGerarTransacao(id);
		} catch (ApiErroException ex) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Houve uma falha na tentativa de suspender a transação.");
		}
	}

	@GetMapping("/{id}")
	public List<TransacaoResponseDto> ConsultarTransacao(@PathVariable String id,
			@PageableDefault(sort = "efetivadaEm", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {

		List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoId(id, pageable);

		if (transacoes.isEmpty())
			throw new ApiErroException(HttpStatus.NOT_FOUND, "Nenhuma transação foi localizada para este cartão.");

		return TransacaoResponseDto.toModel(transacoes);
	}

}
