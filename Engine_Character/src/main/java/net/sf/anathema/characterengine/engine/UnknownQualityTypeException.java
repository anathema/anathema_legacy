package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.quality.Type;

public class UnknownQualityTypeException extends RuntimeException {
  public UnknownQualityTypeException(Type type) {
    super("Unknown Quality Type: " + type);
  }
}
