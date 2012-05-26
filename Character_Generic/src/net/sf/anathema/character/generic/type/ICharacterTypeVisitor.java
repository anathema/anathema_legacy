package net.sf.anathema.character.generic.type;

public interface ICharacterTypeVisitor {

  void visitSolar(ICharacterType visitedType);

  void visitMortal(ICharacterType visitedType);

  void visitSidereal(ICharacterType visitedType);
  
  void visitInfernal(ICharacterType visitedType);

  void visitDB(ICharacterType visitedType);

  void visitAbyssal(ICharacterType visitedType);
  
  void visitSpirit(ICharacterType type);
  
  void visitGhost(ICharacterType type);

  void visitLunar(ICharacterType type);
}