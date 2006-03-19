package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ISpellLearnStrategy;

public class ExperiencedSpellLearnStrategy implements ISpellLearnStrategy {

  public void addSpells(ISpellConfiguration configuration, ISpell[] addedSpells) {
    configuration.addSpells(addedSpells, true);
  }

  public void removeSpells(ISpellConfiguration configuration, ISpell[] removedSpells) {
    configuration.removeSpells(removedSpells, true);
  }

  public boolean isSpellAllowed(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, true);
  }

  public ISpell[] getLearnedSpells(ISpellConfiguration configuration) {
    return configuration.getLearnedSpells(true);
  }

  public boolean isLearned(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isLearnedOnCreationOrExperience(spell);
  }
}