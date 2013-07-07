package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.spells.CircleType;

public interface ISpell extends IMagic {

  CircleType getCircleType();

  String getTarget();
}