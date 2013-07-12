package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.Spell;

import java.util.List;

public class ExperiencedSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellsModel configuration, List<Spell> addedSpells) {
    configuration.addSpells(addedSpells, true);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<Spell> removedSpells) {
    configuration.removeSpells(removedSpells, true);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, Spell spell) {
    return configuration.isSpellAllowed(spell, true);
  }

  @Override
  public Spell[] getLearnedSpells(SpellsModel configuration) {
    return configuration.getLearnedSpells(true);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, Spell spell) {
    return configuration.isLearnedOnCreationOrExperience(spell);
  }
}