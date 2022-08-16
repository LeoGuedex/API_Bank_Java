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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartao")
public class Cartao {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numCartao", length = 20)
  private String numCartao;

  @Column(name = "codSeguranca", length = 3)
  private Integer codSeguranca;

  @Column(name = "dataValidade", length = 5)
  private String dataValidade;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "conta_id")
  private Conta conta;



}
