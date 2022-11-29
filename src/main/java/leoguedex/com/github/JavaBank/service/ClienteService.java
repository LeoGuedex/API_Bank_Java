package leoguedex.com.github.JavaBank.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import leoguedex.com.github.JavaBank.model.dto.exception.DataIntegratyException;
import leoguedex.com.github.JavaBank.model.dto.exception.ObjetoNaoEncontradoException;
import leoguedex.com.github.JavaBank.model.Cliente;
import leoguedex.com.github.JavaBank.model.Conta;
import leoguedex.com.github.JavaBank.model.dto.ClienteDto;
import leoguedex.com.github.JavaBank.model.dto.ClienteRequestDto;
import leoguedex.com.github.JavaBank.repository.ClienteRepository;
import leoguedex.com.github.JavaBank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ContaRepository contaRepository;

  @Transactional
  public void insert(Cliente cliente, Conta conta) {
    cliente.setId(null);
    conta.setId(null);
    clienteRepository.save(cliente);
    contaRepository.save(conta);
  }

  public Cliente findCliente(Integer id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.orElseThrow(
        () -> new ObjetoNaoEncontradoException("Objeto Não Encontrado! Id: " + id + ", tipo: "
            + Cliente.class.getName()));
  }

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public void delete(Integer id) {
    findCliente(id);
    try {
      clienteRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegratyException(
          "Não é possivel excluir um cliente que possui dados vinculados.");
    }
  }

  public Cliente fromDtoCliente(ClienteDto clienteDto) {
    return new Cliente().builder()
        .nome(clienteDto.getNome())
        .cpf(clienteDto.getCpf())
        .email(clienteDto.getEmail())
        .senha(clienteDto.getSenha())
        .build();
  }

  public Cliente updateFromDto(ClienteRequestDto requestDto) {
    return new Cliente(null, requestDto.getNome(), null,
            requestDto.getEmail(),
        requestDto.getSenha(), null);
  }

  public Cliente updateCliente(Cliente cliente) {
    Cliente clienteToUpdate = findCliente(cliente.getId());
    updateData(clienteToUpdate, cliente);
    return clienteRepository.save(clienteToUpdate);
  }

  public void updateData(Cliente clienteToUpdate, Cliente cliente) {
    clienteToUpdate.setNome(cliente.getNome());
    clienteToUpdate.setEmail(cliente.getEmail());
    clienteToUpdate.setSenha(cliente.getSenha());
  }
}
