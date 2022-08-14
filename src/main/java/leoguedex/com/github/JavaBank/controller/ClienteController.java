package leoguedex.com.github.JavaBank.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import leoguedex.com.github.JavaBank.exception.DataIntegratyException;
import leoguedex.com.github.JavaBank.model.Cliente;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.model.dto.ClienteDto;
import leoguedex.com.github.JavaBank.model.dto.ClienteRequestDto;
import leoguedex.com.github.JavaBank.service.ClienteService;
import leoguedex.com.github.JavaBank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ContaService contaService;

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDto clienteDto) {
    Cliente cliente = clienteService.fromDtoCliente(clienteDto);
    Conta conta = contaService.fromDtoConta(clienteDto);
    cliente.setConta(conta);
    conta.setCliente(cliente);
    clienteService.insert(cliente, conta);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(cliente.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<StringBuffer> findCliente(@PathVariable Integer id) {
    Cliente cliente = clienteService.findCliente(id);
    StringBuffer sb = new StringBuffer("Cliente{\n");
    sb.append("  Id: ").append(id).append("\n");
    sb.append("  Nome: ").append(cliente.getNome()).append("\n");
    sb.append("  Cpf: ").append(cliente.getCpf()).append("\n");
    sb.append("  Email: ").append(cliente.getEmail()).append("\n");
    sb.append("  Senha: ").append(cliente.getSenha()).append("\n");
    sb.append("    Conta Id: ").append(cliente.getConta().getId()).append("\n");
    sb.append("    Tipo Conta: ").append(cliente.getConta().getTipoConta()).append("\n");
    sb.append("    Conta Saldo: ").append(cliente.getConta().getSaldoAtual()).append("\n");
    sb.append("    Conta criada Dia: ").append(cliente.getConta().getDataCriacao().getDayOfMonth())
        .append("\n");
    sb.append("    Conta criada Mes: ").append(cliente.getConta().getDataCriacao().getMonth())
        .append("\n");
    sb.append("    Conta criada Ano: ").append(cliente.getConta().getDataCriacao().getYear())
        .append("\n");
    sb.append('}');
    return ResponseEntity.ok().body(sb);
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Cliente> findAll() {
    return clienteService.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
      clienteService.delete(id);
      return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> updateCliente(@PathVariable Integer id,
      @RequestBody ClienteRequestDto requestDto) {
    Cliente cliente = clienteService.updateFromDto(requestDto);
    cliente.setId(id);
    clienteService.updateCliente(cliente);
    return ResponseEntity.noContent().build();
  }

}
