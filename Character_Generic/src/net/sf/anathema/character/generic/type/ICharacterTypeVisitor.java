package net.sf.anathema.character.generic.type;

public interface ICharacterTypeVisitor {

  public void visitSolar(ICharacterType visitedType);

  public void visitMortal(ICharacterType visitedType);

  public void visitSidereal(ICharacterType visitedType);

  public void visitDB(ICharacterType visitedType);

  public void visitAbyssal(ICharacterType visitedType);
  
  public void visitSpirit(ICharacterType type);
  
  public void visitGhost(ICharacterType type);

  public void visitDragonKing(ICharacterType type);

  public void visitLunar(ICharacterType type);
}