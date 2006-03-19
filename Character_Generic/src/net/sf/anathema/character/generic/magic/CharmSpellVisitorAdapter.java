package net.sf.anathema.character.generic.magic;

public abstract class CharmSpellVisitorAdapter implements IMagicVisitor {

  public final void visitMartialArtsCharm(IMartialArtsCharm charm) {
    visitCharm(charm);
  }
}