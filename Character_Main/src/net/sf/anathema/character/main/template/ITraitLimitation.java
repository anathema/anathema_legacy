package net.sf.anathema.character.main.template;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ICloneable;

public interface ITraitLimitation extends ICloneable<ITraitLimitation> {

  int getAbsoluteLimit(Hero hero);

  int getCurrentMaximum(Hero hero, boolean modified);
}