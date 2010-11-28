package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISpellConfiguration {

  public void removeSpells(ISpell[] removedSpells);

  public void addSpells(ISpell[] addedSpells);

  public ISpell[] getLearnedSpells();

  public void addChangeListener(IChangeListener listener);

  public void addMagicLearnListener(IMagicLearnListener<ISpell> listener);

  public boolean isSpellAllowed(ISpell spell);

  public ISpell[] getSpellsByCircle(CircleType circle);

  public ISpell getSpellById(String string);

  public boolean isLearnedOnCreation(ISpell spell);

  public ISpell[] getLearnedSpells(boolean experienced);

  public void addSpells(ISpell[] addedSpells, boolean experienced);

  public void removeSpells(ISpell[] removedSpells, boolean experienced);

  public boolean isSpellAllowed(ISpell spell, boolean experienced);

  public boolean isLearned(ISpell spell);

  public boolean isLearnedOnCreationOrExperience(ISpell spell);
}