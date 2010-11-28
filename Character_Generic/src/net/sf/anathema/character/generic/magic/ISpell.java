package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public interface ISpell extends IMagic {

  public CircleType getCircleType();

  public IExaltedSourceBook getSource(IExaltedEdition edition);

  public String getTarget();
}