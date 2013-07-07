package net.sf.anathema.character.equipment;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;

public interface CharacterTypeMaterialRules {
  MagicalMaterial getDefault();

  ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials();
}