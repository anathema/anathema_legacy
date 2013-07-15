package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.equipment.core.MagicalMaterial;

public interface MaterialRules {
  MagicalMaterial getDefault(CharacterType characterType);

  ArtifactAttuneType[] getAttunementTypes(CharacterType characterType,
                                          MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials(CharacterType characterType);
}