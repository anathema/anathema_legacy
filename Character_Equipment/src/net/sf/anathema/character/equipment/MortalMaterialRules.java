package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

public class MortalMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return getNullAttunementTypes();
  }
}