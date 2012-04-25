package net.sf.anathema.character.generic.magic.spells;

public abstract class SorcercyCircleVisitor implements ICircleTypeVisitor {

  @Override
  public void visitShadowland(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }

  @Override
  public void visitLabyrinth(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }

  @Override
  public void visitVoid(CircleType type) {
    throw new UnsupportedOperationException("Only sorcery should be visited"); //$NON-NLS-1$
  }
}