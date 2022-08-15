package leoguedex.com.github.JavaBank.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import leoguedex.com.github.JavaBank.model.Cliente;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.model.enums.TipoConta;
import leoguedex.com.github.JavaBank.repository.ClienteRepository;
import leoguedex.com.github.JavaBank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ContaRepository contaRepository;

  @Autowired
  private ContaService contaService;

  public void instantiateDatabase() throws ParseException {

    Cliente cli1 = new Cliente(null, "Cliente 1", "71148923063", "cliente1@gmail.com", "111111");
    Cliente cli2 = new Cliente(null, "Cliente 2", "48812435009", "cliente2@gmail.com", "222222");
    Cliente cli3 = new Cliente(null, "Cliente 3", "59361542036", "cliente3@gmail.com", "333333");

    clienteRepository.save(cli1);
    clienteRepository.save(cli2);
    clienteRepository.save(cli3);

    Conta conta1 = new Conta(null, 0.0, "Conta Criada no dia" + LocalDateTime.now(),
        TipoConta.CONTA_SALARIO, cli1);
    Conta conta2 = new Conta(null, 0.0, "Conta Criada no dia" + LocalDateTime.now(),
        TipoConta.CONTA_CORRENTE, cli2);
    Conta conta3 = new Conta(null, 0.0, "Conta Criada no dia" + LocalDateTime.now(),
        TipoConta.CONTA_POUPANCA, cli3);

    contaRepository.save(conta1);
    contaRepository.save(conta2);
    contaRepository.save(conta3);

    cli1.setConta(conta1);
    cli2.setConta(conta2);
    cli3.setConta(conta3);

    clienteRepository.save(cli1);
    clienteRepository.save(cli2);
    clienteRepository.save(cli3);

    contaService.depositar(conta1, 50.0);
    contaService.depositar(conta1, 12.89);
    contaService.depositar(conta1, 8.64);

    contaService.depositar(conta2, 50.0);
    contaService.depositar(conta2, 12.89);
    contaService.depositar(conta2, 8.64);

    contaService.depositar(conta3, 50.0);
    contaService.depositar(conta3, 12.89);
    contaService.depositar(conta3, 8.64);

    contaService.sacar(conta1, 15.48);
    contaService.sacar(conta1, 5.42);
    contaService.sacar(conta1, 2.64);

    contaService.sacar(conta2, 7.0);
    contaService.sacar(conta2, 1.49);
    contaService.sacar(conta2, 25.64);

    contaService.sacar(conta3, 2.0);
    contaService.sacar(conta3, 4.79);
    contaService.sacar(conta3, 1.11);

    contaService.transferenciaEntreContas(conta1, conta3, 1.36);
    contaService.transferenciaEntreContas(conta2, conta1, 4.25);
    contaService.transferenciaEntreContas(conta3, conta2, 7.12);
    contaService.transferenciaEntreContas(conta2, conta3, 1.0);
    contaService.transferenciaEntreContas(conta3, conta1, 2.25);
    contaService.transferenciaEntreContas(conta1, conta2, 3.32);
  }

}
