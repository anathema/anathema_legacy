package net.sf.anathema.character.generic.type;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharacterType extends IIdentificate, Comparable<CharacterType> {

  void accept(ICharacterTypeVisitor visitor);

  boolean isExaltType();

  boolean isEssenceUser();

  FavoringTraitType getFavoringTraitType();
}