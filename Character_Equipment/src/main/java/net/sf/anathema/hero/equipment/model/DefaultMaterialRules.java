package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.equipment.core.MagicalMaterial;

public class DefaultMaterialRules extends SimpleMaterialRules {
  public MagicalMaterial getDefault() {
    return null;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material) {
    return getNullAttunementTypes();
  }
}