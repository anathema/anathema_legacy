package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;
import net.sf.anathema.exaltedengine.NumericValue;

public class MaximumAttributeValue implements Rule {
  private final int maximum;

  public MaximumAttributeValue(int maximum) {
    this.maximum = maximum;
  }

  @Override
  public Permission permits(Quality changedCopyOfQuality) {
    Attribute copy = (Attribute) changedCopyOfQuality;
    for (int value = 0; value <= maximum; value++) {
      if (copy.hasValue(new NumericValue(value))) {
        return Permission.Granted;
      }
    }
    return Permission.Denied;
  }
}