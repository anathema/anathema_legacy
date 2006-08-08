package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.library.trait.aggregated.IAggregatedTrait;

public interface ITraitVisitor {

  public void visitModifiableTrait(IModifiableTrait visitedTrait);

  public void visitAggregatedTrait(IAggregatedTrait visitedTrait);
}