package net.sf.anathema.character.generic.magic.spells;

public abstract class SorcercyCircleVisitor implements ICircleTypeVisitor {

  public void visitShadowland(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }

  public void visitLabyrinth(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }

  public void visitVoid(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }
}