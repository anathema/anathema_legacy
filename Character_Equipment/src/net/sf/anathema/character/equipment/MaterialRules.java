package net.sf.anathema.character.equipment;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.type.ICharacterType;

public interface MaterialRules {
  MagicalMaterial getDefault(ICharacterType characterType);

  ArtifactAttuneType[] getAttunementTypes(ICharacterType characterType,
                                          MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials(ICharacterType characterType);
}