package net.sf.anathema.lib.gui.icon;

public class LoadingException extends RuntimeException {
  LoadingException(String message) {
    super(message);
  }

  LoadingException(String message, Throwable cause) {
    super(message + " :" + cause.getMessage());
  }
}