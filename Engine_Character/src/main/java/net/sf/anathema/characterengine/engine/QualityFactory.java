package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;

public interface QualityFactory {
  Quality create(Name name);
}
