package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAlternative;

public class CharmAlternative implements ICharmAlternative {

  private final ICharm[] charms;

  public CharmAlternative(ICharm[] charms) {
    this.charms = charms;
  }

  public ICharm[] getCharms() {
    return charms;
  }
}