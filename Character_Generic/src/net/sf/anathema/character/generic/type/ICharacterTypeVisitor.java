package net.sf.anathema.character.generic.type;

public interface ICharacterTypeVisitor {

  void visitSolar();

  void visitMortal();

  void visitSidereal();
  
  void visitInfernal();

  void visitDB();

  void visitAbyssal();
  
  void visitSpirit();
  
  void visitGhost();

  void visitLunar();
}