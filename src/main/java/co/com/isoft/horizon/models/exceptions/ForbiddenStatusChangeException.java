package co.com.isoft.horizon.models.exceptions;

public class ForbiddenStatusChangeException extends Exception {
  public ForbiddenStatusChangeException() {}

  public ForbiddenStatusChangeException(String message) {
    super(message);
  }
}
