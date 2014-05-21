package net.sf.anathema.hero.traits.model.limitation;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface TraitLimitation extends ICloneable<TraitLimitation> {

  int getAbsoluteLimit(Hero hero);

  int getCurrentMaximum(Hero hero, boolean modified);
}