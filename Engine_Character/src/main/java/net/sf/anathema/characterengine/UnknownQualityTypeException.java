package net.sf.anathema.characterengine;

public class UnknownQualityTypeException extends RuntimeException {
  public UnknownQualityTypeException(Type type) {
    super("Unknown Quality Type: " + type);
  }
}
