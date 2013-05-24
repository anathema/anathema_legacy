package net.sf.anathema.exaltedengine.numericquality;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;

public class MaximumQualityValue implements Rule {
  private final int maximum;

  public MaximumQualityValue(int maximum) {
    this.maximum = maximum;
  }

  @Override
  public Permission permits(Quality changedCopyOfQuality) {
    QualityWithValue copy = (QualityWithValue) changedCopyOfQuality;
    for (int value = 0; value <= maximum; value++) {
      if (copy.hasValue(new NumericValue(value))) {
        return Permission.Granted;
      }
    }
    return Permission.Denied;
  }
}