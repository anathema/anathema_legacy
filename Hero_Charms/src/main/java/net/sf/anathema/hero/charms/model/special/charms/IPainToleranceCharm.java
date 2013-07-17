package net.sf.anathema.hero.charms.model.special.charms;

public interface IPainToleranceCharm extends ISpecialCharm {

  int getPainToleranceLevel(int learnCount);
}