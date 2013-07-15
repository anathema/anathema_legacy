package net.sf.anathema.hero.equipment.model;

public class MissingMaterialException extends RuntimeException {

  public MissingMaterialException(String message) {
    super(message);
  }
}