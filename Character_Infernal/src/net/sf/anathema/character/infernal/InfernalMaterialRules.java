package net.sf.anathema.character.infernal;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;

@MaterialRulesDefinition(characterType = "Infernal")
public class InfernalMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return MagicalMaterial.VitriolOrichalcum;
  }

  @Override
  protected boolean resonatesWithMaterial(MagicalMaterial material) {
    return MagicalMaterial.isVitriolTainted(material);
  }

  @Override
  public boolean canAttuneToMalfeanMaterials() {
    return true;
  }
}