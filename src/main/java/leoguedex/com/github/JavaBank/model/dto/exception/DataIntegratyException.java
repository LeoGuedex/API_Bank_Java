package leoguedex.com.github.JavaBank.model.dto.exception;

public class DataIntegratyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataIntegratyException(String message) {
    super(message);
  }

}