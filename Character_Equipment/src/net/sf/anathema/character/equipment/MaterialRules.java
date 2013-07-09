package net.sf.anathema.character.equipment;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.equipment.core.MagicalMaterial;

public interface MaterialRules {
  MagicalMaterial getDefault(ICharacterType characterType);

  ArtifactAttuneType[] getAttunementTypes(ICharacterType characterType,
                                          MagicalMaterial material);

  boolean canAttuneToMalfeanMaterials(ICharacterType characterType);
}