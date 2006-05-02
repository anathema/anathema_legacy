package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;

public interface ICharmProvider {

  public ISpecialCharm[] getSpecialCharms(CharacterType characterType, IExaltedEdition edition);

  public void setSpecialCharms(CharacterType type, IExaltedEdition edition, ISpecialCharm[] charms);

  public ISpecialCharm[] getAllSpecialCharms(IExaltedEdition edition);
}