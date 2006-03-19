package net.sf.anathema.character.generic.type;

public abstract class AbstractSupportedCharacterTypeVisitor implements ICharacterTypeVisitor {

  public void visitDragonKing(CharacterType type) {
    throw new UnsupportedOperationException("Dragon Kings not fully supported"); //$NON-NLS-1$
  }
}