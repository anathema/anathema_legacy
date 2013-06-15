package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.lib.util.Identified;

public interface ICharacterType extends Identified {

  boolean isExaltType();

  boolean isEssenceUser();

  FavoringTraitType getFavoringTraitType();
}