package net.sf.anathema.character.generic.rules;

public interface IEditionVisitor {

  void visitFirstEdition(IExaltedEdition visitedEdition);

  void visitSecondEdition(IExaltedEdition visitedEdition);
}