package net.sf.anathema.character.generic.framework.xml.trait;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.hero.Hero;

public interface IMinimumRestriction {

  boolean isFulfilledWithout(Hero hero, TraitType traitType);

  int getCalculationMinValue(Hero hero, TraitType traitType);

  void clear();

  int getStrictMinimumValue();

  void addTraitType(TraitType type);

  void setIsFreebie(boolean isFreebie);
}