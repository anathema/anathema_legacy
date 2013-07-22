package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.spells.Spell;

import java.util.List;

public interface ISpellLearnStrategy {

  void addSpells(SpellsModel configuration, List<Spell> addedSpells);

  void removeSpells(SpellsModel configuration, List<Spell> removedSpells);

  boolean isSpellAllowed(SpellsModel configuration, Spell spell);

  Spell[] getLearnedSpells(SpellsModel configuration);

  boolean isLearned(SpellsModel configuration, Spell spell);
}