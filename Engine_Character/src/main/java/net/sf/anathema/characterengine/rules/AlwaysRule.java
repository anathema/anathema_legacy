package net.sf.anathema.characterengine.rules;

import net.sf.anathema.characterengine.persona.Permission;
import net.sf.anathema.characterengine.quality.Quality;

public class AlwaysRule implements Rule {
  @Override
  public Permission permits(Quality changedCopyOfQuality) {
    return Permission.Granted;
  }
}