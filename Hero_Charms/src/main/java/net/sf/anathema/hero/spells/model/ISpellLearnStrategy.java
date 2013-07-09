package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.spells.SpellModel;

public interface ISpellLearnStrategy {

  void addSpells(SpellModel configuration, ISpell[] addedSpells);

  void removeSpells(SpellModel configuration, ISpell[] removedSpells);

  boolean isSpellAllowed(SpellModel configuration, ISpell spell);

  ISpell[] getLearnedSpells(SpellModel configuration);

  boolean isLearned(SpellModel configuration, ISpell spell);
}