package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class AgeBasedLimitation implements ITraitLimitation {
  private final int absoluteLimit;
  private final int[] ageArray = {100, 250, 500, 1000};

  public AgeBasedLimitation(int maxValue) {
    this.absoluteLimit = maxValue;
  }

  @Override
  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    return absoluteLimit;
  }

  @Override
  public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified) {
    int age = limitationContext.getAge();
    for (int categories = 0; categories != ageArray.length; categories++) {
      if (age < ageArray[categories]) {
        return 5 + categories;
      }
    }
    return absoluteLimit;
  }

  @Override
  public AgeBasedLimitation clone() {
    try {
      return (AgeBasedLimitation) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }
}