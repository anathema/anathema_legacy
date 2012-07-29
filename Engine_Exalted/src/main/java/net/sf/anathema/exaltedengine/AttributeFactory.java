package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;

public class AttributeFactory implements QualityFactory {

  private NumericValue startValue;

  public AttributeFactory(NumericValue startValue) {
    this.startValue = startValue;
  }

  @Override
  public Quality create(Name name) {
    return new Attribute(startValue, name);
  }
}
