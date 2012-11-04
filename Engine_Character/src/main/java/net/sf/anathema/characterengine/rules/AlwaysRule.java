package net.sf.anathema.characterengine.rules;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;

public class AlwaysRule implements Rule {
  @Override
  public Permission permits(QualityClosure closure, Quality quality) {
    return Permission.Granted;
  }
}
