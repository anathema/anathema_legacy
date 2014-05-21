package net.sf.anathema.hero.spiritual.persister;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.OtherTraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.traits.persistence.TraitTypeMap;

import java.util.HashMap;
import java.util.Map;

public class SpiritualTraitTypeMap implements TraitTypeMap {

  private Map<String, TraitType> traitTypeMap = new HashMap<>();

  {
    addTraitType(OtherTraitType.Essence);
    addTraitType(OtherTraitType.Willpower);
    for (TraitType type : VirtueType.values()) {
      addTraitType(type);
    }
  }

  private void addTraitType(TraitType type) {
    traitTypeMap.put(type.getId(), type);
  }

  @Override
  public TraitType get(String id) {
    return traitTypeMap.get(id);
  }
}
