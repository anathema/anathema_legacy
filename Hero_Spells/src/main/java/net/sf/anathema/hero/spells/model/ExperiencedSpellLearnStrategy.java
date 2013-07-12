package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;

import java.util.List;

public class ExperiencedSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellsModel configuration, List<ISpell> addedSpells) {
    configuration.addSpells(addedSpells, true);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<ISpell> removedSpells) {
    configuration.removeSpells(removedSpells, true);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, true);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellsModel configuration) {
    return configuration.getLearnedSpells(true);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, ISpell spell) {
    return configuration.isLearnedOnCreationOrExperience(spell);
  }
}