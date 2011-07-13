package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.lib.util.IIdentificate;

public class ComplexAdditionalEssencePool extends AdditionalEssencePool implements IIdentificate {

  private final String id;

  public ComplexAdditionalEssencePool(String id, int multiplier) {
    super(multiplier);
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
}