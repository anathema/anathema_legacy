package net.sf.anathema.character.main.caste;

import net.sf.anathema.character.main.template.ITemplateType;

public class ConfigurableCasteCollection implements CasteCollection {
  private final CasteType[] allTypes;

  public ConfigurableCasteCollection(CasteType[] allTypes) {
    this.allTypes = allTypes;
  }

  @Override
  public CasteType getById(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No caste with found for id " + casteTypeId);
  }

  @Override
  public boolean isEmpty() {
    return allTypes.length <= 0;
  }

  @Override
  public boolean containsCasteType(String casteTypeId) {
    for (CasteType type : allTypes) {
      if (type.getId().equals(casteTypeId)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public CasteType[] getAllCasteTypes(ITemplateType template) {
    return allTypes;
  }
}