package net.sf.anathema.character.generic.magic;

public interface IMagicVisitor {

  public void visitCharm(ICharm charm);

  public void visitSpell(ISpell spell);
}