package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;

import java.util.List;

public interface ISpellLearnStrategy {

  void addSpells(SpellsModel configuration, List<ISpell> addedSpells);

  void removeSpells(SpellsModel configuration, List<ISpell> removedSpells);

  boolean isSpellAllowed(SpellsModel configuration, ISpell spell);

  ISpell[] getLearnedSpells(SpellsModel configuration);

  boolean isLearned(SpellsModel configuration, ISpell spell);
}