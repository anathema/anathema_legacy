package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.utilities.ForCharacterType;
import net.sf.anathema.hero.equipment.model.SimpleMaterialRules;

@ForCharacterType("Solar")
public class SolarMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Orichalcum;
  }
}