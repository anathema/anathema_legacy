package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.engine.QualityFactory;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.exaltedengine.NumericValue;

public class AttributeFactory implements QualityFactory {

  private NumericValue startValue = new NumericValue(1);

  @Override
  public Quality create(Name name) {
    return new Attribute(startValue, name);
  }
}
