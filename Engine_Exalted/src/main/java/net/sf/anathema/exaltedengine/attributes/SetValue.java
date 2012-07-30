package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.exaltedengine.NumericValue;

public class SetValue implements QualityClosure {
  private final NumericValue numericValue;

  public SetValue(NumericValue numericValue) {
    this.numericValue = numericValue;
  }

  @Override
  public void execute(Quality quality) {
    ((Attribute)quality).changeValueTo(numericValue);
  }
}
