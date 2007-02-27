package net.sf.anathema.character.generic.type;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICharacterType extends IIdentificate, Comparable<CharacterType> {

  public void accept(ICharacterTypeVisitor abstractSupportedCharacterTypeVisitor);

  // nothing to do for the moment

  public boolean isExaltType();
}