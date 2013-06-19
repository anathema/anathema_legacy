package net.sf.anathema.character.generic.traits;

import net.sf.anathema.hero.model.Hero;

public interface ITraitMinimum {

  int getMinimumValue(Hero hero);
  
  int getCalculationMinValue(Hero hero, TraitType traitType);
}