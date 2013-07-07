package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface IMagicVisitor {

  void visitCharm(ICharm charm);

  void visitSpell(ISpell spell);
}