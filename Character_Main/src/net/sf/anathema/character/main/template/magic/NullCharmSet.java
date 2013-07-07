package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.ICharm;

public class NullCharmSet implements ICharmSet {

  @Override
  public ICharm[] getCharms() {
    return new ICharm[0];
  }

  @Override
  public ICharm[] getMartialArtsCharms() {
    return new ICharm[0];
  }
}