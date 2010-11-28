package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.ISpell;

public interface ISpellLearnStrategy {

  public void addSpells(ISpellConfiguration configuration, ISpell[] addedSpells);

  public void removeSpells(ISpellConfiguration configuration, ISpell[] removedSpells);

  public boolean isSpellAllowed(ISpellConfiguration configuration, ISpell spell);

  public ISpell[] getLearnedSpells(ISpellConfiguration configuration);

  public boolean isLearned(ISpellConfiguration configuration, ISpell spell);
}