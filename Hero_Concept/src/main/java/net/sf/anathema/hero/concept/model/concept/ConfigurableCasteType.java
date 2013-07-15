package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.concept.CasteType;

public class ConfigurableCasteType implements CasteType {

  private String id;

  public ConfigurableCasteType(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}