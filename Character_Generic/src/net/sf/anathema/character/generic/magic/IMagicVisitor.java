package net.sf.anathema.character.generic.magic;

public interface IMagicVisitor {

  void visitCharm(ICharm charm);

  void visitSpell(ISpell spell);
}