package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ISpell extends IMagic {

  public CircleType getCircleType();

  public IMagicSource getSource(IExaltedEdition edition);
}