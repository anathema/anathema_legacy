package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ISpellLearnStrategy;

public class ExperiencedSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(ISpellConfiguration configuration, ISpell[] addedSpells) {
    configuration.addSpells(addedSpells, true);
  }

  @Override
  public void removeSpells(ISpellConfiguration configuration, ISpell[] removedSpells) {
    configuration.removeSpells(removedSpells, true);
  }

  @Override
  public boolean isSpellAllowed(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, true);
  }

  @Override
  public ISpell[] getLearnedSpells(ISpellConfiguration configuration) {
    return configuration.getLearnedSpells(true);
  }

  @Override
  public boolean isLearned(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isLearnedOnCreationOrExperience(spell);
  }
}