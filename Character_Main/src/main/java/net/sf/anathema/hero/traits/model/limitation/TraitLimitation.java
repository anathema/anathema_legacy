package net.sf.anathema.hero.traits.model.limitation;

import net.sf.anathema.hero.model.Hero;

public interface TraitLimitation {

  int getAbsoluteLimit(Hero hero);

  int getCurrentMaximum(Hero hero, boolean modified);
}