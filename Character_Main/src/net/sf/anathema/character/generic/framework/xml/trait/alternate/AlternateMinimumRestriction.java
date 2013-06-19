package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

import java.util.ArrayList;
import java.util.List;

public class AlternateMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction {

  private final int minimumTraitCount;
  private final int strictMinimumValue;
  private final List<TraitType> alternateTraitTypes = new ArrayList<>();
  private boolean isFreebie;

  public AlternateMinimumRestriction(int minimumTraitCount, int strictMinimumValue) {
    this.minimumTraitCount = minimumTraitCount;
    this.strictMinimumValue = strictMinimumValue;
  }

  @Override
  public boolean isFulfilledWithout(Hero hero, TraitType traitType) {
    TraitModel traits = TraitModelFetcher.fetch(hero);
    int fulfillingTraitCount = 0;
    for (TraitType type : alternateTraitTypes) {
      if (type != traitType && traits.getTrait(type).getCurrentValue() >= strictMinimumValue) {
        fulfillingTraitCount++;
      }
    }
    return fulfillingTraitCount >= minimumTraitCount;
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType traitType) {
    if (!isFreebie) {
      return 0;
    }
    TraitModel traits = TraitModelFetcher.fetch(hero);
    int fulfillingTraitCount = 0;
    for (TraitType type : alternateTraitTypes) {
      if (traits.getTrait(type).getCurrentValue() >= strictMinimumValue) {
        fulfillingTraitCount++;
        if (type == traitType) {
          return strictMinimumValue;
        }
      }
      if (fulfillingTraitCount == minimumTraitCount) {
        break;
      }
    }
    return 0;
  }

  @Override
  public void setIsFreebie(boolean value) {
    isFreebie = value;
  }

  @Override
  public void addTraitType(TraitType traitType) {
    alternateTraitTypes.add(traitType);
  }

  @Override
  public int getStrictMinimumValue() {
    return strictMinimumValue;
  }

  @Override
  public void clear() {
  }
}