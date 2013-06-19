package net.sf.anathema.character.main.model.spells;

import net.sf.anathema.character.generic.magic.ISpell;

public interface ISpellLearnStrategy {

  void addSpells(SpellModel configuration, ISpell[] addedSpells);

  void removeSpells(SpellModel configuration, ISpell[] removedSpells);

  boolean isSpellAllowed(SpellModel configuration, ISpell spell);

  ISpell[] getLearnedSpells(SpellModel configuration);

  boolean isLearned(SpellModel configuration, ISpell spell);
}