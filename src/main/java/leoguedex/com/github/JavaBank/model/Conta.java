package leoguedex.com.github.JavaBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import leoguedex.com.github.JavaBank.model.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conta")
public class Conta {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Double saldoAtual;

  @ElementCollection
  @CollectionTable(name = "extrato")
  private List<String> extratoBancario = new ArrayList<>();

  private TipoConta tipoConta;

  @OneToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  private LocalDateTime dataCriacao;

  public Conta(Double saldoAtual, String extratoBancario, Integer tipoConta ) {
    this.saldoAtual = saldoAtual;
    this.extratoBancario.add(extratoBancario);
    this.tipoConta = TipoConta.toEnum(tipoConta);
    this.dataCriacao = LocalDateTime.now();
  }

  public void setExtratoBancario(String extratoBancario) {
    this.extratoBancario.add(extratoBancario);
  }
}
