package leoguedex.com.github.JavaBank.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

  @NotEmpty(message = "Campo nome é obrigatório")
  private String nome;

  @NotEmpty(message = "Field name is mandatory")
  private String cpf;

  private String email;

  private String senha;

  private Integer tipoConta;

}
