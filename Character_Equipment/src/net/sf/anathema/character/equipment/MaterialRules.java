package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;

public interface MaterialRules {
  MagicalMaterial getDefault();

  ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials();
}