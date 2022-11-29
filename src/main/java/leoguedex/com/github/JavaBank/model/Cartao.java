package leoguedex.com.github.JavaBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "dataValidade")
    private String dataValidade;

    @Column(name = "limiteCartao")
    private BigDecimal limiteCartao;

    @ManyToOne()
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Cartao(Conta conta) {
        Random random = new Random();
        this.numCartao = "" + random.nextInt(9999) + " "
                + random.nextInt(9999) + " "
                + random.nextInt(9999) + " "
                + random.nextInt(9999) + " ";
        this.codSeguranca = random.nextInt(999);
        this.dataValidade = "" + random.nextInt(12) + "/" + (LocalDate.now().getYear() + 6);
        this.limiteCartao = BigDecimal.valueOf(random.nextInt(5250));
        this.conta = conta;
    }

}
