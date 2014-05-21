package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.template.caste.CasteTemplate;

import java.util.ArrayList;
import java.util.List;

public class ConfigurableCasteCollection implements CasteCollection {
  private final List<CasteType> allTypes = new ArrayList<>();

  public ConfigurableCasteCollection(CasteTemplate template) {
    for (String caste : template.castes) {
      allTypes.add(new ConfigurableCasteType(caste));
    }
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
    return allTypes.isEmpty();
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
  public CasteType[] getAllCasteTypes(TemplateType template) {
    return allTypes.toArray(new CasteType[allTypes.size()]);
  }
}