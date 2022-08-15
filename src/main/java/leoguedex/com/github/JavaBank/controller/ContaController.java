package leoguedex.com.github.JavaBank.controller;

import java.util.List;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

  @Autowired
  private ContaService contaService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Conta> findConta(@PathVariable Integer id) {
    Conta conta = contaService.findConta(id);
    return ResponseEntity.ok().body(conta);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteConta(@PathVariable Integer id) {
    contaService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/depositar/{id}/{valor}", method = RequestMethod.PUT)
  public ResponseEntity<String> depositar(@PathVariable Integer id, @PathVariable Double valor) {
    Conta conta = contaService.findConta(id);
    contaService.depositar(conta, valor);
    return ResponseEntity.ok().body("Saldo atualizado: " + conta.getSaldoAtual());
  }

  @RequestMapping(value = "/sacar/{id}/{valor}", method = RequestMethod.PUT)
  public ResponseEntity<String> sacar(@PathVariable Integer id, @PathVariable Double valor) {
    Conta conta = contaService.findConta(id);
    contaService.sacar(conta, valor);
    return ResponseEntity.ok().body("Saldo atualizado: " + conta.getSaldoAtual());
  }

  @RequestMapping(value = "/extratos/{id}", method = RequestMethod.GET)
  public List<String> obterExtratos(@PathVariable Integer id){
    Conta conta = contaService.findConta(id);
    return conta.getExtratoBancario();
  }

  @RequestMapping(value = "/transferencia/{idOrigem}/{valor}/{idDestino}", method = RequestMethod.POST)
  public void tranferenciaEntreContas(@PathVariable Integer idOrigem,
      @PathVariable Double valor, @PathVariable Integer idDestino){
    Conta contaOrigem = contaService.findConta(idOrigem);
    Conta contaDestino = contaService.findConta(idDestino);
    contaService.transferenciaEntreContas(contaOrigem, contaDestino, valor);
  }

}
