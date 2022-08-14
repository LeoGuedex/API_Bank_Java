package leoguedex.com.github.JavaBank.model.dto;

import leoguedex.com.github.JavaBank.model.Cliente;
import leoguedex.com.github.JavaBank.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {

  private Integer id;

  private String nome;

  private String cpf;

  private String email;

  private Conta conta;

  public ClienteResponseDto(Cliente cliente){
    this.id = cliente.getId();
    this.nome = cliente.getNome();
    this.cpf = cliente.getCpf();
    this.email = cliente.getEmail();
    this.conta = cliente.getConta();
  }

}
