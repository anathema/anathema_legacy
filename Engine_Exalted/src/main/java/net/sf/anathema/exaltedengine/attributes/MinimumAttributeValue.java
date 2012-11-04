package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.rules.Rule;
import net.sf.anathema.exaltedengine.NumericValue;

public class MinimumAttributeValue implements Rule {
  private final int minimum;

  public MinimumAttributeValue(int minimum) {
    this.minimum = minimum;
  }

  @Override
  public Permission permits(Quality changedCopyOfQuality) {
    Attribute copy = (Attribute) changedCopyOfQuality;
    for (int value = 0; value < minimum; value++) {
      if (copy.hasValue(new NumericValue(value))) {
        return Permission.Denied;
      }
    }
    return Permission.Granted;
  }
}