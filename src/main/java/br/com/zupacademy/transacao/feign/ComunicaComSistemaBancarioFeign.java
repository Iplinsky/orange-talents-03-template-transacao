package br.com.zupacademy.transacao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.transacao.feign.request.SistemaBancarioFeignRequest;
import feign.Headers;

@FeignClient(name = "sistemaBancarioFeign", url = "${url.sistema.bancario}")
public interface ComunicaComSistemaBancarioFeign {

	@PostMapping(produces = "application/json")
	public void realizarComunicacaoParaGerarTransacao(@RequestBody SistemaBancarioFeignRequest sistemaBancarioRequest);

	@DeleteMapping("/{id}")
	@Headers("Content-Type: application/json")
	public void realizarComunicacaoParaSuspenderOProcessoDeGerarTransacao(@PathVariable Long id);

}
