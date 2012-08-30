package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;

public abstract class AbstractMultiLearnableCharm implements IMultiLearnableCharm {

  private final String charmId;

  public AbstractMultiLearnableCharm(String charmId) {
    this.charmId = charmId;
  }

  @Override
  public final String getCharmId() {
    return charmId;
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultiLearnableCharm(this);
  }

  @Override
  public int getMinimumLearnCount(GenericTraitProvider traitCollection) {
    return 1;
  }
}