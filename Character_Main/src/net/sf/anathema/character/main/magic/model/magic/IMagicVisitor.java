package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.ISpell;

public interface IMagicVisitor {

  void visitCharm(Charm charm);

  void visitSpell(ISpell spell);
}