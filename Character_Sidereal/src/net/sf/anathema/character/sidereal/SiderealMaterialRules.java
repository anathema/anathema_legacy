package net.sf.anathema.character.sidereal;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Sidereal")
public class SiderealMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Starmetal;
  }
}