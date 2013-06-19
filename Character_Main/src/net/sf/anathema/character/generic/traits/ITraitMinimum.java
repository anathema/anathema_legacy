package net.sf.anathema.character.generic.traits;

import net.sf.anathema.character.main.hero.Hero;

public interface ITraitMinimum {

  int getMinimumValue(Hero hero);
  
  int getCalculationMinValue(Hero hero, TraitType traitType);
}