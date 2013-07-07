package net.sf.anathema.character.main.magic;

public interface IMagicVisitor {

  void visitCharm(ICharm charm);

  void visitSpell(ISpell spell);
}