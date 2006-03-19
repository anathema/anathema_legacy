package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.spells.CircleType;


public interface ISpell extends IMagic {

  public CircleType getCircleType();
}