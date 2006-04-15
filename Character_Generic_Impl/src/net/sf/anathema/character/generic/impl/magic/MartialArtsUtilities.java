package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;

public class MartialArtsUtilities {

  public static boolean isMartialArtsCharm(ICharm charm) {
    return charm instanceof IMartialArtsCharm;
  }
}