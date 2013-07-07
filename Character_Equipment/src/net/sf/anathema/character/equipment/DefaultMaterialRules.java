package net.sf.anathema.character.equipment;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;

public class DefaultMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return getNullAttunementTypes();
  }
}