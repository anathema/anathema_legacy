package net.sf.anathema.character.ghost;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRulesDefinition;
import net.sf.anathema.character.equipment.SimpleMaterialRules;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.ExpensivePartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;

@MaterialRulesDefinition(characterType = "Ghost")
public class GhostMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    if (material == MagicalMaterial.Orichalcum ||
            material == MagicalMaterial.Moonsilver ||
            material == MagicalMaterial.Starmetal)
      return new ArtifactAttuneType[]
              {Unattuned, ExpensivePartiallyAttuned};
    else
      return new ArtifactAttuneType[]
              {Unattuned, PartiallyAttuned};
  }
}