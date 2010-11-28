package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmGroup extends IIdentificate {

  public ICharm[] getAllCharms();

  public ICharacterType getCharacterType();

  boolean isMartialArtsGroup();
}