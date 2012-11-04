package net.sf.anathema.characterengine.rules;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;

public interface Rule {
  Permission permits(QualityClosure closure, Quality quality);
}