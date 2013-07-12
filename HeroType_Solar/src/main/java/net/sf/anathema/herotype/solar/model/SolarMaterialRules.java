package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.model.MaterialRulesDefinition;
import net.sf.anathema.hero.equipment.model.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Solar")
public class SolarMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Orichalcum;
  }
}