package leoguedex.com.github.JavaBank.exception;

import org.springframework.http.HttpStatus;

public class DataIntegratyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataIntegratyException(String message) {
    super(message);
  }

}