package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Abyssal")
public class AbyssalMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Soulsteel;
  }
}