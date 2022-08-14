package leoguedex.com.github.JavaBank.controller;

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

}
