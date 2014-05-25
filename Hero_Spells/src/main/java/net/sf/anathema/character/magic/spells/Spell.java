package net.sf.anathema.character.magic.spells;

import net.sf.anathema.character.magic.basic.Magic;

public interface Spell extends Magic {

  CircleType getCircleType();

  String getTarget();
}