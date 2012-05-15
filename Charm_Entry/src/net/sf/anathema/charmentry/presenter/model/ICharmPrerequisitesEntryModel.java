package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;

public interface ICharmPrerequisitesEntryModel {

  void addModelListener(IChangeListener inputListener);

  void setPrerequisiteCharms(ICharm[] charms);

  ICharm[] getAvailableCharms() throws PersistenceException;

  void setRequiresExcellency(boolean required);

}
