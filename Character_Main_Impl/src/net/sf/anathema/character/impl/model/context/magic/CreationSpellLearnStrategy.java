package net.sf.anathema.character.impl.model.context.magic;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ISpellLearnStrategy;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  public void addSpells(ISpellConfiguration configuration, ISpell[] addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  public void removeSpells(ISpellConfiguration configuration, ISpell[] removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  public boolean isSpellAllowed(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  public ISpell[] getLearnedSpells(ISpellConfiguration configuration) {
    return configuration.getLearnedSpells(false);
  }

  public boolean isLearned(ISpellConfiguration configuration, ISpell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}