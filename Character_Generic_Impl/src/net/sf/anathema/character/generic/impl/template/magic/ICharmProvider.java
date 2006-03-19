package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterType;

public interface ICharmProvider {

  public ISpecialCharm[] getAllSpecialCharms();

  public ISpecialCharm[] getSpecialCharms(CharacterType characterType);

  public void setSpecialCharms(CharacterType type, ISpecialCharm[] charms);

}
