package net.sf.anathema.characterengine.support;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;

public class NumericQualityMinimumValueRule implements Rule {
  private final int minimum;

  public NumericQualityMinimumValueRule(int minimum) {
    this.minimum = minimum;
  }

  @Override
  public Permission permits(QualityClosure closure, Quality quality) {
    NumericQuality copy = (NumericQuality) quality.copy();
    closure.execute(copy);
    for (int value = 0; value < minimum; value++) {
      if (copy.hasValue(new NumericValue(value))) {
        return Permission.Denied;
      }
    }
    return Permission.Granted;
  }
}