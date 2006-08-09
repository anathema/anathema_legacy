package net.sf.anathema.character.library.trait.visitor;

import net.sf.anathema.character.library.trait.IDefaultTrait;

public interface ITraitVisitor {

  public void visitDefaultTrait(IDefaultTrait visitedTrait);

  public void visitAggregatedTrait(IAggregatedTrait visitedTrait);
}