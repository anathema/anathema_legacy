package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.equipment.core.MagicalMaterial;

public interface CharacterTypeMaterialRules {
  MagicalMaterial getDefault();

  ArtifactAttuneType[] getAttunementTypes(MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials();
}