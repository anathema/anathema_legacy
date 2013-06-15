package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.lib.util.Identifier;

public interface ICharacterType extends Identifier {

  boolean isExaltType();

  boolean isEssenceUser();

  FavoringTraitType getFavoringTraitType();
}