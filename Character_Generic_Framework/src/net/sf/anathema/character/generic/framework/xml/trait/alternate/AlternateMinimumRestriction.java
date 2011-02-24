package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class AlternateMinimumRestriction extends ReflectionEqualsObject implements IMinimumRestriction {

  private final int minimumTraitCount;
  private final int strictMinimumValue;
  private final List<ITraitType> alternateTraitTypes = new ArrayList<ITraitType>();

  public AlternateMinimumRestriction(int minimumTraitCount, int strictMinimumValue) {
    this.minimumTraitCount = minimumTraitCount;
    this.strictMinimumValue = strictMinimumValue;
  }

  public boolean isFullfilledWithout(ILimitationContext context, ITraitType traitType) {
    int fullfillingTraitCount = 0;
    for (ITraitType type : alternateTraitTypes) {
      if (type != traitType && context.getTraitCollection().getTrait(type).
    		  getCurrentValue() >= strictMinimumValue) {
        fullfillingTraitCount++;
      }
    }
    return fullfillingTraitCount >= minimumTraitCount;
  }

  public void addTraitType(ITraitType traitType) {
    alternateTraitTypes.add(traitType);
  }

  public int getStrictMinimumValue() {
    return strictMinimumValue;
  }
}