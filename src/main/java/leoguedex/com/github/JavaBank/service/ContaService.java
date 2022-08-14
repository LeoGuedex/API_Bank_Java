package leoguedex.com.github.JavaBank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import leoguedex.com.github.JavaBank.exception.DataIntegratyException;
import leoguedex.com.github.JavaBank.exception.ObjetoNaoEncontrado;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.model.dto.ClienteDto;
import leoguedex.com.github.JavaBank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
      throw new DataIntegratyException("Não é possivel excluir uma conta que possui dados vinculados.");
    }
  }

  public Conta fromDtoConta(ClienteDto clienteDto) {
    return new Conta(0.0, "Conta Criada no dia " + LocalDate.now(), clienteDto.getTipoConta());
  }

}
