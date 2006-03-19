package net.sf.anathema.character.generic.type;

public interface ICharacterTypeVisitor {

  public void visitSolar(CharacterType visitedType);

  public void visitMortal(CharacterType visitedType);

  public void visitSidereal(CharacterType visitedType);

  public void visitDB(CharacterType visitedType);

  public void visitAbyssal(CharacterType visitedType);

  public void visitDragonKing(CharacterType type);

  public void visitLunar(CharacterType type);
}