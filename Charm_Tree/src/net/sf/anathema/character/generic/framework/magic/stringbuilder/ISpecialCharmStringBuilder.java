package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;

public interface ISpecialCharmStringBuilder {

  public String createDetailsString(ICharm charm, ISpecialCharm details);
}
