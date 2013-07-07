package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialRules;
import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.type.ICharacterType;

public class DummyMaterialRules implements MaterialRules {
  @Override
  public MagicalMaterial getDefault(ICharacterType characterType) {
    return MagicalMaterial.Moonsilver;
  }

  @Override
  public ArtifactAttuneType[] getAttunementTypes(ICharacterType characterType, MagicalMaterial material) {
    return new ArtifactAttuneType[0];
  }

  @Override
  public boolean canAttuneToMalfeanMaterials(ICharacterType characterType) {
    return false;
  }
}