package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.ICharmSet;

public class NullCharmSet implements ICharmSet {

  @Override
  public ICharm[] getCharms() {
    return new ICharm[0];
  }
  
  @Override
  public ICharm[] getUniqueCharms() {
	return new ICharm[0];
  }

  @Override
  public ICharm[] getMartialArtsCharms() {
    return new ICharm[0];
  }
}