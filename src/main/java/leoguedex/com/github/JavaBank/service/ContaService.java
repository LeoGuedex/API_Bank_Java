package leoguedex.com.github.JavaBank.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import leoguedex.com.github.JavaBank.exception.DataIntegratyException;
import leoguedex.com.github.JavaBank.exception.ObjetoNaoEncontrado;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.model.dto.ClienteDto;
import leoguedex.com.github.JavaBank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

  @Autowired
  private ContaRepository contaRepository;

  public Conta findConta(Integer id) {
    Optional<Conta> conta = contaRepository.findById(id);
    return conta.orElseThrow(
        () -> new ObjetoNaoEncontrado("Objeto Não Encontrado! Id: " + id + ", tipo: "
            + Conta.class.getName()));
  }

  public void delete(Integer id) {
    findConta(id);
    try {
      contaRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegratyException(
          "Não é possivel excluir uma conta que possui dados vinculados.");
    }
  }

  public void depositar(Conta conta, Double valor) {
    conta.setSaldoAtual(conta.getSaldoAtual() + valor);
    conta.setExtratoBancario(
        "Deposito de R$ " + valor + ", realizado no dia " + LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    contaRepository.save(conta);
  }

  public void sacar(Conta conta, Double valor) {
    if (conta.getSaldoAtual() - valor < 0.0) {
      throw new RuntimeException("Impossivel transferir mais que o valor existente");
    }
    conta.setSaldoAtual(conta.getSaldoAtual() - valor);
    conta.setExtratoBancario(
        "Saque     de R$ " + valor + ", realizado no dia " + LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    contaRepository.save(conta);
  }

  public void transferenciaEntreContas(Conta contaOrigem, Conta contaDestino, Double valor) {
    if(contaOrigem.getSaldoAtual() - valor <= 0){
      throw new RuntimeException("Impossivel sacar mais que o valor existente");
    }
    contaOrigem.setSaldoAtual(contaOrigem.getSaldoAtual() - valor);
    contaOrigem.setExtratoBancario("Transferencia ENVIADA  no valor de R$ " + valor + " para " + contaDestino.getCliente().getNome());

    contaDestino.setSaldoAtual(contaDestino.getSaldoAtual() + valor);
    contaDestino.setExtratoBancario("Transferencia RECEBIDA no valor de R$ " + valor + " de " + contaOrigem.getCliente().getNome());
    contaRepository.save(contaDestino);
    contaRepository.save(contaOrigem);
  }

  public Conta fromDtoConta(ClienteDto clienteDto) {
    return new Conta(0.0, "Conta Criada no dia " + LocalDate.now(), clienteDto.getTipoConta());
  }

}
