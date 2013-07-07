package net.sf.anathema.hero.charms.model.spells;

import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.hero.spells.SpellModel;

public class ExperiencedSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellModel configuration, ISpell[] addedSpells) {
    configuration.addSpells(addedSpells, true);
  }

  @Override
  public void removeSpells(SpellModel configuration, ISpell[] removedSpells) {
    configuration.removeSpells(removedSpells, true);
  }

  @Override
  public boolean isSpellAllowed(SpellModel configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, true);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellModel configuration) {
    return configuration.getLearnedSpells(true);
  }

  @Override
  public boolean isLearned(SpellModel configuration, ISpell spell) {
    return configuration.isLearnedOnCreationOrExperience(spell);
  }
}