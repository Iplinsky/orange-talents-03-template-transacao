package br.com.zupacademy.transacao.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.transacao.feign.ComunicaComSistemaBancarioFeign;
import br.com.zupacademy.transacao.feign.request.SistemaBancarioFeignRequest;
import br.com.zupacademy.transacao.models.request.CartaoRequestDto;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private ComunicaComSistemaBancarioFeign comunicaComSistemaBancarioFeign;

	public TransacaoController(ComunicaComSistemaBancarioFeign comunicaComSistemaBancarioFeign) {
		this.comunicaComSistemaBancarioFeign = comunicaComSistemaBancarioFeign;
	}

	@PostMapping
	public void gerarTransacao(@RequestBody @Valid CartaoRequestDto requestDto) {
		comunicaComSistemaBancarioFeign.realizarComunicacaoParaGerarTransacao(new SistemaBancarioFeignRequest(requestDto.getId(), requestDto.getEmail()));
	}

	@DeleteMapping("/{id}")
	public void pararTransacao(@PathVariable("id") Long id) {
		comunicaComSistemaBancarioFeign.realizarComunicacaoParaSuspenderOProcessoDeGerarTransacao(id);
	}

}
