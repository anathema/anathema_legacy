package net.sf.anathema.character.main.magic.model.spells;

import net.sf.anathema.character.main.magic.model.magic.Magic;

public interface Spell extends Magic {

  CircleType getCircleType();

  String getTarget();
}