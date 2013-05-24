package net.sf.anathema.exaltedengine.numericquality;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;

public class MinimumQualityValue implements Rule {
  private final int minimum;

  public MinimumQualityValue(int minimum) {
    this.minimum = minimum;
  }

  @Override
  public Permission permits(Quality changedCopyOfQuality) {
    QualityWithValue copy = (QualityWithValue) changedCopyOfQuality;
    for (int value = 0; value < minimum; value++) {
      if (copy.hasValue(new NumericValue(value))) {
        return Permission.Denied;
      }
    }
    return Permission.Granted;
  }
}