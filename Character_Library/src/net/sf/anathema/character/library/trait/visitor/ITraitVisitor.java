package net.sf.anathema.character.library.trait.visitor;

public interface ITraitVisitor {

  public void visitDefaultTrait(IDefaultTrait visitedTrait);

  public void visitAggregatedTrait(IAggregatedTrait visitedTrait);
}