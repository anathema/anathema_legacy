package net.sf.anathema.hero.charms.model.special.paintolerance;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface IPainToleranceCharm extends ISpecialCharm {

  int getPainToleranceLevel(int learnCount);
}