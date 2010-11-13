package net.sf.anathema.character.generic.magic.spells;

public abstract class NecromancyCircleVisitor implements ICircleTypeVisitor {
  public void visitTerrestrial(CircleType type) {
    throw new UnsupportedOperationException("Only necromancy should be visited"); //$NON-NLS-1$
  }

  public void visitCelestial(CircleType type) {
    throw new UnsupportedOperationException("Only necromancy should be visited"); //$NON-NLS-1$
  }

  public void visitSolar(CircleType type) {
    throw new UnsupportedOperationException("Only necromancy should be visited"); //$NON-NLS-1$
  }
}