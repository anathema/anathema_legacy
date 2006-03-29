package net.sf.anathema.character.generic.rules;



public interface IEditionVisitor {

  void visitFirstEdition(IExaltedEdition edition);

  void visitSecondEdition(IExaltedEdition edition);

}
