package net.sf.anathema.character.main.magic.spells;

import net.sf.anathema.character.main.magic.model.Magic;

public interface Spell extends Magic {

  CircleType getCircleType();

  String getTarget();
}