package leoguedex.com.github.JavaBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
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

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

}