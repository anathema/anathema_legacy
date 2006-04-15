package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.exception.PersistenceException;

public interface ICharmEntrySubPresenter {

  public void charmAdded(IConfigurableCharmData charmData) throws PersistenceException;

}
