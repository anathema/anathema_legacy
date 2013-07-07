package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Solar")
public class SolarMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Orichalcum;
  }
}