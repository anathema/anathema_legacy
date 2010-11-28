package net.sf.anathema.character.generic.type;

public abstract class AbstractSupportedCharacterTypeVisitor implements ICharacterTypeVisitor {

  public void visitDragonKing(ICharacterType type) {
    throw new UnsupportedOperationException("Dragon Kings not fully supported"); //$NON-NLS-1$
  }
}