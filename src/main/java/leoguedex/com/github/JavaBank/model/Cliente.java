package leoguedex.com.github.JavaBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "Campo nome é obrigatório")
  private String nome;

  @Column(name = "cpf", length = 11)
  //@CPF(message = "Insert a valid CPF")
  @NotEmpty(message = "campo CPF é obrigatório")
  private String cpf;

  //@Column(unique = true)
  @Email(message = "Email invalido")
  private String email;

  @Column(name = "senha", length = 6)
  private String senha;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "conta_id")
  private Conta conta;


}