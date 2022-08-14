package leoguedex.com.github.JavaBank.model.enums;

public enum TipoConta {

  CONTA_SALARIO(0, "Conta Salario"),
  CONTA_CORRENTE(1, "Conta Corrente"),
  CONTA_POUPANCA(2, "Conta Poupança");

  private int cod;
  private String description;

  TipoConta(int id, String description) {
    this.cod = id;
    this.description = description;
  }

  public int getCod() {
    return cod;
  }

  public static TipoConta toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }
    for (TipoConta x : TipoConta.values()) {
      if (cod.equals(x.getCod())) {
        return x;
      }
    }

    throw  new IllegalArgumentException("Invalid ID" + cod);
  }
}
