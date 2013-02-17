package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.lib.util.Identified;

public interface ICharacterType extends Identified {

  void accept(ICharacterTypeVisitor visitor);

  boolean isExaltType();

  boolean isEssenceUser();

  FavoringTraitType getFavoringTraitType();

  boolean canAttuneToMalfeanMaterials();
}