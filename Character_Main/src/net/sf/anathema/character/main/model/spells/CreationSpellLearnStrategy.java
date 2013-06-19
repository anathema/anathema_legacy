package net.sf.anathema.character.main.model.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.character.main.model.spells.ISpellLearnStrategy;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellModel configuration, ISpell[] addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  @Override
  public void removeSpells(SpellModel configuration, ISpell[] removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  @Override
  public boolean isSpellAllowed(SpellModel configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellModel configuration) {
    return configuration.getLearnedSpells(false);
  }

  @Override
  public boolean isLearned(SpellModel configuration, ISpell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}