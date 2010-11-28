package net.sf.anathema.character.library.trait.visitor;

import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;

public interface IAggregatedTrait extends ITrait {

  public ISubTraitContainer getSubTraits();

  public IDefaultTrait getFallbackTrait();
}