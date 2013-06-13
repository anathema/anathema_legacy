package net.sf.anathema.character.library.trait.visitor;

public interface ITraitVisitor {

  void visitDefaultTrait(IDefaultTrait visitedTrait);

  void visitAggregatedTrait(IAggregatedTrait visitedTrait);
}