package net.sf.anathema.character.db;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Dragon-Blooded")
public class DbMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.Jade;
  }
}