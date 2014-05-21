package net.sf.anathema.hero.dummy.models;

import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteType;

public class NullCasteCollection implements CasteCollection {
  @Override
  public boolean containsCasteType(String casteTypeId) {
    return false;
  }

  @Override
  public CasteType[] getAllCasteTypes(TemplateType template) {
    return new CasteType[0];
  }

  @Override
  public CasteType getById(String casteTypeId) {
    return CasteType.NULL_CASTE_TYPE;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
