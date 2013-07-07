package net.sf.anathema.character.main.magic.model.spells;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.spells.CircleType;

public interface ISpell extends IMagic {

  CircleType getCircleType();

  String getTarget();
}