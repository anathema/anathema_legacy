package net.sf.anathema.characterengine.rules;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;

public interface Rule {
  Permission permits(Quality changedCopyOfQuality);
}