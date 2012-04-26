package net.sf.anathema.character.generic.dummy.magic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.IMagicLearnListener;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummySpellConfiguration implements ISpellConfiguration {

  private List<ISpell> spells = new ArrayList<ISpell>();

  @Override
  public void removeSpells(ISpell[] removedSpells) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addSpells(ISpell[] addedSpells) {
    spells.addAll(Arrays.asList(addedSpells));
  }

  @Override
  public ISpell[] getLearnedSpells() {
    throw new NotYetImplementedException();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    throw new NotYetImplementedException();
  }

  public ISpell[] getAllSpells() {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isSpellAllowed(ISpell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell[] getSpellsByCircle(CircleType circle) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell getSpellById(String string) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearnedOnCreation(ISpell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell[] getLearnedSpells(boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented"); //$NON-NLS-1$
    }
    return spells.toArray(new ISpell[spells.size()]);
  }

  @Override
  public void addSpells(ISpell[] addedSpells, boolean experienced) {
    throw new NotYetImplementedException();
  }

  @Override
  public void removeSpells(ISpell[] removedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented"); //$NON-NLS-1$
    }
    spells.removeAll(Arrays.asList(removedSpells));
  }

  @Override
  public boolean isSpellAllowed(ISpell spell, boolean experienced) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(ISpell spell) {
    return false;
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(ISpell spell) {
    return false;
  }

  @Override
  public void addMagicLearnListener(IMagicLearnListener<ISpell> listener) {
    throw new NotYetImplementedException();
  }
}