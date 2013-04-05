package net.sf.anathema.lib.gui.image;

public class LoadingException extends RuntimeException {
  LoadingException(String message) {
    super(message);
  }

  LoadingException(String message, Throwable cause) {
    super(message + " :" + cause.getMessage());
  }
}