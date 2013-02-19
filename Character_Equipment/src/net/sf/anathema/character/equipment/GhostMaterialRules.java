package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.ExpensivePartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;

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