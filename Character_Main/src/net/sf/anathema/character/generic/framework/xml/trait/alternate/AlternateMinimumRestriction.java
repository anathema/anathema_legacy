package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.xml.trait.IMinimumRestriction;
import net.sf.anathema.character.generic.traits.TraitType;
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
  public boolean isFullfilledWithout(ILimitationContext context, TraitType traitType) {
    int fullfillingTraitCount = 0;
    for (TraitType type : alternateTraitTypes) {
      if (type != traitType && context.getTraitCollection().getTrait(type).
              getCurrentValue() >= strictMinimumValue) {
        fullfillingTraitCount++;
      }
    }
    return fullfillingTraitCount >= minimumTraitCount;
  }

  @Override
  public int getCalculationMinValue(ILimitationContext context, TraitType traitType) {
    if (!isFreebie) {
      return 0;
    }
    int fullfillingTraitCount = 0;
    for (TraitType type : alternateTraitTypes) {
      if (context.getTraitCollection().getTrait(type).getCurrentValue() >= strictMinimumValue) {
        fullfillingTraitCount++;
        if (type == traitType) {
          return strictMinimumValue;
        }
      }
      if (fullfillingTraitCount == minimumTraitCount) {
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