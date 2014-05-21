package net.sf.anathema.character.framework.type;

import net.sf.anathema.hero.template.magic.FavoringTraitType;
import net.sf.anathema.lib.util.Identifier;

public interface CharacterType extends Identifier {

  boolean isExaltType();

  boolean isEssenceUser();

  FavoringTraitType getFavoringTraitType();
}