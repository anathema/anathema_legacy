package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

public class SpiritMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return getNullAttunementTypes();
  }
}