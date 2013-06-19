package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface ITraitLimitation extends ICloneable<ITraitLimitation> {

  int getAbsoluteLimit(Hero hero);

  int getCurrentMaximum(Hero hero, boolean modified);
}