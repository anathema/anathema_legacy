package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.IPresenter;

public interface ICharmEntrySubPresenter extends IPresenter {

  public void charmAdded(IConfigurableCharmData charmData) throws PersistenceException;
}