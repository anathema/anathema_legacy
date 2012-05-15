package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.ISpell;

public interface ISpellLearnStrategy {

  void addSpells(ISpellConfiguration configuration, ISpell[] addedSpells);

  void removeSpells(ISpellConfiguration configuration, ISpell[] removedSpells);

  boolean isSpellAllowed(ISpellConfiguration configuration, ISpell spell);

  ISpell[] getLearnedSpells(ISpellConfiguration configuration);

  boolean isLearned(ISpellConfiguration configuration, ISpell spell);
}