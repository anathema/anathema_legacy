package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;

public abstract class AbstractMultiLearnableCharm implements IMultiLearnableCharm {

  private final String charmId;

  public AbstractMultiLearnableCharm(String charmId) {
    this.charmId = charmId;
  }

  public final String getCharmId() {
    return charmId;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultiLearnableCharm(this);
  }
  
  public int getMinimumLearnCount(IGenericTraitCollection traitCollection)
  {
	  return 1;
  }
}